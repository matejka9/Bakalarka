package com.dusky.festival.adapter.podujatie;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.dusky.festival.R;
import com.dusky.festival.database.MojePodujatiaDBHelper;
import com.example.dusky.myapplication.backend.podujatie.model.PodujatieResponseEntityModel;
import com.example.dusky.myapplication.backend.podujatie.model.VystupenieResponseEntityModel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by dusky on 5/23/16.
 */
public class PodujatieAdapter extends ArrayAdapter<PodujatieResponseEntityModel> {

    private Context context;
    private int layoutResourceId;
    private List<PodujatieResponseEntityModel> data = null;
    private HashSet<Long> inputFavourite;
    private MojePodujatiaDBHelper db;

    public PodujatieAdapter(Context context, int layoutResourceId, List<PodujatieResponseEntityModel> data, HashSet<Long> inputFavourite, MojePodujatiaDBHelper db) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        this.inputFavourite = inputFavourite;
        this.db = db;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public PodujatieResponseEntityModel getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final PodujatieHolder holder;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new PodujatieHolder();
            holder.datumOd = (TextView)row.findViewById(R.id.text_datum_od);
            holder.datumDo = (TextView)row.findViewById(R.id.text_datum_do);
            holder.nazov = (TextView)row.findViewById(R.id.text_typ);
            holder.typ = (TextView)row.findViewById(R.id.text_nazov);
            holder.favourite = (ToggleButton) row.findViewById(R.id.favorite);
            row.setTag(holder);
        }
        else
        {
            holder = (PodujatieHolder)row.getTag();
        }

        final PodujatieResponseEntityModel podujatie = data.get(position);

        if (inputFavourite.contains(podujatie.getId())){
            holder.favourite.setChecked(true);
        }else{
            holder.favourite.setChecked(false);
        }

        if (podujatie.getDatumOd() != null) {
            holder.datumOd.setText("Od: " + new Date(podujatie.getDatumOd().getValue()).toString());
        }

        if (podujatie.getDatumDo() != null){
            holder.datumDo.setText("Do: " + new Date(podujatie.getDatumDo().getValue()).toString());
        }
        holder.nazov.setText(podujatie.getNazov());
        holder.typ.setText(podujatie.getTyp());

        holder.favourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    inputFavourite.add(podujatie.getId());
                    db.insertPodujatie(podujatie);
                } else {
                    inputFavourite.remove(podujatie.getId());
                    System.out.println(podujatie);
                    db.deletePodujatie(podujatie);
                }
            }
        });
        return row;
    }

    public void succesDownload(List<PodujatieResponseEntityModel> developments) {
        System.out.println(developments);
        data = developments;
        notifyDataSetChanged();
    }

    public void failedDownload() {
        System.out.println("Chyba pri stahovani");
        data = new ArrayList<PodujatieResponseEntityModel>();
        notifyDataSetChanged();
    }

    static class PodujatieHolder
    {
        TextView datumOd;
        TextView datumDo;
        TextView typ;
        TextView nazov;
        ToggleButton favourite;
    }
}
package com.dusky.festival.adapter.vystupenie;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.dusky.festival.R;
import com.dusky.festival.activity.detail.PodiumDetailListActivity;
import com.dusky.festival.database.MojeVystupeniaDBHelper;
import com.example.dusky.myapplication.backend.podujatie.model.VystupenieResponseEntityModel;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by dusky on 5/28/16.
 */
public class VystupenieAdapter extends ArrayAdapter<VystupenieResponseEntityModel> {

    private final PodiumDetailListActivity context;
    private final MojeVystupeniaDBHelper db;
    private int layoutResourceId;
    private HashSet<Long> inputFavourite;
    private List<VystupenieResponseEntityModel> data = null;
    private Long idPodujatie;

    public VystupenieAdapter(PodiumDetailListActivity stanokDetailListActivity, int tovar_item_row, List<VystupenieResponseEntityModel> listValues, HashSet<Long> inputFavourite, MojeVystupeniaDBHelper db, Long idPodujatie) {
        super(stanokDetailListActivity, tovar_item_row, listValues);
        this.context = stanokDetailListActivity;
        this.layoutResourceId = tovar_item_row;
        this.data = listValues;
        this.inputFavourite = inputFavourite;
        this.db = db;
        this.idPodujatie = idPodujatie;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public VystupenieResponseEntityModel getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final VystupenieHolder holder;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new VystupenieHolder();
            holder.casOd = (TextView)row.findViewById(R.id.text_cas_od);
            holder.casDo = (TextView)row.findViewById(R.id.text_cas_do);
            holder.nazov = (TextView)row.findViewById(R.id.text_nazov);
            holder.favourite = (ToggleButton) row.findViewById(R.id.favorite);
            row.setTag(holder);
        }
        else
        {
            holder = (VystupenieHolder)row.getTag();
        }

        final VystupenieResponseEntityModel vystupenie = data.get(position);

        if (vystupenie.getCasOd() != null) {
            holder.casOd.setText("Od: " + new Date(vystupenie.getCasOd().getValue()).toString());
        }

        if (vystupenie.getCasDo() != null){
            holder.casDo.setText("Do: " + new Date(vystupenie.getCasDo().getValue()).toString());
        }

        holder.nazov.setText(vystupenie.getNazov());

        if (inputFavourite.contains(vystupenie.getId())){
            holder.favourite.setChecked(true);
        }else{
            holder.favourite.setChecked(false);
        }

        holder.favourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    inputFavourite.add(vystupenie.getId());
                    db.insertPodujatie(vystupenie, idPodujatie);
                } else {
                    inputFavourite.remove(vystupenie.getId());
                    System.out.println(vystupenie);
                    db.deletePodujatie(vystupenie);
                }
            }
        });

        return row;
    }

    static class VystupenieHolder
    {
        TextView casOd;
        TextView casDo;
        TextView nazov;
        ToggleButton favourite;
    }
}

package com.dusky.festival.adapter.notifikacia;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dusky.festival.R;
import com.example.dusky.myapplication.backend.notifikacia.model.NotifikaciaResponseEntityModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dusky on 6/14/16.
 */
public class NotifikaciaAdapter extends ArrayAdapter<NotifikaciaResponseEntityModel> {
    private final Context context;
    private final int layoutResourceId;
    private List<NotifikaciaResponseEntityModel> data;

    public NotifikaciaAdapter(Context context, int layoutResourceId, List<NotifikaciaResponseEntityModel> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public NotifikaciaResponseEntityModel getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final NotifikaciaHolder holder;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new NotifikaciaHolder();
            holder.nadpis = (TextView)row.findViewById(R.id.nadpis);
            holder.text = (TextView)row.findViewById(R.id.text);
            row.setTag(holder);
        }
        else
        {
            holder = (NotifikaciaHolder)row.getTag();
        }

        final NotifikaciaResponseEntityModel podujatie = data.get(position);

        holder.nadpis.setText(podujatie.getNadpis());
        holder.text.setText(podujatie.getText());

        return row;
    }

    public void succesDownload(List<NotifikaciaResponseEntityModel> developments) {
        System.out.println(developments);
        data = developments;
        notifyDataSetChanged();
    }

    public void failedDownload() {
        System.out.println("Chyba pri stahovani");
        data = new ArrayList<NotifikaciaResponseEntityModel>();
        notifyDataSetChanged();
    }

    static class NotifikaciaHolder{
        TextView nadpis;
        TextView text;
    }
}

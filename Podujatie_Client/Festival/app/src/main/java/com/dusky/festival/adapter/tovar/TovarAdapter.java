package com.dusky.festival.adapter.tovar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dusky.festival.R;
import com.dusky.festival.activity.detail.StanokDetailListActivity;
import com.example.dusky.myapplication.backend.podujatie.model.TovarResponseEntityModel;

import java.util.List;

/**
 * Created by dusky on 5/28/16.
 */
public class TovarAdapter extends ArrayAdapter<TovarResponseEntityModel> {

    Context context;
    int layoutResourceId;
    List<TovarResponseEntityModel> data = null;

    public TovarAdapter(StanokDetailListActivity stanokDetailListActivity, int tovar_item_row, List<TovarResponseEntityModel> listValues) {
        super(stanokDetailListActivity, tovar_item_row, listValues);
        this.context = stanokDetailListActivity;
        this.layoutResourceId = tovar_item_row;
        this.data = listValues;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public TovarResponseEntityModel getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final TovarHolder holder;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new TovarHolder();
            holder.nazov = (TextView)row.findViewById(R.id.tovar_nazov);
            holder.cena = (TextView)row.findViewById(R.id.tovar_cena);
            row.setTag(holder);
        }
        else
        {
            holder = (TovarHolder)row.getTag();
        }

        final TovarResponseEntityModel tovar = data.get(position);

        holder.nazov.setText("Tovar: " + tovar.getNazov());
        holder.cena.setText( "Cena:  " + tovar.getCena());

        return row;
    }

    static class TovarHolder
    {
        TextView nazov;
        TextView cena;
    }
}

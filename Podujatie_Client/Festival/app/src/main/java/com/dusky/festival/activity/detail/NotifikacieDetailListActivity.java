package com.dusky.festival.activity.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.dusky.festival.R;
import com.dusky.festival.activity.base.menu.LoadingListActivity;
import com.dusky.festival.adapter.notifikacia.NotifikaciaAdapter;
import com.dusky.festival.gae.connection.notifikacia.NotifikacieAsyncTask;
import com.example.dusky.myapplication.backend.notifikacia.model.NotifikaciaResponseEntityModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dusky on 6/14/16.
 */
public class NotifikacieDetailListActivity extends LoadingListActivity {

    private Long idMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podujatie);

        headerText = (TextView) findViewById(R.id.mainText);
        headerText.setText("Posledné upozornenia");
        mProgressView = findViewById(R.id.podujatie_progress);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.idMapa = extras.getLong("idMapa");
        }

        List<NotifikaciaResponseEntityModel> listValues = new ArrayList<NotifikaciaResponseEntityModel>();

        NotifikaciaAdapter adapter = new NotifikaciaAdapter(this, R.layout.notifikacia_item_row, listValues);

        listView = this.getListView();

        View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
        listView.addHeaderView(header);

        listView.setAdapter(adapter);

        showProgress(true);
        new NotifikacieAsyncTask(this, adapter).execute(idMapa);
    }

    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);

        NotifikaciaResponseEntityModel selectedItem = (NotifikaciaResponseEntityModel) getListView().getItemAtPosition(position);

        Intent intent = new Intent(this, NotifikaciaDetailActivity.class );
        intent.putExtra("nadpis", selectedItem.getNadpis());
        intent.putExtra("detail", selectedItem.getDetail());
        startActivity(intent);
    }
}

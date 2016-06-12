package com.dusky.festival.activity.base.menu;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Pair;
import android.widget.Toast;

import com.dusky.festival.R;
import com.dusky.festival.activity.base.map.MapTileViewActivity;
import com.dusky.festival.activity.detail.PodiumDetailListActivity;
import com.dusky.festival.adapter.podujatie.PodujatieAdapter;
import com.dusky.festival.database.MojePodujatiaDBHelper;
import com.dusky.festival.gae.connection.podujatie.MojePodujatiaAsyncTask;
import com.dusky.festival.gae.connection.podujatie.NajblizsiePodujatieAsyncTask;
import com.dusky.festival.gae.connection.vystupenie.MojeVystupenieAsyncTask;
import com.dusky.festival.parcelable.InputMenuType;
import com.example.dusky.myapplication.backend.podujatie.model.PodujatieResponseEntityModel;
import com.google.api.client.util.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class PodujatieListActivity extends LoadingListActivity {

    private static InputMenuType type;
    private MojePodujatiaDBHelper db;

    public PodujatieListActivity(){
        this.db = new MojePodujatiaDBHelper(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podujatie);

        headerText = (TextView) findViewById(R.id.mainText);
        mProgressView = findViewById(R.id.podujatie_progress);

        List<PodujatieResponseEntityModel> listValues = new ArrayList<PodujatieResponseEntityModel>();

        HashSet<Long> mojePodujatia = getMojePodujatia();

        PodujatieAdapter adapter = new PodujatieAdapter(this, R.layout.podujatie_item_row, listValues, mojePodujatia, db);

        listView = this.getListView();

        View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
        listView.addHeaderView(header);

        listView.setAdapter(adapter);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = (InputMenuType) extras.getSerializable("menu");
            headerText.setText(type.getValue());
        }

        super.showProgress(true);
        switch (type) {
            case NAJBLIZSIE_PODUJATIA:
                new NajblizsiePodujatieAsyncTask(this).execute(new Pair<PodujatieAdapter, Pair<DateTime, Pair<Long, Long>>>(adapter, new Pair<DateTime, Pair<Long, Long>>(new DateTime(new Date()), new Pair<Long, Long>(200l, 0l))));
                break;
            case MOJE_PODUJATIA:
                new MojePodujatiaAsyncTask(this).execute(adapter);
                break;
            case MOJE_VYSTUPENIA:
                new MojeVystupenieAsyncTask(this).execute(new Pair<PodujatieAdapter, Pair<DateTime, Pair<Long, Long>>>(adapter, new Pair<DateTime, Pair<Long, Long>>(new DateTime(new Date()), new Pair<Long, Long>(200l, 0l))));
                break;
            default:
                super.showProgress(false);
                headerText.setText("Neznámy typ");
                break;
        }
    }

    // when an item of the list is clicked
    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);

        PodujatieResponseEntityModel selectedItem = (PodujatieResponseEntityModel) getListView().getItemAtPosition(position);

        switch (type) {
            case MOJE_VYSTUPENIA:
                Toast.makeText(this, "Vystupenie.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, PodiumDetailListActivity.class );
                intent.putExtra("poduajtieId", selectedItem.getId());
                intent.putExtra("podiumNazov", selectedItem.getNazov());
                intent.putExtra("prisielZPodujatia", true);
                startActivity(intent);
                break;
            default:
                intent = new Intent(this, MapTileViewActivity.class );
                intent.putExtra("id", selectedItem.getId());
                startActivity(intent);
                break;
        }
    }

    public HashSet<Long> getMojePodujatia() {
        HashSet<Long> vysledok = new HashSet<Long>();
        ArrayList<PodujatieResponseEntityModel> mojePodujatia = db.getAllPodujatie();
        for (PodujatieResponseEntityModel mojePodujatie : mojePodujatia){
            vysledok.add(mojePodujatie.getId());
        }
        return vysledok;
    }
}

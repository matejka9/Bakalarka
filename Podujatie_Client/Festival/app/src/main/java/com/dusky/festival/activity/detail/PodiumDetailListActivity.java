package com.dusky.festival.activity.detail;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.dusky.festival.R;
import com.dusky.festival.adapter.vystupenie.VystupenieAdapter;
import com.dusky.festival.database.MojeVystupeniaDBHelper;
import com.dusky.festival.database.VystupenieDBHelper;
import com.example.dusky.myapplication.backend.podujatie.model.VystupenieResponseEntityModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by dusky on 5/28/16.
 */
public class PodiumDetailListActivity extends ListActivity{
    private TextView headerText;
    private ListView listView;

    private VystupenieDBHelper dbVystupenia;
    private MojeVystupeniaDBHelper dbMojeVystupenia;
    private Long idPodium;
    private Long idPodujatie;
    private Boolean prisielZPodujatia;

    public PodiumDetailListActivity(){
        this.dbVystupenia = new VystupenieDBHelper(this);
        this.dbMojeVystupenia = new MojeVystupeniaDBHelper(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podujatie);

        headerText = (TextView) findViewById(R.id.mainText);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.idPodium = extras.getLong("podiumId");
            this.idPodujatie = extras.getLong("poduajtieId");
            this.headerText.setText(extras.getString("podiumNazov"));
            this.prisielZPodujatia = extras.getBoolean("prisielZPodujatia");
        }

        List<VystupenieResponseEntityModel> listValues;
        if(prisielZPodujatia != null && prisielZPodujatia){
            listValues = getMojeVystupeniaForPodujatie();
        }else{
            listValues = getVystupeniaForPodium();
        }

        HashSet<Long> inputFavourite = getMojeIdVystupenia();

        VystupenieAdapter adapter = new VystupenieAdapter(this, R.layout.vystupenie_item_row, listValues, inputFavourite, dbMojeVystupenia, idPodujatie);

        listView = this.getListView();

        View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
        listView.addHeaderView(header);

        listView.setAdapter(adapter);
    }

    // when an item of the list is clicked
    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);

        VystupenieResponseEntityModel selectedItem = (VystupenieResponseEntityModel) getListView().getItemAtPosition(position);

        Intent intent = new Intent(this, VystupenieDetailActivity.class );
        intent.putExtra("id", selectedItem.getId());
        intent.putExtra("nazov", selectedItem.getNazov());
        intent.putExtra("detail", selectedItem.getDetail());
        intent.putExtra("casOd", selectedItem.getCasOd().toStringRfc3339());
        intent.putExtra("casDo", selectedItem.getCasDo().toStringRfc3339());
        intent.putExtra("podium_id", selectedItem.getPodiumId());
        intent.putExtra("podujatie_id", idPodujatie);
        intent.putExtra("vystupenie_id", selectedItem.getVystupenieId());
        startActivity(intent);

    }

    public List<VystupenieResponseEntityModel> getVystupeniaForPodium() {
        return dbVystupenia.vystupenieForPodium(idPodium);
    }

    public ArrayList<VystupenieResponseEntityModel> getMojeVystupeniaForPodujatie() {
        ArrayList<VystupenieResponseEntityModel> mojeVystupenia = dbMojeVystupenia.getAllVystupeniaForPodujatie(idPodujatie);
        return mojeVystupenia;
    }

    public HashSet<Long> getMojeIdVystupenia() {
        HashSet<Long> vysledok = new HashSet<Long>();
        ArrayList<VystupenieResponseEntityModel> mojeVystupenia;
        if(prisielZPodujatia != null && prisielZPodujatia){
            mojeVystupenia = dbMojeVystupenia.getAllVystupeniaForPodujatie(idPodujatie);
        }else{
            mojeVystupenia = dbMojeVystupenia.getAllVystupeniaForPodium(idPodium);
        }
        dbMojeVystupenia.getAllVystupeniaForPodium(idPodium);
        for (VystupenieResponseEntityModel mojeVystupenie : mojeVystupenia){
            vysledok.add(mojeVystupenie.getId());
        }
        return vysledok;
    }
}

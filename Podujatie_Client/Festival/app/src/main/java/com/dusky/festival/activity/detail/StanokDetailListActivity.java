package com.dusky.festival.activity.detail;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.dusky.festival.R;
import com.dusky.festival.adapter.tovar.TovarAdapter;
import com.dusky.festival.database.TovarDBHelper;
import com.example.dusky.myapplication.backend.podujatie.model.TovarResponseEntityModel;

import java.util.List;

/**
 * Created by dusky on 5/28/16.
 */
public class StanokDetailListActivity extends ListActivity{
    private TextView headerText;
    private ListView listView;

    private TovarDBHelper db;
    private Long idStanok;

    public StanokDetailListActivity(){
        this.db = new TovarDBHelper(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podujatie);

        headerText = (TextView) findViewById(R.id.mainText);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.idStanok = extras.getLong("stanokId");
            headerText.setText(extras.getString("stanokNazov"));
        }

        List<TovarResponseEntityModel> listValues = getTovarForStanok();

        TovarAdapter adapter = new TovarAdapter(this, R.layout.tovar_item_row, listValues);

        listView = this.getListView();

        View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
        listView.addHeaderView(header);

        listView.setAdapter(adapter);
    }

    // when an item of the list is clicked
    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);
    }

    public List<TovarResponseEntityModel> getTovarForStanok() {
        return db.tovarForStanok(idStanok);
    }
}

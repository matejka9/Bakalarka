package com.dusky.festival.activity.detail;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.dusky.festival.R;
import com.example.dusky.myapplication.backend.podujatie.model.VystupenieResponseEntityModel;
import com.google.api.client.util.DateTime;

public class VystupenieDetailActivity extends AppCompatActivity {
    private VystupenieResponseEntityModel vystupenie;
    private Long idPodujatie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vystupenie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NestedScrollView nestedView = (NestedScrollView) getLayoutInflater().inflate(R.layout.content_vystupenie_detail, null);

        AppBarLayout layout = (AppBarLayout) findViewById(R.id.app_bar);
        CollapsingToolbarLayout colLayout = (CollapsingToolbarLayout) layout.findViewById(R.id.toolbar_layout);

        vystupenie = new VystupenieResponseEntityModel();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.vystupenie.setId(extras.getLong("id"));
            this.vystupenie.setNazov(extras.getString("nazov"));
            this.vystupenie.setDetail(extras.getString("detail"));
            this.vystupenie.setCasOd(DateTime.parseRfc3339(extras.getString("casOd")));
            this.vystupenie.setCasDo(DateTime.parseRfc3339(extras.getString("casDo")));
            this.vystupenie.setPodiumId(extras.getLong("podium_id"));
            this.vystupenie.setVystupenieId(extras.getLong("vystupenie_id"));
            this.idPodujatie = extras.getLong("podujatie_id");

            colLayout.setTitle(vystupenie.getNazov());

            TextView tv = (TextView)findViewById(R.id.large_text); // just like single layout
            tv.setText(vystupenie.getDetail());
        }

    }
}

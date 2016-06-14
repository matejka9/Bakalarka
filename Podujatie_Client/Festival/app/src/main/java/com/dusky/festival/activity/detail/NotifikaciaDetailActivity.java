package com.dusky.festival.activity.detail;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.dusky.festival.R;

/**
 * Created by dusky on 6/14/16.
 */
public class NotifikaciaDetailActivity extends AppCompatActivity {
    private String nadpis;
    private String detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vystupenie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NestedScrollView nestedView = (NestedScrollView) getLayoutInflater().inflate(R.layout.content_vystupenie_detail, null);

        AppBarLayout layout = (AppBarLayout) findViewById(R.id.app_bar);
        CollapsingToolbarLayout colLayout = (CollapsingToolbarLayout) layout.findViewById(R.id.toolbar_layout);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.nadpis = extras.getString("nadpis");
            this.detail = extras.getString("detail");

            colLayout.setTitle(nadpis);

            TextView tv = (TextView) findViewById(R.id.large_text); // just like single layout
            tv.setText(detail);
        }

    }
}
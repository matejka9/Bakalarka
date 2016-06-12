package com.dusky.festival.activity.base.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dusky.festival.R;
import com.dusky.festival.parcelable.InputMenuType;
import com.dusky.festival.parcelable.LoginParcerable;

public class MainMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String email;
    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            LoginParcerable value = extras.getParcelable("login");

            this.email = value.getEmail();
            this.id = value.getId();

            TextView textView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.textView);
            textView.setText(value.getEmail());
        }

        final MainMenuActivity parent = this;

        Button najblizsiePodujatia = (Button) findViewById(R.id.najblizsie_podujatia);
        najblizsiePodujatia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(parent, PodujatieListActivity.class);
                InputMenuType parcelable = InputMenuType.NAJBLIZSIE_PODUJATIA;
                i.putExtra("menu", parcelable);
                startActivity(i);
            }
        });

        Button mojePodujatia = (Button) findViewById(R.id.moje_podujatia);
        mojePodujatia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(parent, PodujatieListActivity.class);
                InputMenuType parcelable = InputMenuType.MOJE_PODUJATIA;
                i.putExtra("menu", parcelable);
                startActivity(i);
            }
        });

        Button mojeVystupenia = (Button) findViewById(R.id.moje_vystupenia);
        mojeVystupenia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(parent, PodujatieListActivity.class);
                InputMenuType parcelable = InputMenuType.MOJE_VYSTUPENIA;
                i.putExtra("menu", parcelable);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_share) {
            super.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

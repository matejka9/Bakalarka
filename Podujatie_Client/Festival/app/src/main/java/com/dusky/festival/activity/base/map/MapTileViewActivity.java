package com.dusky.festival.activity.base.map;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dusky.festival.R;
import com.dusky.festival.activity.base.map.callout.SampleCallout;
import com.dusky.festival.activity.base.map.callout.SampleCalloutDetail;
import com.dusky.festival.activity.base.map.provider.BitmapProviderLocalFiles;
import com.dusky.festival.activity.detail.NotifikacieDetailListActivity;
import com.dusky.festival.database.MojeMiestaDbHelper;
import com.dusky.festival.database.TovarDBHelper;
import com.dusky.festival.database.VystupenieDBHelper;
import com.dusky.festival.gae.connection.MapaAsyncTask;
import com.dusky.festival.gps.MyLocationListener;
import com.dusky.festival.helper.image.SplitCutterImage;
import com.dusky.festival.helper.image.SplittedImage;
import com.dusky.festival.helper.ui.HideKeyboardHelper;
import com.dusky.festival.model.MyPoint;
import com.example.dusky.myapplication.backend.podujatie.model.OstatneMiestaResponseEntityModel;
import com.example.dusky.myapplication.backend.podujatie.model.PodiumResponseEntityModel;
import com.example.dusky.myapplication.backend.podujatie.model.PodujatieDetailResponseEntityModel;
import com.example.dusky.myapplication.backend.podujatie.model.StanokResponseEntityModel;
import com.example.dusky.myapplication.backend.podujatie.model.TovarResponseEntityModel;
import com.example.dusky.myapplication.backend.podujatie.model.VystupenieResponseEntityModel;
import com.qozix.tileview.TileView;
import com.qozix.tileview.markers.MarkerLayout;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class MapTileViewActivity extends TileViewActivity {


    private double NORTH_WEST_LATITUDE;
    private double NORTH_WEST_LONGITUDE;
    private double SOUTH_EAST_LATITUDE;
    private double SOUTH_EAST_LONGITUDE;

    private Long idPodujatie;
    private Long idMapa;
    private String keyMapy;

    private MojeMiestaDbHelper mojeMiestaDbHelper;

    private PodujatieDetailResponseEntityModel data;
    private List<StanokResponseEntityModel> stanky;
    private List<PodiumResponseEntityModel> podia;
    private List<OstatneMiestaResponseEntityModel> ostatneMiesta;
    private List<MyPoint> mojeMiesta;

    private HashMap<Long, List<TovarResponseEntityModel>> stanokAndTovar;
    private HashMap<Long, List<VystupenieResponseEntityModel>> podiumAndVystupenie;

    private ArrayList<ImageView> ostatneBudovyMarkre;
    private ArrayList<ImageView> stankyMarkre;
    private ArrayList<ImageView> podiaMarkre;
    private ArrayList<ImageView> mojeMiestaMarkre;

    private LocationManager locationManager;
    private View user;
    private double userLatitude = 25.0;
    private double userLongitude = 60.0;
    private MyLocationListener locationListener;

    private int indexNajlacnejsi;


    public void changePosition(double latitude, double longitude) {
        TileView tile = getTileView();
        tile.removeMarker(user);
        System.out.println(latitude + " " + longitude);
        this.userLatitude = latitude;
        this.userLongitude = longitude;
        tile.addMarker(user, userLatitude, userLongitude, null, null);
        Log.d("Pozicia", userLatitude + " " + userLongitude);
    }

    public enum CallOutType {
        STANOK, PODIUM, OSTATNE_BUDOVY, USER, MOJE_MIESTO
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View v = getCurrentFocus();
        if (v instanceof EditText) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            // calculate the relative position of the clicking position against the position of the view
            float x = event.getRawX() - scrcoords[0];
            float y = event.getRawY() - scrcoords[1];

            // check whether action is up and the clicking position is outside of the view
            if (event.getAction() == MotionEvent.ACTION_UP
                    && (x < 0 || x > v.getRight() - v.getLeft()
                    || y < 0 || y > v.getBottom() - v.getTop())) {
                if (v.getOnFocusChangeListener() != null) {
                    v.getOnFocusChangeListener().onFocusChange(v, false);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mojeMiestaDbHelper = new MojeMiestaDbHelper(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.idPodujatie = extras.getLong("id");
            this.keyMapy = SplitCutterImage.getKeyForMap(idPodujatie);
        }

        ostatneBudovyMarkre = new ArrayList<ImageView>();
        stankyMarkre = new ArrayList<ImageView>();
        podiaMarkre = new ArrayList<ImageView>();
        mojeMiestaMarkre = new ArrayList<ImageView>();

        showProgress(true);
        new MapaAsyncTask().execute(new Pair<MapTileViewActivity, Long>(this, idPodujatie));
    }

    @Override
    protected void pouzilNotifikacie() {
        System.out.println("Pouzil notifikacie");
        Intent intent = new Intent(this, NotifikacieDetailListActivity.class );
        intent.putExtra("idMapa", this.idMapa);
        startActivity(intent);
    }

    @Override
    protected void pouzilPoziciu() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nová poloha");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        final Activity context = this;
        final double longitude = this.userLongitude;
        final double latitude = this.userLatitude;

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyPoint myPoint = new MyPoint(longitude, latitude + 25, input.getText().toString());
                Long id = mojeMiestaDbHelper.insertMojeMiesto(myPoint, idPodujatie);
                myPoint.setId(id);
                addMojeMiestoMarker(myPoint);
            }
        });
        builder.setNegativeButton("Zrušiť", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }



    @Override
    protected void pouzilInput() {
        System.out.println("Klikol");
        HideKeyboardHelper.hideSoftKeyboard(this);
        hideAllMarks();
        String hladanyTovar = input.getText().toString();

        if (indexNajlacnejsi >= 0){
            ImageView marker = stankyMarkre.get(indexNajlacnejsi);
            marker.setImageResource(R.drawable.map_marker_normal );
            indexNajlacnejsi = -1;
        }

        if (hladanyTovar.equals("")){
            showAllMarks();
        }else{
            TileView tile = getTileView();
            double najlacnejsiaCena = Double.MAX_VALUE;

            for (int index = 0; index < stanky.size(); index++){
                StanokResponseEntityModel stanok = stanky.get(index);
                for (TovarResponseEntityModel tovar: stanokAndTovar.get(stanok.getId())){
                    if (tovar.getNazov().equals(hladanyTovar)){
                        if (tovar.getCena() < najlacnejsiaCena){
                            najlacnejsiaCena = tovar.getCena();
                            indexNajlacnejsi = index;
                        }
                        View marker = stankyMarkre.get(index);
                        tile.addMarker( marker, stanok.getLatidute(), stanok.getLongitude(), null, null );
                    }
                }
            }

            if (indexNajlacnejsi >= 0){
                ImageView marker = stankyMarkre.get(indexNajlacnejsi);
                marker.setImageResource(R.drawable.map_marker_featured );
                StanokResponseEntityModel stanok = stanky.get(indexNajlacnejsi);
                frameTo(stanok.getLongitude(), stanok.getLatidute());
            }
        }
    }

    private void showAllMarks() {
        addBackStanky();
        addBackPodia();
        addBackOstatneMiesta();
    }

    private void addBackStanky() {
        TileView tile = getTileView();
        for (int index = 0; index < stankyMarkre.size(); index++){
            StanokResponseEntityModel stanok = stanky.get(index);
            View marker = stankyMarkre.get(index);
            tile.addMarker( marker, stanok.getLatidute(), stanok.getLongitude(), null, null );
        }
    }

    private void addBackPodia() {
        TileView tile = getTileView();
        for (int index = 0; index < podiaMarkre.size(); index++){
            PodiumResponseEntityModel podium = podia.get(index);
            View marker = podiaMarkre.get(index);
            tile.addMarker( marker, podium.getLatidute(), podium.getLongitude(), null, null );
        }
    }

    private void addBackOstatneMiesta() {
        TileView tile = getTileView();
        for (int index = 0; index < ostatneBudovyMarkre.size(); index++){
            OstatneMiestaResponseEntityModel ostatneMiesto = ostatneMiesta.get(index);
            View marker = ostatneBudovyMarkre.get(index);
            tile.addMarker( marker, ostatneMiesto.getLatidute(), ostatneMiesto.getLongitude(), null, null );
        }
    }

    private void hideAllMarks() {
        removeMarkers(ostatneBudovyMarkre);
        removeMarkers(stankyMarkre);
        removeMarkers(podiaMarkre);
    }

    private void removeMarkers(ArrayList<ImageView> listMarkers) {
        TileView tile = getTileView();
        for (View view: listMarkers){
            tile.removeMarker(view);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }else if (user == null){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 1, locationListener);
            createUserMarker(getTileView());
        }
    }

    public void succesDownload(PodujatieDetailResponseEntityModel result) {
        this.data = result;
        NORTH_WEST_LATITUDE = result.getMapa().getLavyHornyRohGpsLatitude();
        NORTH_WEST_LONGITUDE = result.getMapa().getLavyHornyRohGpsLongitude();
        SOUTH_EAST_LATITUDE = result.getMapa().getPravySpodnyRohGpsLatitude();
        SOUTH_EAST_LONGITUDE = result.getMapa().getPravySpodnyRohGpsLongitude();
        this.stanky = result.getStanky().getStankyList();
        this.podia = result.getPodia().getPodiaList();
        this.ostatneMiesta = result.getOstatneMiesta().getOstatneMiestaList();
        this.stanokAndTovar = sortTovarAndStoreToDb(result.getTovar().getTovarList());
        this.podiumAndVystupenie = sortVystupenieAndStoreToDb(result.getVystupenia().getVystupeniaList());
        setTovarForInput();
        showProgress(false);
    }

    public void failedDownload() {
        super.failedDownload();
        Toast.makeText(this, "Failed download Data", Toast.LENGTH_LONG).show();
    }

    private void setTovarForInput() {
        HashSet<String> allTovar = new HashSet<String>();

        for (TovarResponseEntityModel tovar: data.getTovar().getTovarList()){
            allTovar.add(tovar.getNazov());
        }
        String[] array = allTovar.toArray(new String[]{});
        input.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, array));
    }

    private HashMap<Long,List<VystupenieResponseEntityModel>> sortVystupenieAndStoreToDb(List<VystupenieResponseEntityModel> vystupeniaList) {
        VystupenieDBHelper dbVystupenie = new VystupenieDBHelper(this);
        HashMap<Long,List<VystupenieResponseEntityModel>> mapa = new HashMap<Long,List<VystupenieResponseEntityModel>>();
        for (VystupenieResponseEntityModel vystupenie : vystupeniaList){
            List<VystupenieResponseEntityModel> vystupeniaPodia = mapa.get(vystupenie.getPodiumId());
            if (vystupeniaPodia == null){
                vystupeniaPodia = new ArrayList<VystupenieResponseEntityModel>();
                mapa.put(vystupenie.getPodiumId(), vystupeniaPodia);
            }
            vystupeniaPodia.add(vystupenie);
            dbVystupenie.insertVystupenie(vystupenie);
        }
        return mapa;
    }

    private HashMap<Long,List<TovarResponseEntityModel>> sortTovarAndStoreToDb(List<TovarResponseEntityModel> tovarList) {
        TovarDBHelper dbTovar = new TovarDBHelper(this);
        HashMap<Long,List<TovarResponseEntityModel>> mapa = new HashMap<Long,List<TovarResponseEntityModel>>();
        for (TovarResponseEntityModel tovar : tovarList){
            List<TovarResponseEntityModel> tovarStanku = mapa.get(tovar.getStanokId());
            if (tovarStanku == null){
                tovarStanku = new ArrayList<TovarResponseEntityModel>();
                mapa.put(tovar.getStanokId(), tovarStanku);
            }
            tovarStanku.add(tovar);
            dbTovar.insertTovar(tovar);
        }
        return mapa;
    }

    public void succesDownloadMap(Bitmap result) {
        System.out.println(data);
        this.idMapa = data.getMapa().getId();


        SplittedImage obrazky = SplitCutterImage.splitImage(result, idPodujatie);

        for (Map.Entry<String, Bitmap> nazovAndObrazok : obrazky.getImages().entrySet()){
            FileOutputStream out = null;
            try {
                out = openFileOutput(nazovAndObrazok.getKey(), this.MODE_PRIVATE);
                nazovAndObrazok.getValue().compress(Bitmap.CompressFormat.PNG, 100, out);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        getMojeMiesta();

        TileView tileView = getTileView();
        tileView.setBitmapProvider(new BitmapProviderLocalFiles(keyMapy));

        // size and geolocation
        tileView.setSize(obrazky.getNewWidth(), obrazky.getNewHeight());
        tileView.addDetailLevel( 1.0000f, "http://iba_koli_volaniu_url");
        //tileView.addDetailLevel( 1.0000f, this.getFilesDir().getAbsolutePath() + "/" + "1Mapa-%d-%d.jpeg" );

        // markers should align to the coordinate along the horizontal center and vertical bottom
        tileView.setMarkerAnchorPoints( -0.5f, -1.0f );

        // provide the corner coordinates for relative positioning
        tileView.defineBounds(
                NORTH_WEST_LONGITUDE,
                NORTH_WEST_LATITUDE,
                SOUTH_EAST_LONGITUDE,
                SOUTH_EAST_LATITUDE
        );

        this.locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        this.locationListener = new MyLocationListener(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Settings.Secure.ALLOW_MOCK_LOCATION},
                    1);
            return;
        }else{
            System.out.println("Creating user");
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);
            createUserMarker(tileView);
        }

        createStankyMarkers(tileView);
        createOstatneBudovyMarkers(tileView);
        createPodieMarkers(tileView);
        createMojeMiestaMarkers(tileView);
        tileView.getMarkerLayout().setMarkerTapListener(mapListener);

        frameTo( (NORTH_WEST_LATITUDE + SOUTH_EAST_LATITUDE) / 2, (NORTH_WEST_LATITUDE + SOUTH_EAST_LATITUDE) / 2);
        tileView.setScale( 0.5f );
        tileView.setShouldRenderWhilePanning( true );
        tileView.setTransitionsEnabled( false );


        FloatingActionButton button = new FloatingActionButton(this);
        button.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_menu_send));
    }



    private void getMojeMiesta() {
        this.mojeMiesta = mojeMiestaDbHelper.getAllMojeMiesto(this.idPodujatie);
    }

    private void createUserMarker(TileView tileView){
        ImageView marker = new ImageView( this );

        marker.setTag(new Pair<CallOutType, Long>(CallOutType.USER, 1l));

        marker.setImageResource(R.drawable.map_marker_user);

        tileView.addMarker( marker, userLatitude, userLongitude, null, null );
        user = marker;
    }

    private void createStankyMarkers(TileView tileView) {
        for( StanokResponseEntityModel stanok : stanky ) {
            ImageView marker = new ImageView( this );

            marker.setTag(new Pair<CallOutType, Long>(CallOutType.STANOK, stanok.getId()));

            marker.setImageResource(R.drawable.map_marker_normal );

            tileView.addMarker( marker, stanok.getLatidute(), stanok.getLongitude(), null, null );
            stankyMarkre.add(marker);
        }
    }

    private void createPodieMarkers(TileView tileView) {
        for( PodiumResponseEntityModel podium : podia ) {
            ImageView marker = new ImageView( this );

            marker.setTag(new Pair<CallOutType, Long>(CallOutType.PODIUM, podium.getId()));

            marker.setImageResource(R.drawable.map_marker_normal );


            tileView.addMarker( marker, podium.getLatidute(), podium.getLongitude(), null, null );
            podiaMarkre.add(marker);
        }
    }

    private void createOstatneBudovyMarkers(TileView tileView) {
        for( OstatneMiestaResponseEntityModel ostatneMiesto : ostatneMiesta ) {
            ImageView marker = new ImageView( this );

            marker.setTag(new Pair<CallOutType, Long>(CallOutType.OSTATNE_BUDOVY, ostatneMiesto.getId()));

            marker.setImageResource(R.drawable.map_marker_normal);


            tileView.addMarker( marker, ostatneMiesto.getLatidute(), ostatneMiesto.getLongitude(), null, null );
            ostatneBudovyMarkre.add(marker);
        }
    }

    private void createMojeMiestaMarkers(TileView tileView) {
        for( MyPoint mojeMiesto : mojeMiesta ) {
            createMojeMiestoMarker(tileView, mojeMiesto);
        }
    }

    private void addMojeMiestoMarker(MyPoint myPoint) {
        mojeMiesta.add(myPoint);
        createMojeMiestoMarker(getTileView(), myPoint);
    }

    public void removeMojeMiesto(Long id){
        for (int index = 0; index < mojeMiestaMarkre.size(); index++){
            ImageView marker = mojeMiestaMarkre.get(index);
            Long markerId = ((Pair<CallOutType, Long>) marker.getTag()).second;
            if (id == markerId){
                getTileView().removeMarker(marker);
                mojeMiestaMarkre.remove(index);
                break;
            }
        }
        for (int index = 0; index < mojeMiesta.size(); index++ ){
            MyPoint mojeMiesto = mojeMiesta.get(index);
            if (mojeMiesto.getId() == id){
                mojeMiestaDbHelper.deleteMojeMiesto(mojeMiesto);
                break;
            }
        }
    }

    private void createMojeMiestoMarker(TileView tileView, MyPoint mojeMiesto) {
        ImageView marker = new ImageView( this );

        marker.setTag(new Pair<CallOutType, Long>(CallOutType.MOJE_MIESTO, mojeMiesto.getId()));

        marker.setImageResource(R.drawable.map_marker_special );


        tileView.addMarker( marker, mojeMiesto.getLatitude(), mojeMiesto.getLongitude(), null, null );
        mojeMiestaMarkre.add(marker);
    }

    private MarkerLayout.MarkerTapListener mapListener = new MarkerLayout.MarkerTapListener() {

        @Override
        public void onMarkerTap( View view, int x, int y ) {
            TileView tileView = getTileView();
            Pair<CallOutType, Long> typeAndId = (Pair<CallOutType, Long>) view.getTag();

            switch(typeAndId.first){
                case STANOK:
                    for (StanokResponseEntityModel stanok: stanky) {
                        if (stanok.getId() == typeAndId.second) {
                            tileView.slideToAndCenter(stanok.getLatidute(), stanok.getLongitude());
                            SampleCalloutDetail calloutStanok = new SampleCalloutDetail(view.getContext(), typeAndId.first);
                            tileView.addCallout(calloutStanok, stanok.getLatidute(), stanok.getLongitude(), -0.5f, -1.0f);
                            calloutStanok.transitionIn();
                            calloutStanok.setTitle("Stánok");
                            calloutStanok.setSubtitle(stanok.getNazov());
                            calloutStanok.setBudovaId(stanok.getId());
                        }
                    }
                    break;
                case OSTATNE_BUDOVY:
                    for (OstatneMiestaResponseEntityModel ostatneMiesto: ostatneMiesta) {
                        if (ostatneMiesto.getId() == typeAndId.second) {
                            tileView.slideToAndCenter(ostatneMiesto.getLatidute(), ostatneMiesto.getLongitude());
                            SampleCallout calloutOstatnaBudova = new SampleCallout(view.getContext());
                            tileView.addCallout(calloutOstatnaBudova, ostatneMiesto.getLatidute(), ostatneMiesto.getLongitude(), -0.5f, -1.0f);
                            calloutOstatnaBudova.transitionIn();
                            calloutOstatnaBudova.setTitle(ostatneMiesto.getNazov());
                            calloutOstatnaBudova.setSubtitle(ostatneMiesto.getDetail());
                        }
                    }
                    break;
                case PODIUM:
                    for (PodiumResponseEntityModel podium: podia) {
                        if (podium.getId() == typeAndId.second) {
                            tileView.slideToAndCenter(podium.getLatidute(), podium.getLongitude());
                            SampleCalloutDetail calloutPodium = new SampleCalloutDetail(view.getContext(), typeAndId.first);
                            tileView.addCallout(calloutPodium, podium.getLatidute(), podium.getLongitude(), -0.5f, -1.0f);
                            calloutPodium.transitionIn();
                            calloutPodium.setTitle("Pódium");
                            calloutPodium.setSubtitle(podium.getNazov());
                            calloutPodium.setBudovaId(podium.getId());
                            calloutPodium.setPodujatieId(idPodujatie);
                        }
                    }
                    break;
                case USER:
                    tileView.slideToAndCenter(userLatitude, userLongitude);
                    SampleCallout calloutUser = new SampleCallout(view.getContext());
                    tileView.addCallout(calloutUser, userLatitude, userLongitude, -0.5f, -1.0f);
                    calloutUser.transitionIn();
                    calloutUser.setTitle("Tu sa nachádzate");

                    break;
                case MOJE_MIESTO:
                    for (MyPoint mojeMiesto: mojeMiesta) {
                        if (mojeMiesto.getId() == typeAndId.second) {
                            tileView.slideToAndCenter(mojeMiesto.getLatitude(), mojeMiesto.getLongitude());
                            SampleCalloutDetail calloutMojeMiesto = new SampleCalloutDetail(view.getContext(), typeAndId.first);
                            tileView.addCallout(calloutMojeMiesto, mojeMiesto.getLatitude(), mojeMiesto.getLongitude(), -0.5f, -1.0f);
                            calloutMojeMiesto.transitionIn();
                            calloutMojeMiesto.setTitle("Moje Miesto");
                            calloutMojeMiesto.setSubtitle(mojeMiesto.getText());
                            calloutMojeMiesto.setId(mojeMiesto.getId());
                            calloutMojeMiesto.setMap(MapTileViewActivity.this);
                        }
                    }
                    break;
                default:
                    break;
            }

        }
    };
}

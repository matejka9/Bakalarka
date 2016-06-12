package com.dusky.festival.activity.base.map;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dusky.festival.R;
import com.dusky.festival.activity.base.map.callout.SampleCalloutDetail;
import com.dusky.festival.activity.base.map.provider.BitmapProviderLocalFiles;
import com.dusky.festival.database.TovarDBHelper;
import com.dusky.festival.database.VystupenieDBHelper;
import com.dusky.festival.gae.connection.MapaAsyncTask;
import com.dusky.festival.gps.MyLocationListener;
import com.dusky.festival.helper.image.SplitCutterImage;
import com.dusky.festival.helper.image.SplittedImage;
import com.dusky.festival.helper.ui.HideKeyboardHelper;
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
    private String keyMapy;

    private PodujatieDetailResponseEntityModel data;
    private List<StanokResponseEntityModel> stanky;
    private List<PodiumResponseEntityModel> podia;
    private List<OstatneMiestaResponseEntityModel> ostatneMiesta;
    private List<String> vlastneMiesta;//TODO nacitaj z databazy

    private HashMap<Long, List<TovarResponseEntityModel>> stanokAndTovar;
    private HashMap<Long, List<VystupenieResponseEntityModel>> podiumAndVystupenie;

    private ArrayList<ImageView> ostatneBudovyMarkre;
    private ArrayList<ImageView> stankyMarkre;
    private ArrayList<ImageView> podiaMarkre;
    private ArrayList<ImageView> vlastneMiestaMarkre;//TODO vytvor markre pre nacitane

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
    }

    public enum CallOutType {
        //TODO pridaj moje miesto
        STANOK, PODIUM, OSTATNE_BUDOVY, USER
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.idPodujatie = extras.getLong("id");
            this.keyMapy = SplitCutterImage.getKeyForMap(idPodujatie);
        }

        ostatneBudovyMarkre = new ArrayList<ImageView>();
        stankyMarkre = new ArrayList<ImageView>();
        podiaMarkre = new ArrayList<ImageView>();

        showProgress(true);
        new MapaAsyncTask().execute(new Pair<MapTileViewActivity, Long>(this, idPodujatie));
    }

    @Override
    protected void pouzilNotifikacie() {
        System.out.println("Pouzil notifikacie");
    }

    @Override
    protected void pouzilPoziciu() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nová poloha");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO pridaj novu polohu
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
        //Toast.makeText(this, "Succesfull download Mapa", Toast.LENGTH_LONG).show();
        System.out.println(data);


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
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 1, locationListener);
            createUserMarker(tileView);
        }

        createStankyMarkers(tileView);
        createOstatneBudovyMarkers(tileView);
        createPodieMarkers(tileView);
        tileView.getMarkerLayout().setMarkerTapListener(mapListener);

        frameTo( (NORTH_WEST_LATITUDE + SOUTH_EAST_LATITUDE) / 2, (NORTH_WEST_LATITUDE + SOUTH_EAST_LATITUDE) / 2);
        tileView.setScale( 0.5f );
        tileView.setShouldRenderWhilePanning( true );
        tileView.setTransitionsEnabled( false );


        FloatingActionButton button = new FloatingActionButton(this);
        button.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_menu_send));
    }

    private void createUserMarker(TileView tileView){
        ImageView marker = new ImageView( this );

        marker.setTag(new Pair<CallOutType, Long>(CallOutType.USER, 1l));

        marker.setImageResource(R.drawable.unnamed );

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
                            SampleCalloutDetail callout = new SampleCalloutDetail(view.getContext(), typeAndId.first);
                            tileView.addCallout(callout, stanok.getLatidute(), stanok.getLongitude(), -0.5f, -1.0f);
                            callout.transitionIn();
                            callout.setTitle("Stánok");
                            callout.setSubtitle(stanok.getNazov());
                            callout.setBudovaId(stanok.getId());
                        }
                    }
                    break;
                case OSTATNE_BUDOVY:
                    for (OstatneMiestaResponseEntityModel ostatneMiesto: ostatneMiesta) {
                        if (ostatneMiesto.getId() == typeAndId.second) {
                            tileView.slideToAndCenter(ostatneMiesto.getLatidute(), ostatneMiesto.getLongitude());
                            com.dusky.festival.activity.base.map.SampleCallout callout = new com.dusky.festival.activity.base.map.SampleCallout(view.getContext());
                            tileView.addCallout(callout, ostatneMiesto.getLatidute(), ostatneMiesto.getLongitude(), -0.5f, -1.0f);
                            callout.transitionIn();
                            callout.setTitle(ostatneMiesto.getNazov());
                            callout.setSubtitle(ostatneMiesto.getDetail());
                        }
                    }
                    break;
                case PODIUM:
                    for (PodiumResponseEntityModel podium: podia) {
                        if (podium.getId() == typeAndId.second) {
                            tileView.slideToAndCenter(podium.getLatidute(), podium.getLongitude());
                            SampleCalloutDetail callout = new SampleCalloutDetail(view.getContext(), typeAndId.first);
                            tileView.addCallout(callout, podium.getLatidute(), podium.getLongitude(), -0.5f, -1.0f);
                            callout.transitionIn();
                            callout.setTitle("Pódium");
                            callout.setSubtitle(podium.getNazov());
                            callout.setBudovaId(podium.getId());
                            callout.setPodujatieId(idPodujatie);
                        }
                    }
                    break;
                case USER:
                    tileView.slideToAndCenter(userLatitude, userLongitude);
                    com.dusky.festival.activity.base.map.SampleCallout callout = new com.dusky.festival.activity.base.map.SampleCallout(view.getContext());
                    tileView.addCallout(callout, userLatitude, userLongitude, -0.5f, -1.0f);
                    callout.transitionIn();
                    callout.setTitle("Tu ste vy");

                    break;
                //TODO uhendluj moje miesto
                default:
                    break;
            }

        }
    };
}

package com.dusky.festival.activity.base.map;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dusky.festival.R;
import com.dusky.festival.helper.ui.HideKeyboardHelper;
import com.qozix.tileview.TileView;

/**
 * Created by dusky on 5/29/16.
 */
public abstract class TileViewActivity extends Activity{
    private TileView tileView;
    private FloatingActionButton notifikacieButton;
    private FloatingActionButton locationButton;
    private View mProgressView;

    protected AutoCompleteTextView input;
    protected RelativeLayout lay;


    protected void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {

            setVisibility(show, tileView);
            setVisibility(show, input);
            setVisibility(show, notifikacieButton);
            setVisibility(show, locationButton);
            setVisibility(!show, mProgressView);
        } else {
            tileView.setVisibility(show ? View.VISIBLE : View.GONE);
            input.setVisibility(show ? View.VISIBLE : View.GONE);
            notifikacieButton.setVisibility(show ? View.VISIBLE : View.GONE);
            locationButton.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    protected void failedDownload(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            setVisibility(false, mProgressView);
        }else {
            mProgressView.setVisibility(View.GONE);
        }
    }

    private void setVisibility(final boolean show, final View view) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
        view.setVisibility(show ? View.GONE : View.VISIBLE);
        view.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        tileView = new TileView( this );

        /**
         * Set an id. This is necessary to enable the save state mechanism of Android.
         * It is retrieved from a resource value, but it can also be generated with
         * {@code View.generateViewId()}.
         */
        tileView.setId( R.id.tileview_id );
        tileView.setSaveEnabled( true );

        lay = new RelativeLayout(this);
        lay.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));
        lay.setGravity(Gravity.CENTER);

        LayoutInflater inflater = getLayoutInflater();
        mProgressView = (ProgressBar ) inflater.inflate(R.layout.small_progress_bar, null);


        notifikacieButton = new FloatingActionButton(this);
        notifikacieButton.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_menu_send));

        RelativeLayout.LayoutParams layoutButton = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutButton.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutButton.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutButton.setMargins(10, 10, 10, 10);

        notifikacieButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pouzilNotifikacie();
            }
        });

        notifikacieButton.setLayoutParams(layoutButton);
        notifikacieButton.bringToFront();

        locationButton = new FloatingActionButton(this);
        locationButton.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_menu_send));

        RelativeLayout.LayoutParams layoutButton2 = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutButton2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutButton2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutButton2.setMargins(10, 10, 10, 10);

        locationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pouzilPoziciu();
            }
        });

        locationButton.setLayoutParams(layoutButton2);
        locationButton.bringToFront();


        this.input = new AutoCompleteTextView(this);
        input.setPaintFlags(input.getPaintFlags());
        ViewGroup.LayoutParams layout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        input.setLayoutParams(layout);


        String[] array={};
        input.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, array));

        TextView.OnEditorActionListener exampleListener = new TextView.OnEditorActionListener(){
            public boolean onEditorAction(TextView exampleView, int actionId, KeyEvent event) {
                if (actionId == R.id.hladaj || actionId == EditorInfo.IME_NULL || actionId == EditorInfo.IME_ACTION_DONE) {
                    pouzilInput();
                    input.clearFocus();
                    return true;
                }
                return false;
            }
        };

        input.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        input.setOnEditorActionListener(exampleListener);
        input.setImeActionLabel("Hľadaj", R.id.hladaj);
        input.setSingleLine();


        lay.addView(tileView);
        lay.addView(notifikacieButton);
        lay.addView(locationButton);
        lay.addView(input);
        lay.addView(mProgressView);

        lay.setFocusable(true);
        lay.setFocusableInTouchMode(true);

        setContentView( lay );
        HideKeyboardHelper.setupUI(lay,this);
    }

    protected abstract void pouzilNotifikacie();

    private void AddButtonLayout(FloatingActionButton button, int centerInParent, int marginLeft, int marginTop, int marginRight, int marginBottom) {
        // Defining the layout parameters of the Button
        RelativeLayout.LayoutParams buttonLayoutParameters = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        // Add Margin to the LayoutParameters
        buttonLayoutParameters.setMargins(marginLeft, marginTop, marginRight, marginBottom);

        // Add Rule to Layout
        buttonLayoutParameters.addRule(centerInParent);

        // Setting the parameters on the Button
        button.setLayoutParams(buttonLayoutParameters);
    }

    private void AddButtonLayout(FloatingActionButton button, int centerInParent) {
        // Just call the other AddButtonLayout Method with Margin 0
        AddButtonLayout(button, centerInParent, 0 ,0 ,0 ,0);
    }

    protected abstract void pouzilPoziciu();

    abstract protected void pouzilInput();

    @Override
    public void onPause() {
        super.onPause();
        tileView.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        tileView.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tileView.destroy();
        tileView = null;
    }

    public TileView getTileView(){
        return tileView;
    }

    /**
     * This is a convenience method to scrollToAndCenter after layout (which won't happen if called directly in onCreate
     * see https://github.com/moagrius/TileView/wiki/FAQ
     */
    public void frameTo( final double x, final double y ) {
        getTileView().post( new Runnable() {
            @Override
            public void run() {
                getTileView().scrollToAndCenter( x, y );
            }
        });
    }
}

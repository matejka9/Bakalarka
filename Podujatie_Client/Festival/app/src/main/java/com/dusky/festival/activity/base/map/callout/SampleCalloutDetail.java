package com.dusky.festival.activity.base.map.callout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dusky.festival.R;
import com.dusky.festival.activity.base.map.MapTileViewActivity;
import com.dusky.festival.activity.detail.PodiumDetailListActivity;
import com.dusky.festival.activity.detail.StanokDetailListActivity;


public class SampleCalloutDetail extends RelativeLayout {

	private TextView title;
	private TextView subtitle;
	private Button detailButton;
	private Long budovaId;
	private Long podujatieId;
	private Long id;
	private MapTileViewActivity map;

	private static int getDIP( Context context, int pixels ) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pixels, context.getResources().getDisplayMetrics() );
	}

	public SampleCalloutDetail(final Context context , MapTileViewActivity.CallOutType stanok) {

		super( context );
		// children
		LinearLayout bubble = new LinearLayout( context );
		GradientDrawable drawable = new GradientDrawable();
		drawable.setColor( 0xFF313231 );
		drawable.setCornerRadius( getDIP( context, 4 ) );
		bubble.setBackground( drawable );
		bubble.setId( R.id.bubble );
		int padding = getDIP( context, 17 );
		bubble.setPadding( padding, padding, padding, padding );
		LayoutParams bubbleLayout = new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
		addView( bubble, bubbleLayout );

		Nub nub = new Nub( context );
		LayoutParams nubLayout = new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
		nubLayout.addRule( RelativeLayout.BELOW, bubble.getId() );
		nubLayout.addRule( RelativeLayout.CENTER_IN_PARENT );
		addView( nub, nubLayout );

		LinearLayout labels = new LinearLayout( context );
		labels.setOrientation( LinearLayout.VERTICAL );
		LayoutParams labelsLayout = new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
		bubble.addView( labels, labelsLayout );

		int maxWidth = getDIP( context, 230 );
		title = new TextView( context );
		title.setTextColor( 0xFFFFFFFF );
		title.setTextSize( TypedValue.COMPLEX_UNIT_SP, 21 );
		title.setMaxWidth( maxWidth );
		LinearLayout.LayoutParams titleLayout = new LinearLayout.LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
		labels.addView( title, titleLayout );

		subtitle = new TextView( context );
		subtitle.setTextColor( 0xFF88afca );
		subtitle.setTypeface( Typeface.SANS_SERIF );
		subtitle.setTextSize( TypedValue.COMPLEX_UNIT_SP, 14 );
		subtitle.setMaxWidth( maxWidth );
		LinearLayout.LayoutParams subtitleLayout = new LinearLayout.LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
		labels.addView( subtitle, subtitleLayout );

		RelativeLayout iconColumn = new RelativeLayout( context );
		LayoutParams iconColumnLayout = new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT );
		bubble.addView( iconColumn, iconColumnLayout );

		labels.setGravity(Gravity.CENTER_HORIZONTAL);

		switch (stanok){
			case STANOK:
				detailButton = new Button(context);
				detailButton.setText(R.string.detail);
				detailButton.setLayoutParams(new LayoutParams(
						ViewGroup.LayoutParams.WRAP_CONTENT,
						ViewGroup.LayoutParams.WRAP_CONTENT));

				OnClickListener buttonListener = new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent i = new Intent(context, StanokDetailListActivity.class);
						i.putExtra("stanokId", budovaId);
						i.putExtra("stanokNazov", subtitle.getText());
						context.startActivity(i);
					}
				};
				detailButton.setOnClickListener(buttonListener);

				labels.addView(detailButton);
				break;
			case PODIUM:
				detailButton = new Button(context);
				detailButton.setText(R.string.detail);
				detailButton.setLayoutParams(new LayoutParams(
						ViewGroup.LayoutParams.WRAP_CONTENT,
						ViewGroup.LayoutParams.WRAP_CONTENT));

				buttonListener = new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent i = new Intent(context, PodiumDetailListActivity.class);
						i.putExtra("podiumId", budovaId);
						i.putExtra("poduajtieId", podujatieId);
						i.putExtra("podiumNazov", subtitle.getText());
						context.startActivity(i);
					}
				};
				detailButton.setOnClickListener(buttonListener);

				labels.addView(detailButton);
				break;
			case MOJE_MIESTO:
				detailButton = new Button(context);
				detailButton.setText(R.string.zmaz);
				detailButton.setLayoutParams(new LayoutParams(
						ViewGroup.LayoutParams.WRAP_CONTENT,
						ViewGroup.LayoutParams.WRAP_CONTENT));

				buttonListener = new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						map.removeMojeMiesto(id);
						setVisibility(GONE);
					}
				};
				detailButton.setOnClickListener(buttonListener);
				labels.addView(detailButton);
				break;
			default:
				break;
		}


	}

	public void setTitle( CharSequence text ) {
		title.setText( text );
	}

	public void setSubtitle( CharSequence text ) {
		subtitle.setText( text );
	}

	public void setBudovaId(Long budovaId) {
		this.budovaId = budovaId;
	}

	public void setMap(MapTileViewActivity map) {
		this.map = map;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void transitionIn() {

		ScaleAnimation scaleAnimation = new ScaleAnimation( 0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1f );
		scaleAnimation.setInterpolator( new OvershootInterpolator( 1.2f ) );
		scaleAnimation.setDuration( 250 );

		AlphaAnimation alphaAnimation = new AlphaAnimation( 0, 1f );
		alphaAnimation.setDuration( 200 );

		AnimationSet animationSet = new AnimationSet( false );

		animationSet.addAnimation( scaleAnimation );
		animationSet.addAnimation( alphaAnimation );

		startAnimation( animationSet );

	}

	public void setPodujatieId(Long podujatieId) {
		this.podujatieId = podujatieId;
	}


	private static class Nub extends View {

		private Paint paint = new Paint();
		private Path path = new Path();

		public Nub( Context context ) {

			super( context );

			paint.setStyle( Paint.Style.FILL );
			paint.setColor( 0xFF313231 );
			paint.setAntiAlias( true );

			path.lineTo( getDIP( context, 20 ), 0 );
			path.lineTo( getDIP( context, 10 ), getDIP( context, 15 ) );
			path.close();

		}

		@Override
		protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec ) {
			setMeasuredDimension( getDIP( getContext(), 20 ), getDIP( getContext(), 15 ) );
		}

		@Override
		public void onDraw( Canvas canvas ) {
			canvas.drawPath( path, paint );
			super.onDraw( canvas );
		}
	}

}
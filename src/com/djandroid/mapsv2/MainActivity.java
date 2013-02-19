package com.djandroid.mapsv2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends FragmentActivity {

	private GoogleMap MAP;
	private boolean markClick;
	private PolylineOptions rectoptions;
	private Polyline poliline;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		// setTheme(R.style.Theme_Sherlock);
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);

		FragmentManager myFM = getSupportFragmentManager();
		SupportMapFragment myMAPF = (SupportMapFragment) myFM
				.findFragmentById(R.id.fragment1);

		MAP = myMAPF.getMap();
		MAP.setMyLocationEnabled(true);

		MAP.setMapType(GoogleMap.MAP_TYPE_HYBRID);

		MAP.setOnMapClickListener(new OnMapClickListener() {

			@Override
			public void onMapClick(LatLng point) {
				// TODO Auto-generated method stub
				MAP.addMarker(new MarkerOptions().position(point).title(
						point.toString()));
			}
		});

		MAP.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker marker) {
				// TODO Auto-generated method stub
				if (markClick) {
					if (poliline != null) {
						poliline.remove();
						poliline = null;
					}
					rectoptions.add(marker.getPosition());
					rectoptions.color(Color.BLUE);
					poliline = MAP.addPolyline(rectoptions);
				} else {
					if (poliline != null) {
						poliline.remove();
						poliline = null;
					}
					rectoptions = new PolylineOptions().add(marker
							.getPosition());
					markClick = true;
				}
				return false;
			}
		});

		markClick = false;

	}
}

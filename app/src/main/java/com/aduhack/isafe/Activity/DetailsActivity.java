package com.aduhack.isafe.Activity;

import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aduhack.isafe.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

public class DetailsActivity extends ActionBarActivity {
    private String incident, details, location;

    private TextView tv_incident, tv_details;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    //private JSONParser jsonParser;
    Location locationNow;
    private double selflat;
    private double selflong;
    private LocationManager mLocationManager;

    private ImageButton searchButton, listButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        incident = getIntent().getStringExtra("incidentType");
        details = getIntent().getStringExtra("details");
        location = getIntent().getStringExtra("location");


        tv_incident = (TextView) findViewById(R.id.tv_incident);
        tv_details = (TextView) findViewById(R.id.tv_details);
        tv_incident.setText(incident);
        tv_details.setText(details);

        setUpMapIfNeeded();

    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {

        mMap.getUiSettings().setMapToolbarEnabled(false);

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            // Use default InfoWindow frame
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            // Defines the contents of the InfoWindow
            @Override
            public View getInfoContents(final Marker arg0) {

                // Getting view from the layout file info_window_layout
                View v = getLayoutInflater().inflate(R.layout.custom_map_info_layout, null);

                // Getting the position from the marker
                LatLng latLng = arg0.getPosition();


                TextView tv_name = (TextView) v.findViewById(R.id.tv_name);


                //TODO: SET DISTANCE and ADD NEAREST
                // Setting the latitude
                tv_name.setText(arg0.getTitle());
                return v;

            }
        });

        // Adding and showing marker while touching the GoogleMap
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng arg0) {
               /* // Clears any existing markers from the GoogleMap
                mMap.clear();

                // Creating an instance of MarkerOptions to set position
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting position on the MarkerOptions
                markerOptions.position(arg0);
                markerOptions.title(arg0.longitude + "-" + arg0.latitude);
                location = arg0.longitude + "-" + arg0.latitude;
                // Animating to the currently touched position
                mMap.animateCamera(CameraUpdateFactory.newLatLng(arg0));

                // Adding marker on the GoogleMap
                Marker marker = mMap.addMarker(markerOptions);

                // Showing InfoWindow on the GoogleMap
                marker.showInfoWindow();*/
            }
        });

        String[] geoResto = location.split(",");

        if(location.length() > 0) {
            double lat1 = Double.parseDouble(geoResto[0]);
            double lng1 = Double.parseDouble(geoResto[1]);


            LatLng latLng = new LatLng(lat1, lng1);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 2);
            mMap.animateCamera(cameraUpdate);


        // Clears any existing markers from the GoogleMap
        mMap.clear();

        // Creating an instance of MarkerOptions to set position
        MarkerOptions markerOptions = new MarkerOptions();

        // Setting position on the MarkerOptions
        markerOptions.position(latLng);
        markerOptions.title(incident);
        // Animating to the currently touched position
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        // Adding marker on the GoogleMap
        Marker marker = mMap.addMarker(markerOptions);

        // Showing InfoWindow on the GoogleMap
        marker.showInfoWindow();
        }
    }

    public class MarkerItem {
        String Title;
        String Description;
        LatLng Coordinates;
    }

}

package com.aduhack.isafe.Activity;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;

public class SetLocationActivity extends ActionBarActivity {
    Button button_cancel, button_save;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    //private JSONParser jsonParser;
    Location locationNow;
    private double selflat;
    private double selflong;
    private LocationManager mLocationManager;

    private ImageButton searchButton, listButton;

    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);
        button_cancel = (Button)findViewById(R.id.button_cancel);
        button_save = (Button)findViewById(R.id.button_save);

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("location", location);
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });


        //--------maps----------//

        setUpMapIfNeeded();

        mMap.setMyLocationEnabled(true);
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5,
                0, mLocationListener);

        //todo:calling of data arrangement
        showCurrentLocation();

    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
            mMap.animateCamera(cameraUpdate);

            mLocationManager.removeUpdates(this);
            selflat = location.getLatitude();
            selflong = location.getLongitude();

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    public void showCurrentLocation() {

        if (mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationNow = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        if (locationNow != null) {
            selflat = locationNow.getLatitude();
            selflong = locationNow.getLongitude();
        }
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
                // Clears any existing markers from the GoogleMap
                mMap.clear();

                // Creating an instance of MarkerOptions to set position
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting position on the MarkerOptions
                markerOptions.position(arg0);
                markerOptions.title(arg0.latitude + "," + arg0.longitude);
                location = arg0.latitude + "," + arg0.longitude;
                // Animating to the currently touched position
                mMap.animateCamera(CameraUpdateFactory.newLatLng(arg0));

                // Adding marker on the GoogleMap
                Marker marker = mMap.addMarker(markerOptions);

                // Showing InfoWindow on the GoogleMap
                marker.showInfoWindow();
            }
        });
    }

    public class MarkerItem {
        String Title;
        String Description;
        LatLng Coordinates;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //  getMenuInflater().inflate(R.menu.menu_set_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.example.dimsumdetection;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.dimsumdetection.database.PostgreSQL;
import com.example.dimsumdetection.ui.recipe.Restaurant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.TimeUnit;

public class RestaurantActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private String name;
    private double latitude;
    private double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        Intent intent = getIntent();
        name = intent.getExtras().getString("Name");
        latitude = intent.getExtras().getDouble("Latitude");
        longitude = intent.getExtras().getDouble("Longitude");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng landmark = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(landmark).title(name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(landmark));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
    }
}
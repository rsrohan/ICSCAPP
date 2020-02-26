package com.example.icscapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Location currLocation;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private MarkerOptions location, destination;
    private Polyline poly;
    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        fetchLastLocation();

/*        location = new MarkerOptions().position(new LatLng(28.519232, 77.365269)).title("Current Location");
        destination = new MarkerOptions().position(new LatLng(28.629842, 77.372095)).title("Destination JIIT 62");*/
        //String url = getUrl(location.getPosition(), destination.getPosition(), "driving");


    }

    private void fetchLastLocation() {

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    currLocation = location;
                   //a Toast.makeText(getApplicationContext(),currLocation.getLatitude() + " " + currLocation.getLongitude(),Toast.LENGTH_SHORT).show();


                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(MapsActivity.this);
                }
            }
        });
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
        LatLng latLng = new LatLng(currLocation.getLatitude(),currLocation.getLongitude());
        location = new MarkerOptions().position(latLng).title("Current Location");
        destination = new MarkerOptions().position(new LatLng(28.629842, 77.372095)).title("Destination JIIT 62");
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 30.0f));
        mMap.setMinZoomPreference(11f);
        //mMap.setMaxZoomPreference(30f);
        location.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
       // mMap.addMarker(location);
        mMap.addMarker(destination);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    fetchLastLocation();
                }
                break;
        }
    }

    /*private String getUrl(LatLng position, LatLng destination, String driving) {
        String str_origin = "origin=" + position.latitude + "," + position.longitude;

        String str_destination = "destination=" + destination.latitude + "," + destination.longitude;

        String mode = "mode=" + driving;

        String Parameters = str_origin + "&" + str_destination + "&" + mode;

        String output = "json";

        String URL = "http://maps.googleapis.com/maps/api/directions/" + output + "?" + Parameters + "&key=AIzaSyAzR55l1U30PlIfSX5LB0l6moIXkSqjfXI";
        return  URL;
    }*/


}

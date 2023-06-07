package com.example.gestionempresarial.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gestionempresarial.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {


double lon, lat;
GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Bundle bundle = getIntent().getExtras();
        lat =  bundle.getDouble("lat");
        lon =  bundle.getDouble("lon");

        SupportMapFragment mf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mf.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnMapClickListener(this);
        map.setOnMapLongClickListener(this);

        LatLng ubicacion = new LatLng(lat,lon);
        map.addMarker(new MarkerOptions().position(ubicacion).title("Domicilio del empleado"));
        map.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }
}
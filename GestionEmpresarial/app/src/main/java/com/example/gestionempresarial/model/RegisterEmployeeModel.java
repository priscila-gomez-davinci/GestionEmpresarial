package com.example.gestionempresarial.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;

import com.example.gestionempresarial.databases.DbCreator;
import com.example.gestionempresarial.mvp.view.IRegisterModel;

import java.io.IOException;
import java.util.List;

public class RegisterEmployeeModel implements IRegisterModel {
    public double lat;
    public double lon;

    private SQLiteDatabase database;
    private Context context;



    public RegisterEmployeeModel(Context context) {
        this.context = context;
        DbCreator dbHelper = DbCreator.getInstance(context);
        database = dbHelper.getWritableDatabase();
    }
    public void obtenerCoordenadasDireccion(String direccion, Context context, OnGeocodingResultListener listener) {
        Geocoder geocoder = new Geocoder(context);

        try {
            List<Address> addresses = geocoder.getFromLocationName(direccion, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                lat = address.getLatitude();
                lon = address.getLongitude();
                listener.onGeocodingSuccess(lat, lon);
            } else {
                listener.onGeocodingFailure("No se encontraron resultados para la dirección proporcionada");
            }
        } catch (IOException e) {
            listener.onGeocodingFailure("Error al obtener las coordenadas de la dirección");
            e.printStackTrace();
        }
    }

    public interface OnGeocodingResultListener {
        void onGeocodingSuccess(double latitud, double longitud);
        void onGeocodingFailure(String mensajeError);
    }
}

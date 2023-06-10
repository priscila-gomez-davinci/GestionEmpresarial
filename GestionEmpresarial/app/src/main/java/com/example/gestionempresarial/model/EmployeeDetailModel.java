package com.example.gestionempresarial.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;

import com.example.gestionempresarial.databases.DbCreator;
import com.example.gestionempresarial.mvp.models.IEmployeeDetailModel;
import com.example.gestionempresarial.pojos.Employee;

import java.io.IOException;
import java.util.List;

public class EmployeeDetailModel implements IEmployeeDetailModel {

    private SQLiteDatabase database;
    private Context context;

    DbCreator dbHelper;

    double lat, lon;

    public EmployeeDetailModel(Context context) {
        this.context = context;
        dbHelper= DbCreator.getInstance(context);
        database = dbHelper.getWritableDatabase();
    }

    @Override
    public void updateEmployee(int id, String name, String lastname, String email, String telephone,
                               String filenumber, String street, String number,
                               String city, String country, String lat, String lon) {

        String query = dbHelper.queryUpdateEmployee(id, name, lastname, email, telephone,filenumber, "S", street, number, city, country, lat, lon);
        database.execSQL(query);

    }

    @Override
    public void deleteEmployee(int id) {
        String query = dbHelper.queryDeleteEmployee(id);
        database.execSQL(query);
    }

    public void obtenerCoordenadasDireccion(String direccion, Context context, EmployeeDetailModel.OnGeocodingResultListener listener) {
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

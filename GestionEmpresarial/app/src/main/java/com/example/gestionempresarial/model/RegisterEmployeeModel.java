package com.example.gestionempresarial.model;

import static com.example.gestionempresarial.utils.Constants.EMPLOYEES;
import static com.example.gestionempresarial.utils.Constants.FILE_NUMBER;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import com.example.gestionempresarial.databases.DbCreator;
import com.example.gestionempresarial.mvp.models.IRegisterModel;
import com.example.gestionempresarial.pojos.Employee;
import java.io.IOException;
import java.util.List;

public class RegisterEmployeeModel implements IRegisterModel {
    public double lat;
    public double lon;

    private SQLiteDatabase database;
    private Context context;

    DbCreator dbHelper;



    public RegisterEmployeeModel(Context context) {
        this.context = context;
        dbHelper = DbCreator.getInstance(context);
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

    @Override
    public boolean checkIfEmployeeExists(String legajo) {
        String query = getEmployee(legajo);

        Cursor cursor = database.rawQuery(query, null);

        Employee empleado = null;
        if (cursor.moveToFirst()) {
            do {
                empleado = new Employee();

            } while (cursor.moveToNext());
        }

        cursor.close();

        return empleado != null;
    }

    @Override
    public void insertEmployee(String name, String lastname, String email, String telephone, String filenumber, String street, String number, String city, String country, String lat, String lon) {
       String query = dbHelper.queryInsertEmployee(name, lastname, email, telephone, filenumber, "S", street, number, city, country, lat, lon);
       database.execSQL(query);

    }

    public String getEmployee(String user){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT  * FROM ");
        sb.append(EMPLOYEES);
        sb.append(" WHERE ");
        sb.append(FILE_NUMBER);
        sb.append("= ");
        sb.append("'");
        sb.append(user);
        sb.append("' ");
        return  sb.toString();
    }
}

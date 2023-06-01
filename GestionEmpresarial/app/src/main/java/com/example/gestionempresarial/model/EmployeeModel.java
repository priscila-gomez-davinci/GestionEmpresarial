package com.example.gestionempresarial.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.widget.Toast;

import com.example.gestionempresarial.databases.DbCreator;
import com.example.gestionempresarial.mvp.models.IEmployee;
import com.example.gestionempresarial.pojos.Employee;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class EmployeeModel implements IEmployee, Serializable {
    private SQLiteDatabase database;
    private Context context;

    public EmployeeModel(Context context) {
        this.context = context;
        DbCreator dbHelper = DbCreator.getInstance(context);
        database = dbHelper.getWritableDatabase();
    }
    @Override
    public List<Employee> getAllEmployees() {

        return null;

    }

    @Override
    public void insertEmployee(Employee employee) {
        ContentValues values = new ContentValues();
        values.put("name", employee.getName());
        values.put("lastname", employee.getLastname());
        values.put("email", employee.getEmail());
        values.put("telephone", employee.getTelephone());
        values.put("street", employee.getStreet());
        values.put("number", employee.getNumber());
        values.put("postcode", employee.getPostCode());
        values.put("filenumber", employee.getFileNumber());
        values.put("active", employee.getActive());

        long rowId = database.insert("Employees", null, values);
        if (rowId == -1) {
            // Mostrar un cartel informativo de registro duplicado o error de inserción
            Toast.makeText(context, "El empleado ya existe en la base de datos", Toast.LENGTH_SHORT).show();
        }
    }


    public void obtenerCoordenadasDireccion(String direccion, Context context, OnGeocodingResultListener listener) {
        Geocoder geocoder = new Geocoder(context);

        try {
            List<Address> addresses = geocoder.getFromLocationName(direccion, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                double latitud = address.getLatitude();
                double longitud = address.getLongitude();
                listener.onGeocodingSuccess(latitud, longitud);
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

package com.example.gestionempresarial.mvp.models;

import android.content.Context;

import com.example.gestionempresarial.model.EmployeeDetailModel;

public interface IEmployeeDetailModel {

    void updateEmployee(int id, String name, String lastname, String email, String telephone,
                        String filenumber, String street, String number, String city,
                        String country, String lat, String lon );
    void deleteEmployee(int id);

    void obtenerCoordenadasDireccion(String direccion, Context context, EmployeeDetailModel.OnGeocodingResultListener onGeocodingResultListener);
}

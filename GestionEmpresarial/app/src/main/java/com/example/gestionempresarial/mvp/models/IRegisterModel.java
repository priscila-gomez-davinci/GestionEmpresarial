package com.example.gestionempresarial.mvp.models;


import android.content.Context;

import com.example.gestionempresarial.model.RegisterEmployeeModel;

public interface IRegisterModel {

    void obtenerCoordenadasDireccion(String direccion, Context context, RegisterEmployeeModel.OnGeocodingResultListener onGeocodingResultListener);
    boolean checkIfEmployeeExists(String legajo);

    void insertEmployee(String name, String lastname, String email, String telephone, String filenumber,
                        String street, String number, String city, String country, String lat, String lon);
}

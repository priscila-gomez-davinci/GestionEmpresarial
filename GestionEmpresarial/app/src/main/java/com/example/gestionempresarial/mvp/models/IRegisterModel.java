package com.example.gestionempresarial.mvp.models;


import android.content.Context;

import com.example.gestionempresarial.model.RegisterEmployeeModel;

public interface IRegisterModel {

    void obtenerCoordenadasDireccion(String direccion, Context context, RegisterEmployeeModel.OnGeocodingResultListener onGeocodingResultListener);
}

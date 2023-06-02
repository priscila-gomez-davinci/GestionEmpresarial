package com.example.gestionempresarial.mvp.view;


import android.content.Context;

import com.example.gestionempresarial.model.RegisterEmployeeModel;

public interface IRegisterModel {

    void obtenerCoordenadasDireccion(String direccion, Context context, RegisterEmployeeModel.OnGeocodingResultListener onGeocodingResultListener);
}

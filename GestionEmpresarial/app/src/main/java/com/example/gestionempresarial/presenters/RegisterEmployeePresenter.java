package com.example.gestionempresarial.presenters;

import android.content.Context;

import com.example.gestionempresarial.model.RegisterEmployeeModel;
import com.example.gestionempresarial.mvp.models.IRegisterEmployeeView;
import com.example.gestionempresarial.mvp.view.IRegisterModel;

public class RegisterEmployeePresenter {
    private final IRegisterModel model;
    private final IRegisterEmployeeView view;


    public RegisterEmployeePresenter(IRegisterEmployeeView view, IRegisterModel model) {
        this.view = view;
        this.model = model;
    }

    public void obtenerCoordenadasDireccion(String direccion, Context context) {
        model.obtenerCoordenadasDireccion(direccion, context, new RegisterEmployeeModel.OnGeocodingResultListener() {
            @Override
            public void onGeocodingSuccess(double latitud, double longitud) {
                view.setLatAndLon(latitud, longitud);
            }

            @Override
            public void onGeocodingFailure(String mensajeError) {
                // Manejar el caso de error, por ejemplo, mostrar un mensaje de error al usuario
            }
        });
    }
}

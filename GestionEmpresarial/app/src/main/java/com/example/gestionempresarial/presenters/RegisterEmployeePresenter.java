package com.example.gestionempresarial.presenters;

import android.content.Context;

import com.example.gestionempresarial.model.RegisterEmployeeModel;
import com.example.gestionempresarial.mvp.presenter.IRegisterEmployeePresenter;
import com.example.gestionempresarial.mvp.view.IRegisterEmployeeView;
import com.example.gestionempresarial.mvp.models.IRegisterModel;

public class RegisterEmployeePresenter implements IRegisterEmployeePresenter {
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

    @Override
    public boolean checkIfEmployeeExists(String legajo) {
       return model.checkIfEmployeeExists(legajo);
    }

    @Override
    public void saveEmployee(String name, String lastname, String email, String telephone, String filenumber,String street, String number, String city, String country, String lat, String lon ) {
        model.insertEmployee(name, lastname, email, telephone, filenumber, street, number, city, country, lat, lon);
    }
}

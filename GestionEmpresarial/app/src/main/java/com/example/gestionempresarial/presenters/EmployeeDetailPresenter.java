package com.example.gestionempresarial.presenters;

import android.content.Context;

import com.example.gestionempresarial.model.EmployeeDetailModel;
import com.example.gestionempresarial.mvp.models.IEmployeeDetailModel;
import com.example.gestionempresarial.mvp.presenter.IEmployeeDetailPresenter;
import com.example.gestionempresarial.mvp.view.IEmployeeDetailView;

public class EmployeeDetailPresenter implements IEmployeeDetailPresenter {
    private IEmployeeDetailView view;
    private IEmployeeDetailModel model;

    public EmployeeDetailPresenter(IEmployeeDetailView view, IEmployeeDetailModel model) {
        this.view = view;
        this.model = model;
    }
    @Override
    public void editEmployee(int id, String name, String lastname, String email, String telephone,
                             String filenumber, String street, String number,
                             String city, String country, String lat, String lon) {
        model.updateEmployee(id, name, lastname, email, telephone,
                filenumber, street, number,
                city, country, lat, lon);

    }

    @Override
    public void deleteEmployee(int id) {
        model.deleteEmployee(id);
    }

    public void obtenerCoordenadasDireccion(String direccion, Context context) {
        model.obtenerCoordenadasDireccion(direccion, context, new EmployeeDetailModel.OnGeocodingResultListener() {
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

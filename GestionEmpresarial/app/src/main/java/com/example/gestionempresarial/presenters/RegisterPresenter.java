package com.example.gestionempresarial.presenters;

import android.content.Context;

import com.example.gestionempresarial.model.EmployeeModel;

public class RegisterPresenter {
    private EmployeeModel model;
    private Context context;
    private double lat;
    private double lon;

    public RegisterPresenter(Context context) {
        this.context = context;
        model = new EmployeeModel(context);
    }

    public void obtenerCoordenadasDireccion(String direccion, Context context) {
        model.obtenerCoordenadasDireccion(direccion, context, new EmployeeModel.OnGeocodingResultListener() {
            @Override
            public void onGeocodingSuccess(double latitud, double longitud) {

                lat = latitud;
                lon = longitud;

                // Aquí puedes utilizar las coordenadas (latitud y longitud) como desees,
                // por ejemplo, mostrar un mapa con la ubicación del empleado
                // o guardar las coordenadas en la base de datos del empleado
            }

            @Override
            public void onGeocodingFailure(String mensajeError) {
                // Manejar el caso de error, por ejemplo, mostrar un mensaje de error al usuario
            }
        });
    }
}

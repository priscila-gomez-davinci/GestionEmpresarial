package com.example.gestionempresarial.mvp.view;

public interface IEmployeeDetailView {

    void fillViewDefault();
    void fillViewEditable();
    void retrieveData();

    String direccion(String calle, String altura, String ciudad, String pais);

    void setLatAndLon(double latitud, double longitud);

    boolean validateForm();
}

package com.example.gestionempresarial.mvp.view;

public interface IRegisterEmployeeView {
    void setLatAndLon(double lat, double lon);

    void showRegisterError(String mensaje);
    void showSuccessRegister(String mensaje);
    void showFormError();
    void obtenerDatos();
}


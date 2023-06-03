package com.example.gestionempresarial.mvp.view;

public interface IRegisterUserView {
    void showRegisterError(String mensaje);
    void showSuccessRegister(String mensaje);
    void showFormError();
    void obtenerValidarDatos();
}

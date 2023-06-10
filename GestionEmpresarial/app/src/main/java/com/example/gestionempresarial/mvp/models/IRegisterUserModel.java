package com.example.gestionempresarial.mvp.models;


public interface IRegisterUserModel {
    void generateUser(String nombre, String apellido, String usuario, String password);
    boolean checkIfUserExists(String user);
}

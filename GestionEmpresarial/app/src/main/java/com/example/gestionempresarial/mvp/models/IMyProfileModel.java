package com.example.gestionempresarial.mvp.models;

import com.example.gestionempresarial.pojos.Auth;

public interface IMyProfileModel {

    void updateUser(int id, String nombre, String apellido, String usuario, String password);

    void deleteUser(int id);

    Auth getNewUser(int id);

}

package com.example.gestionempresarial.mvp.presenter;

import com.example.gestionempresarial.pojos.Auth;
import com.example.gestionempresarial.pojos.Employee;

public interface IMyProfilePresenter {

    void editUser(int id, String nombre, String apellido, String usuario, String password);
    void deleteUser(int id);

    Auth getNewUser(int id);

}

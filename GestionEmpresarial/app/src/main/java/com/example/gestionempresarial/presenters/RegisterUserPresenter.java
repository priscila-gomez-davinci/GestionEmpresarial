package com.example.gestionempresarial.presenters;

import com.example.gestionempresarial.mvp.models.IRegisterUserModel;
import com.example.gestionempresarial.mvp.presenter.IRegisterUserPresenter;
import com.example.gestionempresarial.mvp.view.IRegisterUserView;
import com.example.gestionempresarial.pojos.Auth;

public class RegisterUserPresenter implements IRegisterUserPresenter {

    private IRegisterUserView view;
    private IRegisterUserModel model;

    private Auth user = null;


    public RegisterUserPresenter(IRegisterUserView view, IRegisterUserModel model) {
        this.view = view;
        this.model = model;
    }

    public void generateUser(String nombre, String apellido, String usuario, String password) {
        model.generateUser( nombre,  apellido,  usuario, password);
    }

    @Override
    public boolean checkIfUserExists(String user) {
        return model.checkIfUserExists(user);
    }
}

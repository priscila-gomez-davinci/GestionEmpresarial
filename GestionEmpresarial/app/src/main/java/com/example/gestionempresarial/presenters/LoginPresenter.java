package com.example.gestionempresarial.presenters;

import com.example.gestionempresarial.mvp.models.ILoginModel;
import com.example.gestionempresarial.mvp.view.ILoginView;
import com.example.gestionempresarial.pojos.Auth;

public class LoginPresenter {
    private ILoginView view;
    private ILoginModel model;
    private Auth usuario = null;

    public LoginPresenter(ILoginView view, ILoginModel model) {
        this.view = view;
        this.model = model;
    }

    public void loginUser(String username, String password) {
       usuario = model.loginUser(username, password);

       if (usuario == null){
           view.showLoginError();
       }else{
           view.saveUserData(usuario);
           view.showLoginSuccess();
       }
    }
}

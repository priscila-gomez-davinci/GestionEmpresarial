package com.example.gestionempresarial.mvp.view;

import com.example.gestionempresarial.pojos.Auth;

public interface ILoginView {
    void showLoginSuccess();
    void showLoginError();
    void saveUserData(Auth usuario);
}

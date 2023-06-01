package com.example.gestionempresarial.mvp.models;

import com.example.gestionempresarial.pojos.Auth;

public interface ILoginModel {
    Auth loginUser(String username, String password);
}

package com.example.gestionempresarial.presenters;

import com.example.gestionempresarial.mvp.models.IMyProfileModel;
import com.example.gestionempresarial.mvp.presenter.IMyProfilePresenter;
import com.example.gestionempresarial.mvp.view.IMyProfileView;
import com.example.gestionempresarial.pojos.Auth;

public class MyprofilePresenter implements IMyProfilePresenter {

    private IMyProfileView view;
    private IMyProfileModel model;

    private Auth usuario = null;

    public MyprofilePresenter(IMyProfileView view, IMyProfileModel model) {
        this.view = view;
        this.model = model;
    }



}

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


    @Override
    public void editUser(int id, String nombre, String apellido, String usuario, String password) {
            model.updateUser(id,nombre,apellido,usuario,password);

    }

    @Override
    public void deleteUser(int id) {
        model.deleteUser(id);
    }

    @Override
    public Auth getNewUser(int id) {

        return model.getNewUser(id);
    }
}

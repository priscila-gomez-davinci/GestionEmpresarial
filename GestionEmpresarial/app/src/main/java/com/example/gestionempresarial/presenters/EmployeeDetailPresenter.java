package com.example.gestionempresarial.presenters;

import com.example.gestionempresarial.mvp.models.IEmployeeDetailModel;
import com.example.gestionempresarial.mvp.presenter.IEmployeeDetailPresenter;
import com.example.gestionempresarial.mvp.view.IEmployeeDetailView;

public class EmployeeDetailPresenter implements IEmployeeDetailPresenter {
    private IEmployeeDetailView view;
    private IEmployeeDetailModel model;

    public EmployeeDetailPresenter(IEmployeeDetailView view, IEmployeeDetailModel model) {
        this.view = view;
        this.model = model;
    }
    @Override
    public void loadEmployee() {

    }

    @Override
    public void editEmployee() {

    }

    @Override
    public void saveEmployee() {

    }

    @Override
    public void deleteEmployee() {

    }
}

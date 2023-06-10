package com.example.gestionempresarial.mvp.presenter;

import android.content.Context;

import com.example.gestionempresarial.model.RegisterEmployeeModel;

public interface IEmployeeDetailPresenter {
    void loadEmployee();
    void editEmployee(int id, String name, String lastname, String email, String telephone,
                      String filenumber, String street, String number,
                      String city, String country, String lat, String lon);
    void saveEmployee();
    void deleteEmployee(int id);
}

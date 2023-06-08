package com.example.gestionempresarial.mvp.presenter;

public interface IRegisterEmployeePresenter {
    boolean checkIfEmployeeExists(String legajo);
    void saveEmployee(String name, String lastname, String email, String telephone, String filenumber,
                      String street, String number, String city, String country, String lat, String lon);
}

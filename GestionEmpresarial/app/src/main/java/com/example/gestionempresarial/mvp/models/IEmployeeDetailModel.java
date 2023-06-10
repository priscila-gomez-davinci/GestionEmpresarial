package com.example.gestionempresarial.mvp.models;

public interface IEmployeeDetailModel {

    void updateEmployee(int id, String name, String lastname, String email, String telephone,
                        String filenumber, String isActive, String street, String number, String city,
                        String country, String lat, String lon );
    void deleteEmployee(int id);
}

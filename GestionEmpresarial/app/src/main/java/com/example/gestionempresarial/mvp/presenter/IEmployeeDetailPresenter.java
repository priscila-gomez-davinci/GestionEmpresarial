package com.example.gestionempresarial.mvp.presenter;

public interface IEmployeeDetailPresenter {
    void loadEmployee();
    void editEmployee();
    void saveEmployee();
    void deleteEmployee(int id);
}

package com.example.gestionempresarial.mvp.models;

import com.example.gestionempresarial.pojos.Employee;

import java.util.List;

public interface IEmployeeListModel {
    List<Employee> getAllEmployees();
    void insertEmployee(Employee employee);
}

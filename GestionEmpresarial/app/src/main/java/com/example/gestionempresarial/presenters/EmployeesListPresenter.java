package com.example.gestionempresarial.presenters;

import com.example.gestionempresarial.mvp.models.IEmployeeListModel;
import com.example.gestionempresarial.mvp.presenter.IEmployeesListPresenter;
import com.example.gestionempresarial.mvp.view.IEmployeesListView;
import com.example.gestionempresarial.pojos.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeesListPresenter implements IEmployeesListPresenter {
    private IEmployeesListView view;
    private IEmployeeListModel model;
    private List<Employee> employees= new ArrayList<Employee>();

    public EmployeesListPresenter(IEmployeesListView view, IEmployeeListModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void loadEmployees() {
        employees = model.getAllEmployees();
        view.setEmployeesList(employees);
    }
}

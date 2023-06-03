package com.example.gestionempresarial.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.gestionempresarial.databases.DbCreator;
import com.example.gestionempresarial.mvp.models.IEmployeeListModel;
import com.example.gestionempresarial.pojos.Employee;

import java.util.List;

public class EmployeesListModel implements IEmployeeListModel {
    private SQLiteDatabase database;
    private Context context;

    private List<Employee> empleados ;
    DbCreator dbHelper;

    public EmployeesListModel(Context context) {
        this.context = context;
        dbHelper= DbCreator.getInstance(context);
        database = dbHelper.getWritableDatabase();
    }
    @Override
    public List<Employee> getAllEmployees() {
        empleados = dbHelper.getEmployees();
        return empleados;
    }

    @Override
    public void insertEmployee(Employee employee) {

    }
}

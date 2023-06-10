package com.example.gestionempresarial.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.gestionempresarial.databases.DbCreator;
import com.example.gestionempresarial.mvp.models.IEmployeeDetailModel;
import com.example.gestionempresarial.pojos.Employee;

import java.util.List;

public class EmployeeDetailModel implements IEmployeeDetailModel {

    private SQLiteDatabase database;
    private Context context;

    DbCreator dbHelper;

    public EmployeeDetailModel(Context context) {
        this.context = context;
        dbHelper= DbCreator.getInstance(context);
        database = dbHelper.getWritableDatabase();
    }

    @Override
    public void updateEmployee(int id, String name, String lastname, String email, String telephone,
                               String filenumber, String isActive, String street, String number,
                               String city, String country, String lat, String lon) {



    }

    @Override
    public void deleteEmployee(int id) {
        String query = dbHelper.queryDeleteEmployee(id);
        database.execSQL(query);
    }
}

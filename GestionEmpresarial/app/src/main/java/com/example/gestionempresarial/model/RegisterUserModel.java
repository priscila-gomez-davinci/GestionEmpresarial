package com.example.gestionempresarial.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.gestionempresarial.databases.DbCreator;
import com.example.gestionempresarial.mvp.models.IRegisterUserModel;

public class RegisterUserModel implements IRegisterUserModel {
    private SQLiteDatabase database;
    private Context context;
    DbCreator dbHelper;

    public RegisterUserModel(Context context) {
        this.context = context;
        dbHelper = DbCreator.getInstance(context);
        database = dbHelper.getWritableDatabase();
    }

    @Override
    public void generateUser(String nombre, String apellido, String usuario, String password) {
        String query = dbHelper.queryInsertAuth(nombre, apellido,  usuario,  password, "S");
        database.execSQL(query);

    }
}

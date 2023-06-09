package com.example.gestionempresarial.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.gestionempresarial.databases.DbCreator;
import com.example.gestionempresarial.mvp.models.IMyProfileModel;

public class MyProfileModel implements IMyProfileModel {
    private SQLiteDatabase database;
    private Context context;
    DbCreator dbHelper;

    public MyProfileModel(Context context) {
        this.context = context;
        dbHelper = DbCreator.getInstance(context);
        database = dbHelper.getWritableDatabase();
    }

}

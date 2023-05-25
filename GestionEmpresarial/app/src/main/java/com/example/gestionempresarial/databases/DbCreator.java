package com.example.gestionempresarial.databases;

import static com.example.gestionempresarial.utils.Constants.DATABASE_NAME;
import static com.example.gestionempresarial.utils.Constants.DB_VERSION;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbCreator extends SQLiteOpenHelper {


    public DbCreator(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

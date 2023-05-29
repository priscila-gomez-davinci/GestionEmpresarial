package com.example.gestionempresarial.databases;

import static com.example.gestionempresarial.utils.Constants.AUTH;
import static com.example.gestionempresarial.utils.Constants.DATABASE_NAME;
import static com.example.gestionempresarial.utils.Constants.DB_VERSION;
import static com.example.gestionempresarial.utils.Constants.ID;
import static com.example.gestionempresarial.utils.Constants.NAME;
import static com.example.gestionempresarial.utils.Constants.PASSWORD;
import static com.example.gestionempresarial.utils.Constants.USER;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbCreator extends SQLiteOpenHelper {

    private static DbCreator instance;

    public DbCreator(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);

    }
    public static synchronized DbCreator getInstance(Context context) {
        if (instance == null) {
            instance = new DbCreator(context.getApplicationContext());
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryArtefactoCreate = createUserTable();
        sqLiteDatabase.execSQL(queryArtefactoCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    private String createUserTable(){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE auth (");
        sb.append("id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append("name VARCHAR, ");
        sb.append("lastname VARCHAR, ");
        sb.append("user VARCHAR, ");
        sb.append("password VARCHAR");
        sb.append(");");

        return sb.toString();
    }

}

package com.example.gestionempresarial.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gestionempresarial.databases.DbCreator;
import com.example.gestionempresarial.mvp.models.ILoginModel;
import com.example.gestionempresarial.pojos.Auth;

public class LoginModel implements ILoginModel {
    private SQLiteDatabase database;
    private Context context;

    public LoginModel(Context context) {
        this.context = context;
        DbCreator dbHelper = DbCreator.getInstance(context);
        database = dbHelper.getWritableDatabase();
    }

    @Override
    public Auth loginUser(String user, String password) {
  // Realizar la consulta en la base de datos
        String query = "SELECT * FROM auth WHERE user = ? AND password = ?";
        String[] selectionArgs = {user, password};

        Cursor cursor = database.rawQuery(query, selectionArgs);

        Auth usuario = null;
        if (cursor.moveToFirst()) {
            do {
                usuario.setId(cursor.getInt(0));
                usuario.setName(cursor.getString(1));
                usuario.setLastname(cursor.getString(2));
                usuario.setUser(cursor.getString(3));
                usuario.setPassword(cursor.getString(4));
                usuario.setActive(cursor.getString(5));

            } while (cursor.moveToNext());
        }

        cursor.close();
        return usuario;
    }
}

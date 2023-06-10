package com.example.gestionempresarial.model;

import static com.example.gestionempresarial.utils.Constants.AUTH;
import static com.example.gestionempresarial.utils.Constants.USER;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gestionempresarial.databases.DbCreator;
import com.example.gestionempresarial.mvp.models.IMyProfileModel;
import com.example.gestionempresarial.pojos.Auth;

public class MyProfileModel implements IMyProfileModel {
    private SQLiteDatabase database;
    private Context context;
    DbCreator dbHelper;

    public MyProfileModel(Context context) {
        this.context = context;
        dbHelper = DbCreator.getInstance(context);
        database = dbHelper.getWritableDatabase();
    }

    @Override
    public void updateUser(int id, String nombre, String apellido, String usuario, String password) {
        /**Aca llama al método que se encarga de hacer el pudate**/
        String query = dbHelper.queryUpdateAuth(id, nombre, apellido, usuario,  password, "S");
        database.execSQL(query);
    }

    @Override
    public void deleteUser(int id) {
        String query = dbHelper.queryDeleteAuth(id);
        database.execSQL(query);
    }

    @Override
    public Auth getNewUser(int id) {
        String query = dbHelper.getUserById(id);

        Cursor cursor = database.rawQuery(query, null);

        Auth usuario = null;
        if (cursor.moveToFirst()) {
            do {
                usuario = new Auth();
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

package com.example.gestionempresarial.model;

import static com.example.gestionempresarial.utils.Constants.AUTH;
import static com.example.gestionempresarial.utils.Constants.PASSWORD;
import static com.example.gestionempresarial.utils.Constants.USER;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gestionempresarial.databases.DbCreator;
import com.example.gestionempresarial.mvp.models.IRegisterUserModel;
import com.example.gestionempresarial.pojos.Auth;

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

    @Override
    public boolean checkIfUserExists(String user) {
        String query = getUser(user);

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

        return usuario != null;
    }

    public String getUser(String user){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT  * FROM ");
        sb.append(AUTH);
        sb.append(" WHERE ");
        sb.append(USER);
        sb.append("= ");
        sb.append("'");
        sb.append(user);
        sb.append("' ");
        return  sb.toString();

    }
}

package com.example.gestionempresarial.model;

import static com.example.gestionempresarial.utils.Constants.AUTH;
import static com.example.gestionempresarial.utils.Constants.IS_ACTIVE;
import static com.example.gestionempresarial.utils.Constants.PASSWORD;
import static com.example.gestionempresarial.utils.Constants.USER;

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
        String query = getUser(user, password);

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


    public String getUser(String user, String password){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT  * FROM ");
        sb.append(AUTH);
        sb.append(" WHERE ");
        sb.append(USER);
        sb.append("= ");
        sb.append("'");
        sb.append(user);
        sb.append("' ");
        sb.append("AND ");
        sb.append(PASSWORD);
        sb.append("=");
        sb.append("'");
        sb.append(password);
        sb.append("'");
        sb.append(" AND ");
        sb.append(IS_ACTIVE);
        sb.append("=");
        sb.append("'");
        sb.append("S");
        sb.append("'");
        return  sb.toString();

    }
}

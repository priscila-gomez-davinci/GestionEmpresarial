package com.example.gestionempresarial.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestionempresarial.R;
import com.example.gestionempresarial.databases.DbCreator;

public class LoginActivity extends AppCompatActivity {
    public CheckBox recordarCredenciales;
    public String user;
    public String password;

    public ProgressBar progressLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText et_user= findViewById(R.id.user);
        EditText et_pass= findViewById(R.id.password);
        Button btn_login = findViewById(R.id.btn_login);
        TextView tv_registrarse = findViewById(R.id.btn_register);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = et_user.getText().toString();
                password = et_pass.getText().toString();
                if (user.isEmpty()|| password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Por favor ingrese usuario y contraseña", Toast.LENGTH_LONG).show();
                } else {
                    login();
                }
            }
        });

        tv_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });

        fillDatabase();
    }
    public void fillDatabase() {

        DbCreator dbCreator = new DbCreator(this);
        SQLiteDatabase db = dbCreator.getReadableDatabase();


    }
    public void login() {
/*        if(recordarCredenciales.isChecked()){
            SharedPreferences prefs =
                    getSharedPreferences("userdata", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("user", user);
            editor.putString("pass", password);
            editor.apply();
        }*/
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

    }

}
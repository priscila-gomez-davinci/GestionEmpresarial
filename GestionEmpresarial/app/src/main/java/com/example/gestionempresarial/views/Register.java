package com.example.gestionempresarial.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gestionempresarial.R;

public class Register extends AppCompatActivity {

    EditText et_legajo, et_nombre, et_apellido, et_email, et_telefono, et_calle, et_numero, et_ciudad, et_pais;

    String legajo, nombre, apellido, email, telefono, calle, numero, ciudad, pais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button register = findViewById(R.id.btn_register);

        et_legajo = findViewById(R.id.et_legajo);
        et_nombre = findViewById(R.id.et_nombre);
        et_apellido = findViewById(R.id.et_apellido);
        et_email = findViewById(R.id.et_email);
        et_telefono = findViewById(R.id.et_telefono);
        et_calle = findViewById(R.id.et_calle);
        et_numero = findViewById(R.id.et_numero);
        et_ciudad = findViewById(R.id.et_ciudad);
        et_pais = findViewById(R.id.et_pais);


        legajo = et_legajo.getText().toString();
        nombre = et_nombre.getText().toString();
        apellido = et_apellido.getText().toString();
        email= et_email.getText().toString();
        telefono = et_legajo.getText().toString();
        calle = et_legajo.getText().toString();
        numero = et_legajo.getText().toString();
        ciudad = et_legajo.getText().toString();
        pais = et_legajo.getText().toString();




        register.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });


    }
}
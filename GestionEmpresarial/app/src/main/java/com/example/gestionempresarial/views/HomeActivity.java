package com.example.gestionempresarial.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gestionempresarial.R;
import com.example.gestionempresarial.pojos.Auth;
import com.example.gestionempresarial.pojos.Employee;

public class HomeActivity extends AppCompatActivity {

    Auth usuario = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button list = findViewById(R.id.btn_employees);
        Button myProfile = findViewById(R.id.btn_myprofile);
        TextView logout = findViewById(R.id.btn_logout);
        Button register_employee = findViewById(R.id.btn_register_employee);

        usuario = (Auth) getIntent().getExtras().getSerializable("user");


        list.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), EmployeesList.class);
            startActivity(intent);
        });

        myProfile.setOnClickListener(view -> {

            Intent intent = new Intent(this, MyProfile.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", usuario);
            intent.putExtras(bundle);
            startActivity(intent);
        });

        logout.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });


        register_employee.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RegisterEmployeeActivity.class);
            startActivity(intent);
        });
    }
}
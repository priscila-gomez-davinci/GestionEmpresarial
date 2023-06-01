package com.example.gestionempresarial.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.gestionempresarial.model.LoginModel;
import com.example.gestionempresarial.mvp.models.ILoginModel;
import com.example.gestionempresarial.mvp.view.ILoginView;
import com.example.gestionempresarial.pojos.Auth;
import com.example.gestionempresarial.presenters.LoginPresenter;

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    public String user;
    public String password;
    LoginPresenter presenter = null;
    LoginModel model = null;

    Auth usuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText et_user= findViewById(R.id.user);
        EditText et_pass= findViewById(R.id.password);
        Button btn_login = findViewById(R.id.btn_login);
        TextView tv_registrarse = findViewById(R.id.btn_register);

        model = new LoginModel(this);
        presenter = new LoginPresenter(this, model);



        btn_login.setOnClickListener(view -> {
            user = et_user.getText().toString();
            password = et_pass.getText().toString();
            if (user.isEmpty()|| password.isEmpty()) {
                showLoginError();
            } else {
                presenter.loginUser(user, password);
            }
        });

        tv_registrarse.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Register.class);
            startActivity(intent);
        });

        fillDatabase();
    }
    public void fillDatabase() {

        DbCreator dbCreator = new DbCreator(this);
        SQLiteDatabase db = dbCreator.getReadableDatabase();


    }

    @Override
    public void showLoginSuccess() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("user", (Serializable) usuario);
        startActivity(intent);
    }

    @Override
    public void showLoginError() {
        errorLoginDialog().show();
    }

    @Override
    public void saveUserData(Auth usuario) {
        this.usuario = usuario;
    }


    private AlertDialog errorLoginDialog()
    {
        return new AlertDialog.Builder(this)
                .setTitle("Credenciales erróneas")
                .setMessage("Por favor reingrese las credenciales")

                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                })
                .create();
    }
}
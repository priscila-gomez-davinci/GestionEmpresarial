package com.example.gestionempresarial.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.gestionempresarial.R;
import com.example.gestionempresarial.model.RegisterUserModel;
import com.example.gestionempresarial.mvp.view.IRegisterUserView;
import com.example.gestionempresarial.presenters.LoginPresenter;
import com.example.gestionempresarial.presenters.RegisterUserPresenter;

public class RegisterUserActivity extends AppCompatActivity implements IRegisterUserView {

    EditText et_nombre, et_apellido, et_usuario, et_password;
    String nombre, apellido, usuario, password;

    RegisterUserModel model = null;
    RegisterUserPresenter presenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        model = new RegisterUserModel(this);
        presenter =  new RegisterUserPresenter(this, model);

        Button register = findViewById(R.id.btn_register);

        et_nombre = findViewById(R.id.et_nombre);
        et_apellido = findViewById(R.id.et_apellido);
        et_usuario = findViewById(R.id.et_usuario);
        et_password = findViewById(R.id.et_password);


        register.setOnClickListener(view -> {

            if(validateForm()){
                obtenerValidarDatos();
                model.generateUser(nombre, apellido, usuario, password);
                errorLoginDialog().show();
            }

        });


    }

    @Override
    public void showRegisterError(String mensaje) {

    }

    @Override
    public void showSuccessRegister(String mensaje) {

    }

    @Override
    public void showFormError() {


    }

    @Override
    public boolean validateForm() {
        if (et_nombre.length() == 0) {
            et_nombre.setError("Campo requerido");
            return false;
        }

        if (et_apellido.length() == 0) {
            et_apellido.setError("Campo requerido");
            return false;
        }

        if (et_usuario.length() == 0) {
            et_usuario.setError("Campo requerido");
            return false;
        }

        if (et_password.length() == 0) {
            et_password.setError("Campo requerido");
            return false;
        }
        return true;
    }

    @Override
    public void obtenerValidarDatos() {
        nombre = et_nombre.getText().toString();
        apellido = et_apellido.getText().toString();
        usuario = et_usuario.getText().toString();
        password= et_password.getText().toString();

    }

    private AlertDialog errorLoginDialog()
    {
        return new AlertDialog.Builder(this)
                .setTitle("Usuario creado")
                .setMessage("Se lo redirigirá al login")

                .setPositiveButton("Ok", (dialog, whichButton) -> {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                })
                .create();
    }
}
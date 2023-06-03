package com.example.gestionempresarial.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.gestionempresarial.R;
import com.example.gestionempresarial.model.RegisterUserModel;
import com.example.gestionempresarial.mvp.view.IRegisterUserView;
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
            obtenerValidarDatos();
            model.generateUser(nombre, apellido, usuario, password);
            //Agregar dos textview y un boton para la busqueda de las coordenadas, si las coordenadas estan mal, que no se active el boton de registro y que se vea el error

            //Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            //startActivity(intent);
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
    public void obtenerValidarDatos() {
        nombre = et_nombre.getText().toString();
        apellido = et_apellido.getText().toString();
        usuario = et_usuario.getText().toString();
        password= et_password.getText().toString();
    }
}
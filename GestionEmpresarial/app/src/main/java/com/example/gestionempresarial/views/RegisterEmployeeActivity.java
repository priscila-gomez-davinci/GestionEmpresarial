package com.example.gestionempresarial.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.gestionempresarial.R;
import com.example.gestionempresarial.model.RegisterEmployeeModel;
import com.example.gestionempresarial.mvp.view.IRegisterEmployeeView;
import com.example.gestionempresarial.presenters.RegisterEmployeePresenter;

public class RegisterEmployeeActivity extends AppCompatActivity implements IRegisterEmployeeView {

    EditText et_legajo, et_nombre, et_apellido, et_email, et_telefono, et_calle, et_numero, et_ciudad, et_pais;

    String legajo, nombre, apellido, email, telefono, calle, numero, ciudad, pais, direccion, lat, lon;
    public double latitud, longitud;

    RegisterEmployeeModel model = null;
    RegisterEmployeePresenter presenter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_employee);

        Button register = findViewById(R.id.btn_register_employee);

        et_legajo = findViewById(R.id.et_legajo);
        et_nombre = findViewById(R.id.et_nombre);
        et_apellido = findViewById(R.id.et_apellido);
        et_email = findViewById(R.id.et_email);
        et_telefono = findViewById(R.id.et_telefono);
        et_calle = findViewById(R.id.et_calle);
        et_numero = findViewById(R.id.et_numero);
        et_ciudad = findViewById(R.id.et_ciudad);
        et_pais = findViewById(R.id.et_pais);

        model = new RegisterEmployeeModel(this);
        presenter = new RegisterEmployeePresenter(this,model);


        register.setOnClickListener(view -> {
            obtenerDatos();
            if(presenter.checkIfEmployeeExists(legajo)){
                errorDialog().show();
            }else{
                presenter.obtenerCoordenadasDireccion(direccion, getApplicationContext());
                lat = String.valueOf(latitud);
                lon = String.valueOf(longitud);
                presenter.saveEmployee(nombre , apellido, email,  telefono, legajo,
                        calle , numero , ciudad , pais, lat,  lon);

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void setLatAndLon(double lat, double lon) {
        latitud = lat;
        longitud = lon;
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
    public void obtenerDatos() {
        legajo = et_legajo.getText().toString();
        nombre = et_nombre.getText().toString();
        apellido = et_apellido.getText().toString();
        email= et_email.getText().toString();
        telefono = et_telefono.getText().toString();
        calle = et_calle.getText().toString();
        numero = et_numero.getText().toString();
        ciudad = et_ciudad.getText().toString();
        pais = et_pais.getText().toString();


        StringBuilder sb = new StringBuilder();
        sb.append(calle);
        sb.append(" ");
        sb.append(numero);
        sb.append(" , ");
        sb.append(ciudad);
        sb.append(" , ");
        sb.append(pais);
        direccion = sb.toString();

    }

    private AlertDialog errorDialog()
    {
        return new AlertDialog.Builder(this)
                .setTitle("Usuario existente")
                .setMessage("Ya existe un usuario con este legajo, revise el listado de nómina")

                .setPositiveButton("Ok", (dialog, whichButton) -> {

                })
                .create();
    }
}
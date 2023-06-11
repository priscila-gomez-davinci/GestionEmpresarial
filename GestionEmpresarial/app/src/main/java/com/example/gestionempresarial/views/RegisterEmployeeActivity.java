package com.example.gestionempresarial.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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

                if(validateForm()){
                    presenter.saveEmployee(nombre , apellido, email,  telefono, legajo,
                            calle , numero , ciudad , pais, lat,  lon);
                    successDialog().show();
                }else{
                    saveDialog().show();
                }
            }
        });


    }

    @Override
    public void setLatAndLon(double lat, double lon) {
        latitud = lat;
        longitud = lon;
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

    @Override
    public boolean validateForm() {

        if (et_legajo.length() == 0) {
            et_legajo.setError("Este campo es obligatorio");
            return false;
        } else if (!TextUtils.isDigitsOnly(legajo)) {
            et_legajo.setError("Ingresa solo números");
            return false;
        }
        if (et_nombre.length() == 0) {
            et_nombre.setError("Este campo es obligatorio");
            return false;
        } else if (!nombre.matches("^[a-zA-Z\\s]+$")) {
            et_nombre.setError("Ingresa solo texto");
            return false;
        }
        if (et_apellido.length() == 0) {
            et_apellido.setError("Este campo es obligatorio");
            return false;
        } else if (!apellido.matches("^[a-zA-Z\\s]+$")) {
            et_apellido.setError("Ingresa solo texto");
            return false;
        }
        if (et_email.length() == 0) {
            et_email.setError("Este campo es obligatorio");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_email.setError("Ingresa una dirección de correo electrónico válida");
            return false;
        }
        if (et_telefono.length() == 0) {
            et_telefono.setError("Este campo es obligatorio");
            return false;
        } else if (!TextUtils.isDigitsOnly(telefono)) {
            et_telefono.setError("Ingresa solo números");
            return false;
        }
        if (et_calle.length() == 0) {
            et_calle.setError("Este campo es obligatorio");
            return false;
        } else if (!calle.matches("^[a-zA-Z\\s]+$")) {
            et_calle.setError("Ingresa solo texto");
            return false;
        }
        if (et_numero.length() == 0) {
            et_numero.setError("Este campo es obligatorio");
            return false;
        } else if (!TextUtils.isDigitsOnly(numero)) {
            et_numero.setError("Ingresa solo números");
            return false;
        }
        if (et_ciudad.length() == 0) {
            et_ciudad.setError("Este campo es obligatorio");
            return false;
        } else if (!ciudad.matches("^[a-zA-Z\\s]+$")) {
            et_ciudad.setError("Ingresa solo texto");
            return false;
        }
        if (et_pais.length() == 0) {
            et_pais.setError("Este campo es obligatorio");
            return false;
        } else if (!pais.matches("^[a-zA-Z\\s]+$")) {
            et_pais.setError("Ingresa solo texto");
            return false;
        }

        return true;
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
    private AlertDialog saveDialog()
    {
        return new AlertDialog.Builder(this)
                .setTitle("Error en el formulario")
                .setMessage("Por favor revise los datos ingresados")

                .setPositiveButton("Ok", (dialog, whichButton) -> {

                })
                .create();
    }

    private AlertDialog successDialog()
    {
        return new AlertDialog.Builder(this)
                .setTitle("Usuario guardado")
                .setMessage("Será redirigido al menú principal")

                .setPositiveButton("Ok", (dialog, whichButton) -> {
                    finish();
                })
                .create();
    }
}
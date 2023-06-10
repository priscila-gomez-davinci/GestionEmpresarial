package com.example.gestionempresarial.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestionempresarial.R;
import com.example.gestionempresarial.model.EmployeeDetailModel;
import com.example.gestionempresarial.mvp.view.IEmployeeDetailView;
import com.example.gestionempresarial.pojos.Employee;
import com.example.gestionempresarial.presenters.EmployeeDetailPresenter;

public class EmployeeDetail extends AppCompatActivity implements IEmployeeDetailView {

    LinearLayout ll_noeditable, ll_editable, ll_buttons;
    EditText et_nombre, et_apellido, et_email, et_telefono, et_calle, et_numero, et_ciudad, et_pais;
    TextView tv_legajo , tv_nombre, tv_apellido, tv_email, tv_telefono, tv_calle, tv_numero, tv_ciudad, tv_pais, et_legajo;
    String legajo , nombre, apellido, email, telefono, calle, numero, ciudad, pais, direccion;
    Employee empleado;
    double lat, lon;
    int id;

    Button btn_edit, btn_delete, btn_back, btn_update, btn_call, btn_maps, btn_email, btn_cancel;
    private final int TEL_COD = 100;
    EmployeeDetailPresenter presenter;
    EmployeeDetailModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);

        Bundle bundle = getIntent().getExtras();
        empleado = (Employee) bundle.getSerializable("empleado");
        legajo = empleado.getFileNumber();
        nombre = empleado.getName();
        apellido = empleado.getLastname();
        email= empleado.getEmail();
        telefono = empleado.getTelephone();
        calle= empleado.getStreet();
        numero= empleado.getNumber();
        ciudad= empleado.getCity();
        pais = empleado.getCountry();
        id = empleado.getId();
        direccion = direccion(calle, numero, ciudad, pais);

        model = new EmployeeDetailModel(this);
        presenter = new EmployeeDetailPresenter(this, model);

        /**Validamos que no sea null, si uno es  o no son doubles, el empleado vive en el obelisco **/
        try {
            lat = Double.parseDouble(empleado.getLat());
            lon = Double.parseDouble(empleado.getLon());
        } catch (NumberFormatException e) {
            lat = -34.605425;
            lon = -58.381555;
        }



        ll_editable = findViewById(R.id.ll_editable);
        ll_noeditable = findViewById(R.id.ll_noeditable);
        ll_buttons = findViewById(R.id.ll_buttons);
        btn_edit = findViewById(R.id.btn_edit);
        btn_delete = findViewById(R.id.btn_delete);
        btn_back = findViewById(R.id.btn_back);
        btn_update  = findViewById(R.id.btn_update);
        btn_call = findViewById(R.id.btn_call);
        btn_maps = findViewById(R.id.btn_maps);

        tv_legajo= findViewById(R.id.tv_legajo);
        tv_nombre= findViewById(R.id.tv_nombre);
        tv_apellido= findViewById(R.id.tv_apellido);
        tv_email= findViewById(R.id.tv_email);
        tv_telefono= findViewById(R.id.tv_telefono);
        tv_calle= findViewById(R.id.tv_calle);
        tv_numero= findViewById(R.id.tv_numero);
        tv_ciudad= findViewById(R.id.tv_ciudad);
        tv_pais= findViewById(R.id.tv_pais);

        et_legajo= findViewById(R.id.et_legajo);
        et_nombre= findViewById(R.id.et_nombre);
        et_apellido= findViewById(R.id.et_apellido);
        et_email= findViewById(R.id.et_email);
        et_telefono= findViewById(R.id.et_telefono);
        et_calle= findViewById(R.id.et_calle);
        et_numero= findViewById(R.id.et_numero);
        et_ciudad= findViewById(R.id.et_ciudad);
        et_pais= findViewById(R.id.et_pais);
        btn_email = findViewById(R.id.btn_email);
        btn_cancel = findViewById(R.id.btn_cancel);
        fillViewDefault();


        btn_edit.setOnClickListener(view -> {
            fillViewEditable();
            ll_noeditable.setVisibility(View.GONE);
            ll_editable.setVisibility(View.VISIBLE);
            ll_buttons.setVisibility(View.GONE);
        });

        btn_back.setOnClickListener(view -> {
            finish();
        });

        btn_call.setOnClickListener(view -> {
            if (telefono != null){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, TEL_COD);
                } else{
                    OlderVersions(telefono);

                }
        }
    });

        btn_update.setOnClickListener(view ->{
            retrieveData();
            editEmployeeDialog().show();
        });

        btn_cancel.setOnClickListener(view -> {
            fillViewDefault();
            ll_noeditable.setVisibility(View.VISIBLE);
            ll_editable.setVisibility(View.GONE);
            ll_buttons.setVisibility(View.VISIBLE);
        });



        btn_maps.setOnClickListener(view -> {
            Intent intent = new Intent(EmployeeDetail.this , MapsActivity.class);
            intent.putExtra("lat", lat);
            intent.putExtra("lon", lon);
            startActivity(intent);
        });



        btn_delete.setOnClickListener(view -> {
            deleteEmployeeDialog().show();
        });

        btn_email.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:" + email));

            // Verifica si hay una aplicación de correo electrónico disponible
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(EmployeeDetail.this, "No se encontró ninguna aplicación de correo electrónico", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void OlderVersions(String phoneNumber){
        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));

        int result = checkCallingOrSelfPermission(android.Manifest.permission.CALL_PHONE);
        if ( result == PackageManager.PERMISSION_GRANTED){

            startActivity(intentCall);}
        else{
            Toast.makeText(EmployeeDetail.this, "Acceso no autorizado", Toast.LENGTH_LONG).show();

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case TEL_COD:
                String permisos = permissions[0];
                int result = grantResults[0];
                if (permisos.equals(android.Manifest.permission.CALL_PHONE)){
                    if (result == PackageManager.PERMISSION_GRANTED){
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+telefono));
                        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) return;
                        startActivity(intentCall);

                    }
                    else{
                        Toast.makeText(EmployeeDetail.this, "Acceso no autorizado", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }

    }

    private boolean CheckPermission(String permission){
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void fillViewDefault() {
        tv_legajo.setText(legajo);
        tv_nombre.setText(nombre);
        tv_apellido.setText(apellido);
        tv_email.setText(email);
        tv_telefono.setText(telefono);
        tv_calle.setText(calle);
        tv_numero.setText(numero);
        tv_ciudad.setText(ciudad);
        tv_pais.setText(pais);
    }

    @Override
    public void fillViewEditable() {
        et_legajo.setText(legajo);
        et_nombre.setText(nombre);
        et_apellido.setText(apellido);
        et_email.setText(email);
        et_telefono.setText(telefono);
        et_calle.setText(calle);
        et_numero.setText(numero);
        et_ciudad.setText(ciudad);
        et_pais.setText(pais);
    }

    @Override
    public void hideView() {


    }

    @Override
    public void errorSaving() {

    }

    @Override
    public void successSaving() {

    }

    @Override
    public void retrieveData() {

        legajo = et_legajo.getText().toString();
        nombre = et_nombre.getText().toString();
        apellido = et_apellido.getText().toString();
        email= et_email.getText().toString();
        telefono = et_telefono.getText().toString();
        calle= et_calle.getText().toString();
        numero= et_numero.getText().toString();
        ciudad= et_ciudad.getText().toString();
        pais = et_pais.getText().toString();
        direccion = direccion(calle, numero, ciudad, pais);
        presenter.obtenerCoordenadasDireccion(direccion, this);

    }

    @Override
    public String direccion(String calle, String altura, String ciudad, String pais) {
        StringBuilder sb = new StringBuilder();
        sb.append(calle);
        sb.append(" ");
        sb.append(numero);
        sb.append(" , ");
        sb.append(ciudad);
        sb.append(" , ");
        sb.append(pais);

        return sb.toString();
    }

    @Override
    public void setLatAndLon(double latitud, double longitud) {
        lat = latitud;
        lon = longitud;
    }


    private AlertDialog deleteEmployeeDialog()
    {
        return new AlertDialog.Builder(this)
                .setTitle("Confirmar acción")
                .setMessage("¿Está seguro de que desea eliminar este empleado?")

                .setPositiveButton("Ok", (dialog, whichButton) -> {
                    presenter.deleteEmployee(id);
                    Intent intent = new Intent(EmployeeDetail.this, EmployeesList.class);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("No", (dialog, whichButton) -> {

                })
                .create();
    }


    private AlertDialog editEmployeeDialog()
    {
        return new AlertDialog.Builder(this)
                .setTitle("Confirmar acción")
                .setMessage("¿Está seguro de que realizar estos cambios?")

                .setPositiveButton("Ok", (dialog, whichButton) -> {
                    presenter.editEmployee(id, nombre, apellido, email, telefono, legajo, calle, numero, ciudad, pais, String.valueOf(lat), String.valueOf(lon) );
                })
                .setNegativeButton("No", (dialog, whichButton) -> {

                })
                .create();
    }
}
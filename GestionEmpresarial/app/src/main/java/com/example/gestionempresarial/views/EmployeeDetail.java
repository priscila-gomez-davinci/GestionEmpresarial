package com.example.gestionempresarial.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gestionempresarial.R;
import com.example.gestionempresarial.mvp.view.IEmployeeDetailView;
import com.example.gestionempresarial.pojos.Employee;

public class EmployeeDetail extends AppCompatActivity implements IEmployeeDetailView {

    LinearLayout ll_noeditable, ll_editable;
    EditText et_legajo , et_nombre, et_apellido, et_email, et_telefono, et_calle, et_numero, et_ciudad, et_pais;
    TextView tv_legajo , tv_nombre, tv_apellido, tv_email, tv_telefono, tv_calle, tv_numero, tv_ciudad, tv_pais;
    String legajo , nombre, apellido, email, telefono, calle, numero, ciudad, pais;
    Employee empleado;

    Button btn_edit, btn_delete, btn_back, btn_update;
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


        btn_edit = findViewById(R.id.btn_edit);
        btn_delete = findViewById(R.id.btn_delete);
        btn_back = findViewById(R.id.btn_back);
        btn_update  = findViewById(R.id.btn_update);

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




        fillViewDefault();
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fillViewEditable();
                ll_noeditable.setVisibility(View.GONE);
                ll_editable.setVisibility(View.VISIBLE);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_noeditable.setVisibility(View.VISIBLE);
                ll_editable.setVisibility(View.GONE);
            }
        });

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
}
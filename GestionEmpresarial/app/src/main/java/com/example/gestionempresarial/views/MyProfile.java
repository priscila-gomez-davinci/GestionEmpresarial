package com.example.gestionempresarial.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestionempresarial.R;
import com.example.gestionempresarial.model.MyProfileModel;
import com.example.gestionempresarial.mvp.view.IMyProfileView;
import com.example.gestionempresarial.pojos.Auth;
import com.example.gestionempresarial.presenters.MyprofilePresenter;

public class MyProfile extends AppCompatActivity implements IMyProfileView {

    Auth usuario;
    int id;
    String name, lastname, user, password,isActive;
    EditText et_nombre, et_apellido, et_password;
    TextView et_usuario, tv_usuario, tv_apellido, tv_password, tv_nombre;
    Button btn_guardar,btn_cancel, btn_editar, btn_eliminar, btn_salir;
    LinearLayout ll_noeditable, ll_editable, save_cancel_buttons, ll_buttons;

    MyprofilePresenter presenter;
    MyProfileModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        model = new MyProfileModel(this);
        presenter = new MyprofilePresenter(this, model);

        usuario = (Auth) getIntent().getExtras().getSerializable("user");
        name = usuario.getName();
        lastname = usuario.getLastname();
        user = usuario.getUser();
        password = usuario.getPassword();
        isActive = usuario.getActive();
        id = usuario.getId();

        /** Vista editable**/
        et_nombre = findViewById(R.id.et_nombre);
        et_apellido = findViewById(R.id.et_apellido);
        et_password = findViewById(R.id.et_password);
        /** Vista no editable**/
        et_usuario = findViewById(R.id.et_usuario);

        tv_nombre = findViewById(R.id.tv_nombre);
        tv_usuario = findViewById(R.id.tv_usuario);
        tv_apellido = findViewById(R.id.tv_apellido);
        tv_password = findViewById(R.id.tv_password);
        /** Botones vista editable**/
        btn_guardar = findViewById(R.id.btn_guardar);
        btn_cancel = findViewById(R.id.btn_cancel);
        /** Botones permanentes**/
        btn_editar = findViewById(R.id.btn_editar);
        btn_eliminar = findViewById(R.id.btn_eliminar);
        btn_salir = findViewById(R.id.btn_salir);
        /** Layouts visibilidad variable**/
        ll_noeditable = findViewById(R.id.ll_noeditable);
        ll_editable = findViewById(R.id.ll_editable);
        save_cancel_buttons = findViewById(R.id.save_cancel_buttons);
        ll_buttons = findViewById(R.id.ll_buttons);

        setNotEditable();

        btn_editar.setOnClickListener(view -> setEditable());
        btn_cancel.setOnClickListener(view -> setNotEditable() );
        btn_salir.setOnClickListener(view -> finish());
        btn_eliminar.setOnClickListener(view -> deleteUserDialog().show());
        btn_guardar.setOnClickListener(view -> editUserDialog().show());

    }

    @Override
    public void setEditable() {
        et_nombre.setText(name);
        et_usuario.setText(user);
        et_apellido.setText(lastname);
        et_password.setText(password);

        ll_noeditable.setVisibility(View.GONE);
        ll_editable.setVisibility(View.VISIBLE);
    }

    @Override
    public void setNotEditable() {
        tv_nombre.setText(name);
        tv_usuario.setText(user);
        tv_apellido.setText(lastname);
        tv_password.setText(password);

        ll_noeditable.setVisibility(View.VISIBLE);
        ll_editable.setVisibility(View.GONE);
    }

    @Override
    public void setNewUser() {
        usuario = presenter.getNewUser(id);
        name = usuario.getName();
        lastname = usuario.getLastname();
        user = usuario.getUser();
        password = usuario.getPassword();
        isActive = usuario.getActive();
        id = usuario.getId();

        tv_nombre.setText(name);
        tv_usuario.setText(user);
        tv_apellido.setText(lastname);
        tv_password.setText(password);

        ll_noeditable.setVisibility(View.VISIBLE);
        ll_editable.setVisibility(View.GONE);

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

        if (et_password.length() == 0) {
            et_password.setError("Campo requerido");
            return false;
        }
        return true;
    }

    private AlertDialog deleteUserDialog()
    {
        return new AlertDialog.Builder(this)
                .setTitle("Confirmar acción")
                .setMessage("¿Esta seguro de que desea eliminar este usuario?")

                .setPositiveButton("Ok", (dialog, whichButton) -> {
                    presenter.deleteUser(id);
                    Intent intent = new Intent(MyProfile.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("No", (dialog, whichButton) -> {

                })
                .create();
    }
    private AlertDialog editUserDialog() {
        return new AlertDialog.Builder(this)
                .setTitle("Confirmar")
                .setMessage("¿Confirma el guardado de los nuevos datos para este usuario?")

                .setPositiveButton("Ok", (dialog, whichButton) -> {
                    if(validateForm()){
                        presenter.editUser(id,
                                et_nombre.getText().toString(),
                                et_apellido.getText().toString(),
                                et_usuario.getText().toString(),
                                et_password.getText().toString());
                        setNewUser();
                    }

                })
                .setNegativeButton("No", (dialog, whichButton) -> Toast.makeText(MyProfile.this, "Por favor complete todos los campos", Toast.LENGTH_LONG).show())
                .create();
    }

}

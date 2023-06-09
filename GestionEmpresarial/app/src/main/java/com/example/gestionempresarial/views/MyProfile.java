package com.example.gestionempresarial.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gestionempresarial.R;
import com.example.gestionempresarial.mvp.view.IMyProfileView;
import com.example.gestionempresarial.pojos.Auth;

public class MyProfile extends AppCompatActivity implements IMyProfileView {

    Auth usuario;
    int id;
    String name, lastname, user, password,isActive;
    EditText et_nombre, et_apellido, et_password;
    TextView et_usuario, tv_usuario, tv_apellido, tv_password, tv_nombre;
    Button btn_guardar,btn_cancel, btn_editar, btn_eliminar, btn_salir;
    LinearLayout ll_noeditable, ll_editable, save_cancel_buttons, ll_buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

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

    private AlertDialog deleteUserDialog()
    {
        return new AlertDialog.Builder(this)
                .setTitle("¿Esta seguro de que desea eliminar este usuario?")
                .setMessage("Confirmar acción")

                .setPositiveButton("Ok", (dialog, whichButton) -> {
                    /**Acá iría la lógica de eliminación**/
                })
                .setNegativeButton("No", (dialog, whichButton) -> {

                })
                .create();
    }
}

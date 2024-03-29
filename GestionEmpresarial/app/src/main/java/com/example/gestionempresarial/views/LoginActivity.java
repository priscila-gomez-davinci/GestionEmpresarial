package com.example.gestionempresarial.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.gestionempresarial.R;
import com.example.gestionempresarial.databases.DbCreator;
import com.example.gestionempresarial.model.LoginModel;
import com.example.gestionempresarial.mvp.view.ILoginView;
import com.example.gestionempresarial.pojos.Auth;
import com.example.gestionempresarial.presenters.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private static final String PREF_FIRST_RUN = "pref_first_run";

    public String user;
    public String password;
    LoginPresenter presenter = null;
    LoginModel model = null;

    Auth usuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        boolean isFirstRun = isFirstRun();

        if (isFirstRun) {
            showDialog();

            markAppAsExecuted();
        }


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
            Intent intent = new Intent(getApplicationContext(), RegisterUserActivity.class);
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
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", usuario);
        intent.putExtras(bundle);
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
                .setTitle("Revise las credenciales ingresadas")
                .setMessage("Por favor ingrese las credenciales nuevamente")

                .setPositiveButton("Ok", (dialog, whichButton) -> {

                })
                .create();
    }

    private boolean isFirstRun() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return sharedPreferences.getBoolean(PREF_FIRST_RUN, true);
    }

    private void markAppAsExecuted() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PREF_FIRST_RUN, false);
        editor.apply();
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atención");
        builder.setMessage("Por seguridad, cada vez que ingrese a la aplicación, se le pedirán las credenciales.");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
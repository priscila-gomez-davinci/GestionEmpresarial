package com.example.gestionempresarial.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.gestionempresarial.R;
import com.example.gestionempresarial.mvp.view.IEmployeeDetailView;

public class EmployeeDetail extends AppCompatActivity implements IEmployeeDetailView {

    LinearLayout ll_noeditable, ll_editable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);


    }

    @Override
    public void fillView() {

    }

    @Override
    public void hideView() {
        ll_noeditable.setVisibility(View.GONE);
        ll_editable.setVisibility(View.VISIBLE);

    }

    @Override
    public void errorSaving() {

    }

    @Override
    public void successSaving() {

    }
}
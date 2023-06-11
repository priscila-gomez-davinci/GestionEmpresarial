package com.example.gestionempresarial.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.gestionempresarial.R;
import com.example.gestionempresarial.databases.DbCreator;
import com.example.gestionempresarial.pojos.Employee;
import com.example.gestionempresarial.views.EmployeeDetail;

import java.util.ArrayList;
import java.util.List;

public class EmployeesListAdapter extends BaseAdapter {

    LayoutInflater mLayoutInflater;
    DbCreator database;

    public List<Employee> empleados;

    public TextView nombre;
    public TextView apellido;

    public EmployeesListAdapter(Context context,List<Employee> empleados) {
        database = new DbCreator(context);
        mLayoutInflater = LayoutInflater.from(context);
        this.empleados = empleados;
    }

    @Override
    public int getCount() {
        return empleados.size();
    }

    @Override
    public Object getItem(int i) {
        return empleados.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = mLayoutInflater.inflate(R.layout.employee_row, null);
                nombre = view.findViewById(R.id.nombre);
                apellido = view.findViewById(R.id.apellido);

                nombre.setText(empleados.get(i).getName());
                apellido.setText(empleados.get(i).getLastname());

            view.setOnClickListener(view1 -> {
                Intent intent = new Intent(viewGroup.getContext(), EmployeeDetail.class);
                Bundle bundle = new Bundle();
                Employee empleado = empleados.get(i);
                bundle.putSerializable("empleado", empleado);
                intent.putExtras(bundle);
                viewGroup.getContext().startActivity(intent);
                ((Activity) viewGroup.getContext()).finish();
            });
        }
        return view;
    }
}

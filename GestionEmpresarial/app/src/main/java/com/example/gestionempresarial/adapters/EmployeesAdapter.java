package com.example.gestionempresarial.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.gestionempresarial.R;
import com.example.gestionempresarial.databases.DbCreator;
import com.example.gestionempresarial.datamodel.Employee;

import java.util.List;

public class EmployeesAdapter extends BaseAdapter {

    LayoutInflater mLayoutInflater;
    DbCreator ordenInspeccionDB;

    public List<Employee> empleados;

    public EmployeesAdapter(Context context) {
        ordenInspeccionDB = new DbCreator(context);
        mLayoutInflater = LayoutInflater.from(context);
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
        }
        return view;
    }
}

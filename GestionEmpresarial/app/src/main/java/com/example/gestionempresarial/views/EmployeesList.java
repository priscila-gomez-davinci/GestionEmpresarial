package com.example.gestionempresarial.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gestionempresarial.R;
import com.example.gestionempresarial.adapters.EmployeesListAdapter;
import com.example.gestionempresarial.model.EmployeesListModel;
import com.example.gestionempresarial.mvp.view.IEmployeesListView;
import com.example.gestionempresarial.pojos.Employee;
import com.example.gestionempresarial.presenters.EmployeesListPresenter;
import java.util.ArrayList;
import java.util.List;

public class EmployeesList extends AppCompatActivity implements IEmployeesListView {
    ListView lv_employees;

    EmployeesListPresenter presenter = null;
    EmployeesListModel model = null;

    LinearLayout noEmployees;

    private List<Employee> employees= new ArrayList<Employee>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_list);


        lv_employees = findViewById(R.id.lv_employees);
        noEmployees = findViewById(R.id.noEmployees);

        model = new EmployeesListModel(this);
        presenter = new EmployeesListPresenter(this, model);

        presenter.loadEmployees();
        if (employees == null || employees.isEmpty()) {
            noEmployees.setVisibility(View.VISIBLE);
            lv_employees.setVisibility(View.GONE);
        }else{
            noEmployees.setVisibility(View.GONE);
            lv_employees.setVisibility(View.VISIBLE);
            lv_employees.setAdapter(new EmployeesListAdapter(this, employees));
        }
    }

    @Override
    public void setEmployeesList(List<Employee> employees) {
        this.employees = employees;
    }
}
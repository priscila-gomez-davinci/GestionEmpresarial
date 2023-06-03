package com.example.gestionempresarial.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
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

    private List<Employee> employees= new ArrayList<Employee>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_list);

        lv_employees = findViewById(R.id.lv_employees);

        model = new EmployeesListModel(this);
        presenter = new EmployeesListPresenter(this, model);

        presenter.loadEmployees();
        lv_employees.setAdapter(new EmployeesListAdapter(this, employees));
    }

    @Override
    public void setEmployeesList(List<Employee> employees) {
        this.employees = employees;
    }
}
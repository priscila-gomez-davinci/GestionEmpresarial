package com.example.gestionempresarial.pojos;

import java.util.List;

public class Workplace {
    public Address address;
    public List<Employee> employees;


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}

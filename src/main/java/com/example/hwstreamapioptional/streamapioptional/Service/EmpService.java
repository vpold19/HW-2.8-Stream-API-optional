package com.example.hwstreamapioptional.streamapioptional.Service;

import com.example.hwstreamapioptional.streamapioptional.Model.Employee;

import java.util.Collection;

public interface EmpService {
    Employee add(String name, String surname);
    Employee remove(String name, String surname);
    Employee find(String name, String surname);

    Collection<Employee> findAll();
}

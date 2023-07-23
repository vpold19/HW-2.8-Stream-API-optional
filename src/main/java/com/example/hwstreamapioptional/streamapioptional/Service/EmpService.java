package com.example.hwstreamapioptional.streamapioptional.Service;

import com.example.hwstreamapioptional.streamapioptional.Model.Employee;

import java.util.Collection;
import java.util.Map;

public interface EmpService {
    Employee add(String name, String surname, int department, double salary);


    Employee add(Employee employee);

    Employee remove(String name, String surname, int department, double salary);

    Employee remove(Employee employee);

    Employee find(String name, String surname, int department, double salary);

    Employee find(Employee employee);

    Collection<Employee> findAll();
}

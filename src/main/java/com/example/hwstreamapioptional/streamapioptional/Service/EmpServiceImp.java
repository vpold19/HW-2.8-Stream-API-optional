package com.example.hwstreamapioptional.streamapioptional.Service;

import com.example.hwstreamapioptional.streamapioptional.Exception.EmployeeAlreadyAddedException;
import com.example.hwstreamapioptional.streamapioptional.Exception.EmployeeNotFoundException;
import com.example.hwstreamapioptional.streamapioptional.Exception.EmployeeStorageIsFullException;
import com.example.hwstreamapioptional.streamapioptional.Model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class EmpServiceImp implements EmpService{
    private static final int Size_Limit = 5;
    private Map<String,Employee> employees = new HashMap<>(Size_Limit);

    public EmpServiceImp() {
        Employee employee1 = new Employee("Vadim", "Poldlin", 2000000,1);
        Employee employee2 = new Employee("Kevin", "Castro", 100000,1);
        Employee employee3 = new Employee("Osky", "Castro", 200000,2);
        Employee employee4 = new Employee("Diego", "Roman", 50000,2);
        employees.put(createKey(employee1),employee1);
        employees.put(createKey(employee2),employee2);
        employees.put(createKey(employee3),employee3);
        employees.put(createKey(employee4),employee4);
    }

    @Override
    public Employee add(String name, String surname, int department, double salary) {
        if (employees.size() >= 10) {
            throw new EmployeeStorageIsFullException("Нельзя добавить работника, привышен придел");
        }
        Employee employee = new Employee(name, surname, salary,department);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFullName(),employee);
        return employee;
    }


    @Override
    public Employee remove(String name, String surname, int department, double salary) {
        Employee employee = new Employee(name, surname, salary,department);
        if (employees.containsKey(employee.getFullName())) {
            return employees.remove(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee find(String name, String surname, int department, double salary) {
        Employee employee = new Employee(name, surname, salary,department);
        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
    private static String createKey(Employee employee){
        return createKey(employee.getName(),employee.getSurname());
    }

    private static String createKey(String name, String surname) {
        return (name+surname).toLowerCase();
    }
}

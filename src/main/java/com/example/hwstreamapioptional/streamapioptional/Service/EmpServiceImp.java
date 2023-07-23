package com.example.hwstreamapioptional.streamapioptional.Service;

import com.example.hwstreamapioptional.streamapioptional.Exception.EmployeeAlreadyAddedException;
import com.example.hwstreamapioptional.streamapioptional.Exception.EmployeeNotFoundException;
import com.example.hwstreamapioptional.streamapioptional.Exception.EmployeeStorageIsFullException;
import com.example.hwstreamapioptional.streamapioptional.Model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class EmpServiceImp implements EmpService{
    private static final int Size_Limit = 6;
    private Map<String,Employee> employees = new HashMap<>(Size_Limit);


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
    public Employee add(Employee employee){
        if (employees.size()>=10){
            throw new EmployeeStorageIsFullException("Нельзя добавить работника, привышен придел ");
        }
        Employee worker = new Employee(employee.getName(),employee.getSurname(),employee.getSalary(),employee.getDepartment());
        if (employees.containsKey(employee.getFullName())){
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует");
        }
        employees.put(employee.getFullName(),worker);
        return worker;
    }


    @Override
    public Employee remove(String name, String surname, int department, double salary) {
        Employee employee = new Employee(name, surname, salary,department);
        if (employees.containsKey(employee.getFullName())) {
            return employees.remove(employee.getFullName());
        }
        throw new  EmployeeNotFoundException();
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

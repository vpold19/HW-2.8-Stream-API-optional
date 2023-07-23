package com.example.hwstreamapioptional.streamapioptional;

import com.example.hwstreamapioptional.streamapioptional.Exception.EmployeeAlreadyAddedException;
import com.example.hwstreamapioptional.streamapioptional.Exception.EmployeeNotFoundException;
import com.example.hwstreamapioptional.streamapioptional.Exception.EmployeeStorageIsFullException;
import com.example.hwstreamapioptional.streamapioptional.Model.Employee;
import com.example.hwstreamapioptional.streamapioptional.Service.EmpServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;


public class EmployeeServiceTest {
    EmpServiceImp empServiceImp = new EmpServiceImp();

    @Test
    public void addEmployee() {
        Employee expected = new Employee("Vadim", "Poldolin", 12653623, 1);
        Assertions.assertEquals(empServiceImp.add("Vadim", "Poldolin", 1, 12653623), expected);
    }

    @Test
    public void addIntoFullStorage() {

        Employee employee1 = new Employee("Vadim", "Poldolin", 12653623, 1);
        Employee employee2 = new Employee("oleg", "olegov", 20000, 1);
        Employee employee3 = new Employee("sergey", "sergeev", 30000, 2);
        Employee employee4 = new Employee("Petr", "Petrov", 5000, 2);
        Employee employee5 = new Employee("Semen", "Semenov", 10000, 3);
        Employee employee6 = new Employee("Gena", "Genov", 1000, 3);

        empServiceImp.add(employee1);
        empServiceImp.add(employee2);
        empServiceImp.add(employee3);
        empServiceImp.add(employee4);
        empServiceImp.add(employee5);
        empServiceImp.add(employee6);


        assertThrows(EmployeeStorageIsFullException.class, () -> empServiceImp.add(
                new Employee("Leva", "Avel", 123908, 1)));

    }

    @Test
    public void addSameEmployee() {
        Employee employee = new Employee("Vadim", "Poldolin", 12653623, 1);
        empServiceImp.add(employee);
        assertThrows(EmployeeAlreadyAddedException.class, () -> empServiceImp.add(employee));


    }

    @Test
    public void removePositive() {
        Employee employee = new Employee("Vadim", "Poldolin", 123430200, 1);
        empServiceImp.add(employee);
        empServiceImp.remove(employee);
        Assertions.assertFalse(empServiceImp.findAll().contains(employee));


    }

    @Test
    public void removeUnexpectedEmployee() {
        Employee employee = new Employee("Vadim", "Poldolin", 123430200, 1);
        empServiceImp.add(employee);
        Assertions.assertThrows(EmployeeNotFoundException.class,()->
                empServiceImp.remove("ivan", "ivanov", 2, 1000));
    }

    @Test
    public void findPositive() {
        Employee employee = new Employee("Vadim", "Poldolin", 123430200, 1);
        empServiceImp.add(employee);
        Employee actual = empServiceImp.find(employee);
        Assertions.assertEquals(employee, actual);
        assertNotNull(actual);

    }

    @Test
    public void findUnexpectedEmployee() {
        Employee employee = new Employee("Vadim", "Poldolin", 123430200, 1);
        empServiceImp.add(employee);
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> empServiceImp.find
                ("ivan", "ivanov", 1, 100));
    }
}

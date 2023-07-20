package com.example.hwstreamapioptional.streamapioptional;

import com.example.hwstreamapioptional.streamapioptional.Model.Employee;
import com.example.hwstreamapioptional.streamapioptional.Service.DepartmentService;
import com.example.hwstreamapioptional.streamapioptional.Service.EmpServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    public static final List<Employee> EMPLOYEES = List.of(
            new Employee("ivan", "ivanov", 10000, 1),
            new Employee("oleg", "olegov", 20000, 1),
            new Employee("sergey", "sergeev", 30000, 2),
            new Employee("Petr", "Petrov", 5000, 2),
            new Employee("Semen", "Semenov", 10000, 3),
            new Employee("Gena", "Genov", 1000, 3));
    @Mock
    EmpServiceImp empServiceImp;
    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    public void init() {
        when(empServiceImp.findAll()).thenReturn(EMPLOYEES);
    }

    @Test
    public void sum() {
        Double expected = 35000.0;
        Double actual = departmentService.getSumSalary(2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void maxMoney() {
        Double expected = 10000.0;
        Double actual = departmentService.getMaxSalary(3);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void minMoney() {
        Double expected = 1000.0;
        Double actual = departmentService.getMinSalary(3);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void allEmployees() {
        List<Employee> expected = new ArrayList<>(List.of(
                new Employee("ivan", "ivanov", 10000, 1),
                new Employee("oleg", "olegov", 20000, 1)
        ));
        List<Employee> actual = departmentService.getAll(1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void wrongDepartment() {
        List<Employee> actual = departmentService.getAll(4);
        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    public void allWorkers() {
        Employee employee = new Employee("ivan", "ivanov", 10000, 1);
        Employee employee1 = new Employee("oleg", "olegov", 20000, 1);
        Map<Integer, List<Employee>> actual = departmentService.getAll();
        Assertions.assertTrue(actual.get(1).contains(employee));
        Assertions.assertTrue(actual.get(1).contains(employee1));
        Assertions.assertFalse(actual.get(2).contains(employee));


    }
}

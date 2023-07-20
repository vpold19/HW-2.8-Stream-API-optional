package com.example.hwstreamapioptional.streamapioptional;

import com.example.hwstreamapioptional.streamapioptional.Exception.EmployeeStorageIsFullException;
import com.example.hwstreamapioptional.streamapioptional.Model.Employee;
import com.example.hwstreamapioptional.streamapioptional.Service.EmpServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        for (int i = 0; i < 6; i++) {
            char additionalChar = (char) ('a' + i);
empServiceImp.add(new Employee("Vadim" + additionalChar, "Poldolin" + additionalChar, 12653623, 1));
        }
       Employee employee1 = new Employee("Vadim", "Poldolin", 12653623, 1);
       Employee employee2 = new Employee("oleg", "olegov", 20000, 1);
      Employee employee3 = new Employee("sergey", "sergeev", 30000, 2);
       Employee employee4 = new Employee("Petr", "Petrov", 5000, 2);
        Employee employee5 = new Employee("Semen", "Semenov", 10000, 3);
        Employee employee6 = new Employee("Gena", "Genov", 1000, 3);
      empServiceImp.add(employee1);

        Assertions.assertThrows(EmployeeStorageIsFullException.class, () -> empServiceImp.add("Iosif", "Stalin",
                2, 45678));

    }

    // @Test
    public void addSameEmployee() {
    }

    //  @Test
    public void removePositive() {
        Employee expected = new Employee("Vadim", "Poldolin", 1, 123430200);
        Employee actual = empServiceImp.remove("Vadim", "Poldolin", 1, 123430200);
        Assertions.assertEquals(expected, actual);
    }

    // @Test
    public void removeUnexpectedEmployee() {

    }

    // @Test
    public void findPositive() {

    }

    // @Test
    public void findUnexpectedEmployee() {

    }
}

package com.example.hwstreamapioptional.streamapioptional.Service;

import com.example.hwstreamapioptional.streamapioptional.Exception.EmployeeNotFoundException;
import com.example.hwstreamapioptional.streamapioptional.Model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmpServiceImp empServiceImp;

    public DepartmentService(EmpServiceImp empServiceImp) {
        this.empServiceImp = empServiceImp;
    }

    public Employee getEmployeeWithMaxSalary(int department) {
        return empServiceImp.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }
    public Employee getEmployeeWithMinSalary(int department) {
        return empServiceImp.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }
    public List<Employee> getAll(int department){
       return empServiceImp.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }
    public Map<Integer,List<Employee>> getAll(){
       return empServiceImp.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}

package com.example.hwstreamapioptional.streamapioptional.Controller;

import com.example.hwstreamapioptional.streamapioptional.Model.Employee;
import com.example.hwstreamapioptional.streamapioptional.Service.EmpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmpService empService;

    public EmployeeController(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping("/employee")
    public String hello() {
        return "Привет";
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String name, @RequestParam String surname, @RequestParam int department, @RequestParam double salary) {
        return empService.add(name, surname, department, salary);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String name, @RequestParam String surname,
                                   @RequestParam double salary, @RequestParam int department) {
        return empService.remove(name, surname, department, salary);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String name, @RequestParam String surname,
                                 @RequestParam double salary, @RequestParam int department) {
        return empService.find(name, surname, department, salary);
    }

    @GetMapping("/findAll")
    public Collection<Employee> findAll() {
        return empService.findAll();
    }
}

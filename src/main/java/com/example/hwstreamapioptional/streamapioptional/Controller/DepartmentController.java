package com.example.hwstreamapioptional.streamapioptional.Controller;

import com.example.hwstreamapioptional.streamapioptional.Model.Employee;
import com.example.hwstreamapioptional.streamapioptional.Service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/salary/max")
    public Double getEmployeeWithMaxSalary(@PathVariable("id") int id) {
        return departmentService.getMaxSalary(id);
    }

    @GetMapping("/{id}/salary/min")
    public Double getEmployeeWithMinSalary(@PathVariable("id") int id) {
        return departmentService.getMinSalary(id);
    }

    @GetMapping(value = "/{id}/employees")
    public List<Employee> getAll(@PathVariable("id") int id) {
        return departmentService.getAll(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getAll() {
        return departmentService.getAll();
    }
    @GetMapping("/{id}/salary/sum")
    public Double getSumSalary(@PathVariable("id") int id){
        return departmentService.getSumSalary(id);
    }
}

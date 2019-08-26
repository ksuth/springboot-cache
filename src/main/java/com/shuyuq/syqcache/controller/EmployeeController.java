package com.shuyuq.syqcache.controller;

import com.shuyuq.syqcache.entity.Employee;
import com.shuyuq.syqcache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @GetMapping("emp/{id}")
    public Employee getEmployeeByid(@PathVariable("id") Integer id){
        Employee employee = employeeService.getEmployeeById(id);
        return employee;
    }

    @GetMapping("/emp")
    public Employee updateEmployee(Employee employee){

        employeeService.updateEmployee(employee);
        return employee;
    }
}

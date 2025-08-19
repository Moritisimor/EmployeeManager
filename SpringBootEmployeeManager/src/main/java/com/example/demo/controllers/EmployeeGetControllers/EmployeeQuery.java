package com.example.demo.controllers.EmployeeGetControllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EmployeeManager;
import com.example.demo.classes.Employee;
import com.example.demo.exceptions.NotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class EmployeeQuery {
    @GetMapping("EmployeeQuery/{queryId}")
    public Employee handleEmployeeQuery(@PathVariable String queryId) {
        Employee selectedEmployee = null;
        for (var employee : EmployeeManager.employeeList) {
            if (queryId.equals(employee.getId())) {
                selectedEmployee = employee;
            }
        }
        if (selectedEmployee != null) {
            return selectedEmployee;
        }
        throw new NotFoundException("ID not found");
    }
}

package com.example.demo.controllers.EmployeeGetControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Employee> handleEmployeeQuery(@PathVariable String queryId) {
        Employee selectedEmployee = null;
        for (var employee : EmployeeManager.employeeList) {
            if (queryId.equals(employee.getId())) {
                selectedEmployee = employee;
            }
        }
        if (selectedEmployee != null) {
            return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(selectedEmployee);
        }
        throw new NotFoundException("ID not found");
    }
}

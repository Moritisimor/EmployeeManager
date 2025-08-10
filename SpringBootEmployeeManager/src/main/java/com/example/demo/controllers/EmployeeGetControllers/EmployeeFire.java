package com.example.demo.controllers.EmployeeGetControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EmployeeManager;
import com.example.demo.classes.Employee;
import com.example.demo.exceptions.NotFoundException;

@RestController
public class EmployeeFire {
    @PostMapping("EmployeeFire/{queryId}")
    public ResponseEntity<String> handleEmployeeFire(@PathVariable String queryId) {
        var employee = Employee.getEmployeeFromList(EmployeeManager.employeeList, queryId);
        if (employee != null) {
            employee.fire(EmployeeManager.employeeList);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Succesfully fired employee");
        }
        throw new NotFoundException("Could not find ID.");
    }
}

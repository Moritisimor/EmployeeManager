package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ConflictException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.classes.Employee;
import com.example.demo.EmployeeManager;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class EmployeeController {
    @GetMapping("/EmployeeQuery/{queryId}")
    public Employee handleEmployeeQuery(@PathVariable String queryId) {
        Employee selectedEmployee = null;
        for (Employee employee : EmployeeManager.employeeList) {
            if (queryId.equals(employee.getId())) {
                selectedEmployee = employee;
            }
        }
        if (selectedEmployee != null) {
            return selectedEmployee;
        }
        throw new NotFoundException("ID not found");
    }

    @GetMapping("/EmployeeCreate/{firstName}/{lastName}/{position}/{id}/{monthlySalaryString}")
    public ResponseEntity<String> handleEmployeeCreation(@PathVariable String firstName, @PathVariable String lastName, // What a monster!
    @PathVariable String position, @PathVariable String id, @PathVariable String monthlySalaryString) {
        String fullName = firstName + lastName;
        Double monthlySalary;
        try {
            monthlySalary = Double.parseDouble(monthlySalaryString);
        } catch (NumberFormatException e) {
            throw new BadRequestException("Expected Double, got String", e);
        }

        if (monthlySalary < 0) {
            throw new BadRequestException("Expected Positive number, got negative number.");
        }

        for (var employee : EmployeeManager.employeeList) {
            if (employee.getId().equals(id)) {
                throw new ConflictException("This ID is already taken.");
            }
        }

        Employee newGuy = new Employee();
        newGuy.setName(fullName);
        newGuy.setPosition(position);
        newGuy.setMonthlySalary(monthlySalary);
        newGuy.setId(id);
        EmployeeManager.employeeList.add(newGuy);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Creation Succesful.\n");
    }
}

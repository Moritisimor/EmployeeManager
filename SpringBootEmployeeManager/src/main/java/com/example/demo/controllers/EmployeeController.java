package com.example.demo.controllers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/EmployeeList")
    public ArrayList<Employee> handleEmployeeList() {
        return EmployeeManager.employeeList;
    }

    @PostMapping("/EmployeeConfig/{option}/{queryId}/{amountString}")
    public ResponseEntity<String> handleEmployeeConfig(@PathVariable String option, @PathVariable String queryId, @PathVariable String amountString) {
        Double amount;
        try {
            amount = Double.parseDouble(amountString);
        } catch (NumberFormatException error) {
            throw new BadRequestException("Expected number, got string instead.", error);
        }

        if (amount < 0) {
            throw new BadRequestException("Number may not be negative.");
        }

        for (Employee employee : EmployeeManager.employeeList) {
            if (employee.getId().equals(queryId)) {
                if (option.equals("demote")) {
                    employee.demoteEmployee(amount);
                    return ResponseEntity.status(HttpStatus.OK).body("Successfully demoted employee.");
                } else if (option.equals("promote")) {
                    employee.promoteEmployee(amount);
                    return ResponseEntity.status(HttpStatus.OK).body("Successfully promoted employee.");
                } else {
                    throw new BadRequestException("Bad option variable.");
                }
            }
        }
        throw new NotFoundException("Could not find specified ID.");
    }

    @PostMapping("/EmployeeFire/{queryId}")
    public ResponseEntity<String> handleEmployeeFire(@PathVariable String queryId) {
        for (var employee : EmployeeManager.employeeList) {
            if (employee.getId().equals(queryId)) {
                EmployeeManager.employeeList.remove(employee);
                return ResponseEntity.status(HttpStatus.OK).body("Successfully fired employee.");
            }
        }
        throw new NotFoundException("Could not find specified ID.");
    }

    @PostMapping("/EmployeeCreate/{firstName}/{lastName}/{position}/{id}/{monthlySalaryString}")
    public ResponseEntity<String> handleEmployeeCreation(@PathVariable String firstName, @PathVariable String lastName, // What a monster!
    @PathVariable String position, @PathVariable String id, @PathVariable String monthlySalaryString) {
        String fullName = firstName + " " + lastName;
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

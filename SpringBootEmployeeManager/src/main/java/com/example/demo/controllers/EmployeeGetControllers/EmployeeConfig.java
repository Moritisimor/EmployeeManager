package com.example.demo.controllers.EmployeeGetControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EmployeeManager;
import com.example.demo.classes.Employee;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class EmployeeConfig {
    @PostMapping("EmployeeConfig/{option}/{queryId}/{amountString}")
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

        var employee = Employee.getEmployeeFromList(EmployeeManager.employeeList, queryId);
        if (employee != null) {
            if (option.equals("promote")) {
                employee.promote(amount);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Succesfully promoted employee");
            } else if (option.equals("demote")) {
                employee.demote(amount);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Succesfully demoted employee");
            } else {
                throw new BadRequestException("Invalid option parameter");
            }
        }
        throw new NotFoundException("Could not find specified ID.");
    }
}

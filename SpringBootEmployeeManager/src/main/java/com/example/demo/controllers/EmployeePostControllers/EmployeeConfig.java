package com.example.demo.controllers.EmployeePostControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EmployeeManager;
import com.example.demo.classes.Employee;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class EmployeeConfig {
    @PostMapping("EmployeeConfig/{option}/{queryId}/{amountString}")
    public ResponseEntity<String> handleEmployeeConfig(@PathVariable String option, @PathVariable String queryId, @PathVariable String amountString) {
        Double amount;
        try {
            amount = Double.parseDouble(amountString);
        } catch (NumberFormatException error) {
            return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Expected number, got string instead.");
        }

        if (amount < 0) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Number may not be negative.");
        }

        var employee = Employee.getEmployeeFromList(EmployeeManager.employeeList, queryId);
        if (employee != null) {
            if (option.equals("promote")) {
                employee.promote(amount);
                return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body("Succesfully promoted employee");
            } else if (option.equals("demote")) {
                employee.demote(amount);
                return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body("Succesfully demoted employee");
            } else {
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid operator, please only use promote or demote.");
            }
        }
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body("{message: successfully }");
    }
}

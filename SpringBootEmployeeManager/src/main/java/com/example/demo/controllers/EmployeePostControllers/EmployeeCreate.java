package com.example.demo.controllers.EmployeePostControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EmployeeManager;
import com.example.demo.classes.Employee;
import com.example.demo.exceptions.ConflictException;
import com.example.demo.exceptions.BadRequestException;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class EmployeeCreate {
    @PostMapping("EmployeeCreate/{firstName}/{lastName}/{position}/{id}/{monthlySalaryString}")
    public ResponseEntity<String> handleEmployeeCreation(@PathVariable String firstName, @PathVariable String lastName,
    @PathVariable String position, @PathVariable String id, @PathVariable String monthlySalaryString) {
        var fullName = firstName + " " + lastName;
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

        var newGuy = new Employee();
        newGuy.setName(fullName);
        newGuy.setPosition(position);
        newGuy.setMonthlySalary(monthlySalary);
        newGuy.setId(id);
        EmployeeManager.employeeList.add(newGuy);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Creation Succesful.\n");
    }
}

package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.classes.Employee;
import com.example.demo.EmployeeManager;

@RestController
public class EmployeeController {
    @GetMapping("/EmployeeQuery/{queryId}")
    public String handleEmployeeQuery(@PathVariable String queryId) {
        Employee selectedEmployee = null;
        for (Employee employee : EmployeeManager.employeeList) {
            if (queryId.equals(employee.getId())) {
                selectedEmployee = employee;
            }
        }
        if (selectedEmployee != null) {
            return selectedEmployee.getInformation();
        }
        return null;
    }

    @GetMapping("/EmployeeCreate/{firstName}/{lastName}/{position}/{id}/{monthlySalaryString}")
    public String handleEmployeeCreation(@PathVariable String firstName, @PathVariable String lastName, // What a monster!
    @PathVariable String position, @PathVariable String id, @PathVariable String monthlySalaryString) {
        String fullName = firstName + lastName;
        Double monthlySalary;
        try {
            monthlySalary = Double.parseDouble(monthlySalaryString);
        } catch (NumberFormatException e) {
            return "The monthly salary must be of numeric value.\n";
        }

        if (monthlySalary < 0) {
            return "The monthly salary may not be negative!\n";
        }

        for (var employee : EmployeeManager.employeeList) {
            if (employee.getId().equals(id)) {
                return "This ID is already taken.\n";
            }
        }

        Employee newGuy = new Employee();
        newGuy.setName(fullName);
        newGuy.setPosition(position);
        newGuy.setMonthlySalary(monthlySalary);
        newGuy.setId(id);
        EmployeeManager.employeeList.add(newGuy);
        
        return String.format("Succesfully created Employee with the following Data:\n%s", newGuy.getInformation());
    }
}

package com.example.demo.controllers.EmployeeGetControllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EmployeeManager;
import com.example.demo.classes.Employee;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class EmployeeList {
    @GetMapping("EmployeeList")
    public ArrayList<Employee> handleEmployeeList() {
        return EmployeeManager.employeeList;
    }
}

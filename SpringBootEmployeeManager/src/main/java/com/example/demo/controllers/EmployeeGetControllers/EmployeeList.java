package com.example.demo.controllers.EmployeeGetControllers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EmployeeManager;
import com.example.demo.classes.Employee;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class EmployeeList {
    @GetMapping("EmployeeList")
    public ResponseEntity<ArrayList<Employee>> handleEmployeeList() {
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(EmployeeManager.employeeList);
    }
}

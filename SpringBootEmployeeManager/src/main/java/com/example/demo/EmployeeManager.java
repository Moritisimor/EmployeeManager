package com.example.demo;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.classes.Employee;

@SpringBootApplication
public class EmployeeManager {
	public static ArrayList<Employee> employeeList = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManager.class, args);
	}
}

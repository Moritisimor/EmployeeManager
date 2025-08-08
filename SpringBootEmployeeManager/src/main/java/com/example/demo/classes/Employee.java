package com.example.demo.classes;

import java.util.ArrayList;

public class Employee {
    private String name;
    private String position;
    private String id;
    private Double monthlySalary;

    public void promoteEmployee(Double amount) {
        if (amount > 0) {
            this.setMonthlySalary(this.monthlySalary += amount);
        }
    }

    public void demoteEmployee(Double amount) {
        if (amount > 0) {
            this.setMonthlySalary(this.monthlySalary -= amount);
        }
    }

    public void fireEmployee(ArrayList<Employee> employeeList) { // This may be removed
        employeeList.remove(this);
    }

    public String getName()     { return this.name; }
    public String getPosition() { return this.position; }
    public String getId()       { return this.id; }

    public Double getMonthlySalary() {
        if (this.monthlySalary != null) {
            return this.monthlySalary;
        }
        return null;
    }

    public Double getWeeklySalary() {
        if (this.monthlySalary != null) {
            return this.monthlySalary / 4;
        }
        return null;
    }
    public Double getYearlySalary() {
        if (this.monthlySalary != null) {
            return this.monthlySalary * 12;
        }
        return null;
    }

    public void setName(String name)            { this.name = name; }
    public void setPosition(String position)    { this.position = position; }
    public void setId(String id)                { this.id = id; }

    public void setMonthlySalary(Double monthlySalary) {
        if (monthlySalary > 0) {
            this.monthlySalary = monthlySalary;
        }
    }
}

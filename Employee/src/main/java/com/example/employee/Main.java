package com.example.employee;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EmployeeData employeeData = new EmployeeData();

        // Create a new Employee object and insert it into the database
        Employee newEmployee = new Employee("John Doe", "Software Developer", 60000, new Date());
        employeeData.createEmployee(newEmployee);

        // Retrieve and display an employee by ID
        Employee employee = employeeData.getEmployeeById(1); // Assuming ID 1 exists
        System.out.println("Retrieved Employee: " + employee);

        // Retrieve and display all employees
        List<Employee> employees = employeeData.getAllEmployees();
        System.out.println("All Employees:");
        for (Employee emp : employees) {
            System.out.println(emp);
        }

        // Update an employee's details
        if (employee != null) {
            employee.setSalary(65000);
            employeeData.updateEmployee(employee);
            System.out.println("Updated Employee: " + employee);
        }

        // Delete an employee by ID
        employeeData.deleteEmployee(1);
        System.out.println("Employee with ID 1 deleted.");
    }
}


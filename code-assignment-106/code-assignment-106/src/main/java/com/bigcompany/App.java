package com.bigcompany;

import com.bigcompany.model.Employee;
import com.bigcompany.service.EmployeeAnalyzer;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class App
{
        public static void main(String[] args) throws Exception {
            String filePath = "employees.csv";
            List<Employee> employees = loadEmployees(filePath);

            EmployeeAnalyzer analyzer = new EmployeeAnalyzer(employees);

            System.out.println("=== Checking Managers' Salaries ===");
            analyzer.checkManagerSalaries();

            System.out.println("\n=== Checking Reporting Lines ===");
            analyzer.checkReportingLines();
        }

    private static List<Employee> loadEmployees(String fileName) throws IOException {
        try (var is = App.class.getClassLoader().getResourceAsStream(fileName);
             var reader = new BufferedReader(new InputStreamReader(is))) {

            List<Employee> employees = new ArrayList<>();
            String line;
            boolean first = true;
            while ((line = reader.readLine()) != null) {
                if (first) { first = false; continue; }
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String firstName = parts[1];
                String lastName = parts[2];
                double salary = Double.parseDouble(parts[3]);
                Integer managerId = parts.length > 4 && !parts[4].isBlank() ? Integer.parseInt(parts[4]) : null;
                employees.add(new Employee(id, firstName, lastName, salary, managerId));
            }
            return employees;
        }
    }

}




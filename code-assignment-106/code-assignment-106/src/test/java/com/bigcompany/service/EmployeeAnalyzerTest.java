package com.bigcompany.service;
import com.bigcompany.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
public class EmployeeAnalyzerTest
{
        @Test
        void testSalaryCheck() {
            List<Employee> employees = Arrays.asList(
                    new Employee(1, "CEO", "Boss", 100000, null),
                    new Employee(2, "John", "Doe", 40000, 1),
                    new Employee(3, "Jane", "Doe", 42000, 1)
            );

            EmployeeAnalyzer analyzer = new EmployeeAnalyzer(employees);
            analyzer.checkManagerSalaries();
        }

        @Test
        void testReportingLineCheck() {
            List<Employee> employees = Arrays.asList(
                    new Employee(1, "CEO", "Boss", 100000, null),
                    new Employee(2, "Manager1", "A", 80000, 1),
                    new Employee(3, "Manager2", "B", 70000, 2),
                    new Employee(4, "Manager3", "C", 60000, 3),
                    new Employee(5, "Manager4", "D", 50000, 4),
                    new Employee(6, "Employee", "E", 40000, 5)
            );

            EmployeeAnalyzer analyzer = new EmployeeAnalyzer(employees);
            analyzer.checkReportingLines();
        }
}




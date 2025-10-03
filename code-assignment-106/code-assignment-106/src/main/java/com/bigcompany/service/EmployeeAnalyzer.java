package com.bigcompany.service;


import com.bigcompany.model.Employee;

import java.util.*;
import java.util.stream.Collectors;


public class EmployeeAnalyzer
{
        private Map<Integer, Employee> employeeMap = new HashMap<>();
        private Map<Integer, List<Employee>> managerToEmployees = new HashMap<>();

        public EmployeeAnalyzer(List<Employee> employees) {
            for (Employee e : employees) {
                employeeMap.put(e.getId(), e);
                if (e.getManagerId() != null) {
                    managerToEmployees.computeIfAbsent(e.getManagerId(), k -> new ArrayList<>()).add(e);
                }
            }
        }

        public void checkManagerSalaries() {
            for (Integer managerId : managerToEmployees.keySet()) {
                Employee manager = employeeMap.get(managerId);
                List<Employee> subs = managerToEmployees.get(managerId);

                double avgSalary = subs.stream().mapToDouble(Employee::getSalary).average().orElse(0);
                double minAllowed = avgSalary * 1.2;
                double maxAllowed = avgSalary * 1.5;

                if (manager.getSalary() < minAllowed) {
                    System.out.println("Manager " + manager + " earns LESS than allowed by " + (minAllowed - manager.getSalary()));
                } else if (manager.getSalary() > maxAllowed) {
                    System.out.println("Manager " + manager + " earns MORE than allowed by " + (manager.getSalary() - maxAllowed));
                }
            }
        }

        public void checkReportingLines() {
            for (Employee e : employeeMap.values()) {
                int depth = getDepth(e);
                if (depth > 4) {
                    System.out.println("Employee " + e + " has too long reporting line by " + (depth - 4));
                }
            }
        }

        private int getDepth(Employee e) {
            int count = 0;
            Integer managerId = e.getManagerId();
            while (managerId != null) {
                count++;
                Employee manager = employeeMap.get(managerId);
                managerId = manager.getManagerId();
            }
            return count;
        }
}



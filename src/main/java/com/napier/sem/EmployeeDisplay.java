package com.napier.sem;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDisplay {

    /**
     * Prints a list of employees.
     * @param employees The list of employees to print.
     */
    public void printSalaries(ArrayList<Employee> employees)
    {
        if (employees == null){
            System.out.println("No employees");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s %-8s", "Emp No", "First Name", "Last Name", "Salary"));
        // Loop over all employees in the list
        for (Employee emp : employees)
        {
            if (emp == null)
                continue;
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s",
                            emp.emp_no, emp.first_name, emp.last_name, emp.salary);
            System.out.println(emp_string);
        }
    }

    public void displayEmployeeWithTitle(List<Employee> emp, String title)
    {
        if (emp != null && !emp.isEmpty())
        {
            System.out.println(String.format("%-10s %-15s %-20s %-8s", "Emp No", "First Name", "Last Name", "Title" ,"Salary"));
            for (Employee employee : emp) {
                String displayString =
                        String.format("%-10s %-15s %-20s %-8s",
                                employee.emp_no, employee.first_name, employee.last_name, title , employee.salary );
                System.out.println(displayString);
            }
        }
    }

    public void displaySalariesByDepartment(List<Employee> emp, String department)
    {
        if (emp != null && !emp.isEmpty())
        {
            System.out.println(String.format("%-10s %-15s %-20s %-8s", "Emp No", "First Name", "Last Name", "Department", "Salary"));
            for (Employee employee : emp) {
                String displayString =
                        String.format("%-10s %-15s %-20s %-8s",
                                employee.emp_no, employee.first_name, employee.last_name, department, employee.salary );
                System.out.println(displayString);
            }
        }
    }

}

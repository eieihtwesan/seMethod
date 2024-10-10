package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSQL {

    private Connection con;

    public EmployeeSQL(Connection con) {
        this.con = con;
    }

    public List<Employee> getEmployeeWithTitle(String title) {
        List<Employee> employees = new ArrayList<>();

        try {
            String strSelect =
                    "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary " +
                            "FROM employees " +
                            "JOIN salaries ON employees.emp_no = salaries.emp_no " +
                            "JOIN titles ON employees.emp_no = titles.emp_no " +
                            "WHERE salaries.to_date = '9999-01-01' " +
                            "AND titles.to_date = '9999-01-01' " +
                            "AND titles.title = ? " +
                            "ORDER BY employees.emp_no ASC";

            PreparedStatement pstmt = con.prepareStatement(strSelect);
            pstmt.setString(1, title);
            ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                emp.salary = rset.getInt("salary");
                employees.add(emp);
            }
            rset.close();
            pstmt.close();
            return employees;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public ArrayList<Employee> getAllSalaries() {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            String strSelect =
                    "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary " +
                            "FROM employees " +
                            "JOIN salaries ON employees.emp_no = salaries.emp_no " +
                            "WHERE salaries.to_date = '9999-01-01' " +
                            "ORDER BY employees.emp_no ASC";

            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                emp.salary = rset.getInt("salary");
                employees.add(emp);
            }
            rset.close();
            stmt.close();
            return employees;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to get salary details");
            return null;
        }
    }

    public ArrayList<Employee> getSalariesByDepartment(Department dept) {
        ArrayList<Employee> employees = new ArrayList<>();
        try{
            String strSelect = "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary " +
                    "FROM employees, salaries, dept_emp, departments " +
                    "WHERE employees.emp_no = salaries.emp_no " +
                    "AND employees.emp_no = dept_emp.emp_no " +
                    "AND dept_emp.dept_no = departments.dept_no " +
                    "AND salaries.to_date = '9999-01-01' " +
                    "AND departments.dept_no = '" + dept.getDept_no() + "' " +  // Dept no is treated as string
                    "ORDER BY employees.emp_no ASC";

            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                emp.salary = rset.getInt("salary");
                employees.add(emp);
            }
            rset.close();
            stmt.close();
            return employees;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to get salary details");
            return null;
        }
    }
}

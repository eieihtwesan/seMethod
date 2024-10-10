package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App
{
    /**
     * connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database
     */
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(delay);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/employees?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the SQL database
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Create new Application and Connect to database
        if (args.length < 1){
            a.connect("localhost:33060", 5000);
        }else {
            a.connect(args[0], Integer.parseInt(args[1]));
        }


        EmployeeSQL employeeSQL = new EmployeeSQL(a.con);
        EmployeeDisplay employeeDisplay = new EmployeeDisplay();

        String title = "Engineer";
        // get Employee who are Engineers database
        ArrayList<Employee> emp = employeeSQL.getEmployeeWithTitle("Engineer");
        employeeDisplay.displayEmployeeWithTitle(emp, title);

        ArrayList<Employee> emp1 = employeeSQL.getAllSalaries();
        employeeDisplay.printSalaries(emp1);

        // department
        String department = "Development";
        DepartmentSQL departmentSQL = new DepartmentSQL(a.con);
        Department dept = departmentSQL.getDepartment(department);
        List<Employee> emp2 = employeeSQL.getSalariesByDepartment(dept);
        employeeDisplay.displaySalariesByDepartment(emp2, department);

        // Disconnect from database
        a.disconnect();
    }
}
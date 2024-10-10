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
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 100;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/employees?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
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

        // Connect to database
        a.connect();

        EmployeeSQL employeeSQL = new EmployeeSQL(a.con);
        EmployeeDisplay employeeDisplay = new EmployeeDisplay();

        // get Employee who are Engineers database
        List<Employee> emp = employeeSQL.getEmployeeWithTitle("Engineer");
        // display employee with title
        employeeDisplay.displayEmployeeWithTitle(emp);

        ArrayList<Employee> emp1 = employeeSQL.getAllSalaries();
        employeeDisplay.printSalaries(emp1);

        DepartmentSQL departmentSQL = new DepartmentSQL(a.con);


        // Disconnect from database
        a.disconnect();
    }
}
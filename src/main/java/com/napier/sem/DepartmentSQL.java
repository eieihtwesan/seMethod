package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DepartmentSQL {

    private Connection con;

    public DepartmentSQL(Connection conn) {

        this.con = conn;
    }

    public Department getDepartment(String dept_name) {

        try {
            // Prepare SQL statement to prevent SQL injection
            String strSelect = "SELECT dept_no, dept_name FROM departments WHERE dept_name = ?";
            PreparedStatement pstmt = con.prepareStatement(strSelect);
            pstmt.setString(1, dept_name);
            ResultSet rset = pstmt.executeQuery();


            if (rset.next()) {
                // Here, we fetch department number as a string (like "d005")
                String dept_no = rset.getString("dept_no");
                // Assume that fetching the manager is handled separately
                Employee manager = null;  // Placeholder for manager if needed
                String dep_name = rset.getString("dept_name");
                return new Department(dep_name, dept_no, manager);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get department details");
            return null;
        }
    }
}

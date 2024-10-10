package com.napier.sem;

public class Department {
    public String dept_name;
    public String dept_no;
    public Employee manager;

    public Department(String dept_name, String dept_no, Employee manager) {
        this.dept_name = dept_name;
        this.dept_no = dept_no;
        this.manager = manager;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getDept_no() {
        return dept_no;
    }

    public void setDept_no(String dept_no) {
        this.dept_no = dept_no;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}
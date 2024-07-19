package com.mycompany.employeemanagement;

public class EmployeeIdSingleton {
    private static EmployeeIdSingleton instance;
    private int employeeId;

    // Private constructor to prevent instantiation from other classes
    private EmployeeIdSingleton() {
        // Initialize employeeId with a default value if needed
        employeeId = -1; // Or any other default value
    }

    // Method to get the singleton instance
    public static synchronized EmployeeIdSingleton getInstance() {
        if (instance == null) {
            instance = new EmployeeIdSingleton();
        }
        return instance;
    }

    // Getter for employeeId
    public int getEmployeeId() {
        return employeeId;
    }

    // Setter for employeeId
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}


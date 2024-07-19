package com.mycompany.employeemanagement;

public class UserApplication {
    private int userId;
    private String userName;
    private String applicationStatus;

    public UserApplication(int userId, String userName, String applicationStatus) {
        this.userId = userId;
        this.userName = userName;
        this.applicationStatus = applicationStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}

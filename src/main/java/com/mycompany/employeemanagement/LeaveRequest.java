package com.mycompany.employeemanagement;

import java.util.Date;

public class LeaveRequest {
    private int id;
    private int employeeId;
    private Date startDate;
    private Date endDate;
    private String description;
    private int numDays;
    private short status;

    private String username;

    private String st;

    // Constants for status
    public static final short STATUS_REJECTED = 0;
    public static final short STATUS_PENDING = 1;
    public static final short STATUS_APPROVED = 2;


    // Default constructor
    public LeaveRequest() {
        this.status = STATUS_PENDING; // Set default status to pending
    }

    // Parameterized constructor
    public LeaveRequest(int id, Date startDate, Date endDate, String description, int numDays, short status) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.numDays = numDays;
        this.status = status;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getNumDays() {
        return numDays;
    }
    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }
    public short getStatus() {
        return status;
    }
    public void setStatus(short status) {
        this.status = status;
    }
    public String getSt() {
        return st;
    }
    public void setSt(String st) {
        this.st = st;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

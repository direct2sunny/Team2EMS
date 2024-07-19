package com.mycompany.employeemanagement;

import java.sql.Date;

public class JobApplication {
    private int id;
    private int hiringPostId;
    private int userId;
    private String applicationStatus;
    private Date applicationDate;

    public JobApplication(int id, int hiringPostId, int userId, String applicationStatus, Date applicationDate) {
        this.id = id;
        this.hiringPostId = hiringPostId;
        this.userId = userId;
        this.applicationStatus = applicationStatus;
        this.applicationDate = applicationDate;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getHiringPostId() { return hiringPostId; }
    public void setHiringPostId(int hiringPostId) { this.hiringPostId = hiringPostId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getApplicationStatus() { return applicationStatus; }
    public void setApplicationStatus(String applicationStatus) { this.applicationStatus = applicationStatus; }

    public Date getApplicationDate() { return applicationDate; }
    public void setApplicationDate(Date applicationDate) { this.applicationDate = applicationDate; }
}

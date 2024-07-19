package com.mycompany.employeemanagement;

import java.sql.Date;

public class Benefit {
    private int employeeId;
    private String employeeType;
    private String benefitName;
    private String benefitDescription;
    private Date benefitDate;
    private double benefitAmount;

    public Benefit() {
    }

    public Benefit(int employeeId, String employeeType, String benefitName, String benefitDescription, Date benefitDate, double benefitAmount) {
        this.employeeId = employeeId;
        this.employeeType = employeeType;
        this.benefitName = benefitName;
        this.benefitDescription = benefitDescription;
        this.benefitDate = benefitDate;
        this.benefitAmount = benefitAmount;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getBenefitName() {
        return benefitName;
    }

    public void setBenefitName(String benefitName) {
        this.benefitName = benefitName;
    }

    public String getBenefitDescription() {
        return benefitDescription;
    }

    public void setBenefitDescription(String benefitDescription) {
        this.benefitDescription = benefitDescription;
    }

    public Date getBenefitDate() {
        return benefitDate;
    }

    public void setBenefitDate(Date benefitDate) {
        this.benefitDate = benefitDate;
    }

    public double getBenefitAmount() {
        return benefitAmount;
    }

    public void setBenefitAmount(double benefitAmount) {
        this.benefitAmount = benefitAmount;
    }
}

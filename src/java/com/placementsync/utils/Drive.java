/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.placementsync.utils;

import java.util.Date;

public class Drive {
    
    //model class
    private int driveId;
    private String companyName;
    private String jobRole;
    private double ctcLpa;
    private double minCgpaRequired;
    private Date applicationDeadLine;

    public Drive(int driveId, String companyName, String jobRole, double ctcLpa, double minCgpaRequired, Date applicationDeadLine) {
        this.driveId = driveId;
        this.companyName = companyName;
        this.jobRole = jobRole;
        this.ctcLpa = ctcLpa;
        this.minCgpaRequired = minCgpaRequired;
        this.applicationDeadLine = applicationDeadLine;
    }

    public int getDriveId() {
        return driveId;
    }

    public void setDriveId(int driveId) {
        this.driveId = driveId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public double getCtcLpa() {
        return ctcLpa;
    }

    public void setCtcLpa(double ctcLpa) {
        this.ctcLpa = ctcLpa;
    }

    public double getMinCgpaRequired() {
        return minCgpaRequired;
    }

    public void setMinCgpaRequired(double minCgpaRequired) {
        this.minCgpaRequired = minCgpaRequired;
    }

    public Date getApplicationDeadLine() {
        return applicationDeadLine;
    }

    public void setApplicationDeadLine(Date applicationDeadLine) {
        this.applicationDeadLine = applicationDeadLine;
    }
    
    
}

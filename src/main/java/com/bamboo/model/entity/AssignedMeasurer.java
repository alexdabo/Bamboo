/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.model.entity;

import java.util.Date;
import java.util.List;

/**
 *
 * @author alexander
 */
public class AssignedMeasurer {
    private Measurer measurer;
    private Date assignmentDate;
    private String status;

    public AssignedMeasurer() {
    }

    public AssignedMeasurer(Measurer measurer, Date assignmentDate, String status) {
        this.measurer = measurer;
        this.assignmentDate = assignmentDate;
        this.status = status;
    }

    public Measurer getMeasurer() {
        return measurer;
    }

    public void setMeasurer(Measurer measurer) {
        this.measurer = measurer;
    }

    public Date getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(Date assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\nAssignedMeasurer{" + "measurers=" + measurer + ", assignmentDate=" + assignmentDate + ", status=" + status + "}";
    }

   
    
    
    
}

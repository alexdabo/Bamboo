/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.model.entity;

import java.util.Date;

/**
 *
 * @author alexander
 */
public class Assigned {

    private Beneficiary beneficiary;
    private Measurer measurer;
    private Date assignmentDate;
    private String status;

    public Assigned() {
    }

    public Assigned(Beneficiary beneficiary, Measurer measurer, Date assignmentDate, String status) {
        this.beneficiary = beneficiary;
        this.measurer = measurer;
        this.assignmentDate = assignmentDate;
        this.status = status;
    }

    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
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
        return "Assigned{"
                + "beneficiary=" + beneficiary
                + ", waterMeter=" + measurer
                + ", assignmentDate=" + assignmentDate
                + ", status='" + status + '\''
                + '}';
    }
}

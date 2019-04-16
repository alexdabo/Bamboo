package com.bamboo.api.dto;

import java.util.Date;

public class AssignedDto {
    private int id;
    private Date assignmentDate;
    private String status;
    private double debt;
    private BeneficiaryDto beneficiary;
    private MeasurerDto measurer;

    public AssignedDto() {
    }

    public AssignedDto(int id, Date assignmentDate, String status, double debt, BeneficiaryDto beneficiary, MeasurerDto measurer) {
        this.id = id;
        this.assignmentDate = assignmentDate;
        this.status = status;
        this.debt = debt;
        this.beneficiary = beneficiary;
        this.measurer = measurer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    public BeneficiaryDto getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(BeneficiaryDto beneficiary) {
        this.beneficiary = beneficiary;
    }

    public MeasurerDto getMeasurer() {
        return measurer;
    }

    public void setMeasurer(MeasurerDto measurer) {
        this.measurer = measurer;
    }
}

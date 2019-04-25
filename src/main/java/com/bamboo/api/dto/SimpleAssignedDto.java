package com.bamboo.api.dto;

import java.util.Date;

public class SimpleAssignedDto {
    private int id;
    private Date assignmentDate;
    private String status;
    private double debt;
    private MeasurerDto measurer;

    public SimpleAssignedDto() {
    }

    public SimpleAssignedDto(int id, Date assignmentDate, String status, double debt, MeasurerDto measurer) {
        this.id = id;
        this.assignmentDate = assignmentDate;
        this.status = status;
        this.debt = debt;
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


    public MeasurerDto getMeasurer() {
        return measurer;
    }

    public void setMeasurer(MeasurerDto measurer) {
        this.measurer = measurer;
    }
}

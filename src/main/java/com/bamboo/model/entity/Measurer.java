/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.model.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author alexander
 */
public class Measurer {

    private int id;
    private String number;
    private Date installationDate;
    private Sap sap;
    private Status status;

    public Measurer() {
    }

    public Measurer(int id, String number, Date installationDate, Sap sap, Status status) {
        this.id = id;
        this.number = number;
        this.installationDate = installationDate;
        this.sap = sap;
        this.status = status;
    }

    public Measurer(int id, String number, String installationDate, Sap sap, Status status) throws ParseException {
        this.id = id;
        this.number = number;
        toDate(installationDate);
        this.sap = sap;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }

    public void setInstallationDate(String installationDate) throws ParseException {
        toDate(installationDate);
    }

    public Sap getSap() {
        return sap;
    }

    public void setSap(Sap sap) {
        this.sap = sap;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private void toDate(String dateOfIssue) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.installationDate = sdf.parse(dateOfIssue);
        } catch (ParseException e) {
            throw e;
        }
    }

    @Override
    public String toString() {
        return "Measurer{"
                + "id=" + id
                + ", number=" + number
                + ", installationDate=" + installationDate
                + ", sap=" + sap
                + ", status=" + status
                + '}';
    }
}

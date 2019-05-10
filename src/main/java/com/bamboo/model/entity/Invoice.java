/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.model.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author alexander
 */
public class Invoice {

    private int id;
    private int number;
    private Date dateOfIssue;
    private Double totalToPay;
    private boolean payed;
    private int beneficiary;
    private int debtcollector;

    public Invoice() {
    }

    public Invoice(int id, int number, Date dateOfIssue, Double totalToPay, boolean payed, int beneficiary, int debtcollector) {
        this.id = id;
        this.number = number;
        this.dateOfIssue = dateOfIssue;
        this.totalToPay = totalToPay;
        this.payed = payed;
        this.beneficiary = beneficiary;
        this.debtcollector = debtcollector;
    }

    public Invoice(int id, int number, String dateOfIssue, Double totalToPay, boolean payed, int beneficiary, int debtcollector) throws ParseException {
        this.id = id;
        this.number = number;
        toDate(dateOfIssue);
        this.totalToPay = totalToPay;
        this.payed = payed;
        this.beneficiary = beneficiary;
        this.debtcollector = debtcollector;
    }

    public int getDebtcollector() {
        return debtcollector;
    }

    public void setDebtcollector(int debtcollector) {
        this.debtcollector = debtcollector;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) throws ParseException {
        toDate(dateOfIssue);
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Double getTotalToPay() {
        return totalToPay;
    }

    public void setTotalToPay(Double totalToPay) {
        this.totalToPay = totalToPay;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setIsPayed(boolean payed) {
        this.payed = payed;
    }

    public int getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(int beneficiary) {
        this.beneficiary = beneficiary;
    }

    private void toDate(String dateOfIssue) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("es_ES"));

        try {
            this.dateOfIssue = sdf.parse(dateOfIssue);
        } catch (ParseException e) {
            throw e;
        }
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", totalToPay=" + totalToPay +
                ", payed=" + payed +
                ", beneficiary=" + beneficiary +
                ", debtcollector=" + debtcollector +
                '}';
    }
}

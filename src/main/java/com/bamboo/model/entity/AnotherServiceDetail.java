/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.model.entity;

/**
 * @author alexander
 */
public class AnotherServiceDetail {

    private int id;
    private int invoice;
    private int service;
    private double total;

    public AnotherServiceDetail() {
    }

    public AnotherServiceDetail(int id, int invoice, int service, double total) {
        this.id = id;
        this.invoice = invoice;
        this.service = service;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoice() {
        return invoice;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "AnotherServiceDetail{" +
                "id=" + id +
                ", invoice=" + invoice +
                ", service=" + service +
                ", total=" + total +
                '}';
    }
}

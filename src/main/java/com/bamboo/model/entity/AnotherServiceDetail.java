/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.model.entity;

/**
 *
 * @author alexander
 */
public class AnotherServiceDetail {

    private Invoice invoice;
    private AnotherService service;
    private double total;

    public AnotherServiceDetail() {
    }

    public AnotherServiceDetail(Invoice invoice, AnotherService service, double total) {
        this.invoice = invoice;
        this.service = service;
        this.total = total;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public AnotherService getService() {
        return service;
    }

    public void setService(AnotherService service) {
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
        return "AnotherServiceDetail{"
                + "invoice=" + invoice
                + ", service=" + service
                + ", total=" + total
                + '}';
    }
}

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
public class SapDetail {

    private Invoice invoice;
    private Uptake uptake;

    public SapDetail() {
    }

    public SapDetail(Invoice invoice, Uptake uptake) {
        this.invoice = invoice;
        this.uptake = uptake;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Uptake getUptake() {
        return uptake;
    }

    public void setUptake(Uptake uptake) {
        this.uptake = uptake;
    }

    @Override
    public String toString() {
        return "SapDetail{"
                + "invoice=" + invoice
                + ", uptake=" + uptake
                + '}';
    }
}

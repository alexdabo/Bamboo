/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.model.entity;

import java.util.List;

/**
 *
 * @author alexander
 */
public class SapDetail {

    private Invoice invoice;
    private List<Uptake> uptakes;

    public SapDetail() {
    }

    public SapDetail(Invoice invoice, List<Uptake> uptakes) {
        this.invoice = invoice;
        this.uptakes = uptakes;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public List<Uptake> getUptakes() {
        return uptakes;
    }

    public void setUptakes(List<Uptake> uptakes) {
        this.uptakes = uptakes;
    }
}

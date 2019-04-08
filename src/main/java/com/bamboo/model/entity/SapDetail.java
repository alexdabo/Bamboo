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

    private int invoice;
    private int uptake;

    public SapDetail() {
    }

    public SapDetail(int invoice, int uptake) {
        this.invoice = invoice;
        this.uptake = uptake;
    }

    public int getInvoice() {
        return invoice;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
    }

    public int getUptake() {
        return uptake;
    }

    public void setUptake(int uptake) {
        this.uptake = uptake;
    }
}

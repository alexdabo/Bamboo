/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.model.entity.dependent;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author alexander
 */
public class Uptake {

    private int id;
    private Date dateTaked;
    private double lastValueTaken;
    private double currentValueTaken;
    private double baseVolume;
    private double basePrice;
    private double extraPrice;
    private double volumeExceeded;
    private double volumeConsumed;
    private double totalPrice;
    private boolean billed;
    private Measurer measurer;

    public Uptake() {
    }

    public Uptake(int id, Date dateTaked, double lastValueTaken, double currentValueTaken, double baseVolume, double basePrice, double extraPrice, double volumeExceeded, double volumeConsumed, double totalPrice, boolean billed, Measurer measurer) {
        this.id = id;
        this.dateTaked = dateTaked;
        this.lastValueTaken = lastValueTaken;
        this.currentValueTaken = currentValueTaken;
        this.baseVolume = baseVolume;
        this.basePrice = basePrice;
        this.extraPrice = extraPrice;
        this.volumeExceeded = volumeExceeded;
        this.volumeConsumed = volumeConsumed;
        this.totalPrice = totalPrice;
        this.billed = billed;
        this.measurer = measurer;
    }

    public Uptake(int id, String dateTaked, double lastValueTaken, double currentValueTaken, double baseVolume, double basePrice, double extraPrice, double volumeExceeded, double volumeConsumed, double totalPrice, boolean billed, Measurer measurer) throws ParseException {
        this.id = id;
        toDate(dateTaked);
        this.lastValueTaken = lastValueTaken;
        this.currentValueTaken = currentValueTaken;
        this.baseVolume = baseVolume;
        this.basePrice = basePrice;
        this.extraPrice = extraPrice;
        this.volumeExceeded = volumeExceeded;
        this.volumeConsumed = volumeConsumed;
        this.totalPrice = totalPrice;
        this.billed = billed;
        this.measurer = measurer;
    }


    public Date getDatetaked() {
        return dateTaked;
    }

    public void setDatetaked(Date dateTaked) {
        this.dateTaked = dateTaked;
    }

    public void setDatetaked(String date) throws ParseException {
        toDate(date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLastValueTaken() {
        return lastValueTaken;
    }

    public void setLastValueTaken(double lastValueTaken) {
        this.lastValueTaken = lastValueTaken;
    }

    public double getCurrentValueTaken() {
        return currentValueTaken;
    }

    public void setCurrentValueTaken(double currentValueTaken) {
        this.currentValueTaken = currentValueTaken;
    }

    public double getBaseVolume() {
        return baseVolume;
    }

    public void setBaseVolume(double baseVolume) {
        this.baseVolume = baseVolume;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(double extraPrice) {
        this.extraPrice = extraPrice;
    }

    public double getVolumeExceeded() {
        return volumeExceeded;
    }

    public void setVolumeExceeded(double volumeExceeded) {
        this.volumeExceeded = volumeExceeded;
    }

    public double getVolumeConsumed() {
        return volumeConsumed;
    }

    public void setVolumeConsumed(double volumeConsumed) {
        this.volumeConsumed = volumeConsumed;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isBilled() {
        return billed;
    }

    public void setBilled(boolean billed) {
        this.billed = billed;
    }

    public Measurer getMeasurer() {
        return measurer;
    }

    public void setMeasurer(Measurer measurer) {
        this.measurer = measurer;
    }

    private void toDate(String dateTaked) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.dateTaked = sdf.parse(dateTaked);
        } catch (ParseException e) {
            throw e;
        }
    }


    @Override
    public String toString() {
        return "Uptake{" +
                "id=" + id +
                ", dateTaked=" + dateTaked +
                ", lastValueTaken=" + lastValueTaken +
                ", currentValueTaken=" + currentValueTaken +
                ", baseVolume=" + baseVolume +
                ", basePrice=" + basePrice +
                ", extraPrice=" + extraPrice +
                ", volumeExceeded=" + volumeExceeded +
                ", volumeConsumed=" + volumeConsumed +
                ", totalPrice=" + totalPrice +
                ", billed=" + billed +
                ", measurer=" + measurer +
                '}';
    }
}

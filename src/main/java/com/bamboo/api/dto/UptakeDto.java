package com.bamboo.api.dto;

import com.bamboo.model.entity.Measurer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UptakeDto {
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

    public UptakeDto() {
    }

    public UptakeDto(int id, Date dateTaked, double lastValueTaken, double currentValueTaken, double baseVolume, double basePrice, double extraPrice, double volumeExceeded, double volumeConsumed, double totalPrice, boolean billed) {
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
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateTaked() {
        return dateTaked;
    }

    public void setDateTaked(Date dateTaked) {
        this.dateTaked = dateTaked;
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


}

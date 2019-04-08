/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.api.dto;

/**
 *
 * @author alexander
 */
public class SapDto {

    private int id;
    private String name;
    private int baseVolume;
    private Double basePrice;
    private Double extraPrice;

    public SapDto() {
    }

    public SapDto(int id, String name, int baseVolume, Double basePrice, Double extraPrice) {
        this.id = id;
        this.name = name;
        this.baseVolume = baseVolume;
        this.basePrice = basePrice;
        this.extraPrice = extraPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaseVolume() {
        return baseVolume;
    }

    public void setBaseVolume(int baseVolume) {
        this.baseVolume = baseVolume;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Double getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(Double extraPrice) {
        this.extraPrice = extraPrice;
    }

}

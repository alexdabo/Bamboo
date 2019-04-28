/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.model.entity;

/**
 * @author alexander
 */
public class Beneficiary {

    private int id;
    private String dni;
    private String lastName;
    private String firstName;
    private String address;
    private String telephone;
    private int village;
    private String placeReference;

    public Beneficiary() {
    }

    public Beneficiary(int id, String dni, String lastName, String firstName, String address, String telephone, int village, String placeReference) {
        this.id = id;
        this.dni = dni;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.telephone = telephone;
        this.village = village;
        this.placeReference = placeReference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getVillage() {
        return village;
    }

    public void setVillage(int village) {
        this.village = village;
    }

    public String getPlaceReference() {
        return placeReference;
    }

    public void setPlaceReference(String placeReference) {
        this.placeReference = placeReference;
    }

    @Override
    public String toString() {
        return "Beneficiary{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", village=" + village +
                ", placeReference='" + placeReference + '\'' +
                '}';
    }
}

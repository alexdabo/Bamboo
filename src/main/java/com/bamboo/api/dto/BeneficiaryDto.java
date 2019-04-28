package com.bamboo.api.dto;

import com.bamboo.model.entity.Village;

public class BeneficiaryDto {
    private int id;
    private String dni;
    private String lastName;
    private String firstName;
    private String address;
    private String telephone;
    private String placeReference;
    private VillageDto village;

    public BeneficiaryDto() {
    }

    public BeneficiaryDto(int id, String dni, String lastName, String firstName, String address, String telephone, VillageDto village, String placeReference) {
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

    public VillageDto getVillage() {
        return village;
    }

    public void setVillage(VillageDto village) {
        this.village = village;
    }

    public String getPlaceReference() {
        return placeReference;
    }

    public void setPlaceReference(String placeReference) {
        this.placeReference = placeReference;
    }

    public String getFullName() {
        return getLastName() + " " + getFirstName();
    }

    @Override
    public String toString() {
        return "BeneficiaryDto{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", placeReference='" + placeReference + '\'' +
                ", village=" + village +
                '}';
    }
}

package com.bamboo.api.dto;

public class EntityDto {
    private String province;
    private String canton;
    private String community;
    private String address;
    private String telephone;
    private String email;
    private String ruc;

    public EntityDto() {
    }

    public EntityDto(String province, String canton, String community, String address, String telephone, String email, String ruc) {
        this.province = province;
        this.canton = canton;
        this.community = community;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.ruc = ruc;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    @Override
    public String toString() {
        return "EntityDto{" +
                "province='" + province + '\'' +
                ", canton='" + canton + '\'' +
                ", community='" + community + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", ruc='" + ruc + '\'' +
                '}';
    }
}

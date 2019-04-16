package com.bamboo.api.dto;

import com.bamboo.api.dto.RoleDto;
import com.bamboo.model.entity.User;

public class UserDto {


    private int id;
    private String userName;
    private String password;
    private String email;
    private String dni;
    private String firstName;
    private String lastName;
    private String telephone;
    private String address;
    private RoleDto role;

    public UserDto() {
    }

    public UserDto(int id, String userName, String password, String email, String dni, String firstName, String lastName, String telephone, String address, RoleDto role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.address = address;
        this.role = role;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

}

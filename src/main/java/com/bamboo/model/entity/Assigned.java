/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author alexander
 */
public class Assigned {

    private Beneficiary beneficiary;
    private List<AssignedMeasurer> assigneds;

    public Assigned() {
        beneficiary = new Beneficiary();
        assigneds = new ArrayList<>();
    }

    public Assigned(Beneficiary beneficiary, List<AssignedMeasurer> assigneds) {
        this.beneficiary = beneficiary;
        this.assigneds = assigneds;
    }

    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }

    public List<AssignedMeasurer> getAssigneds() {
        return assigneds;
    }

    public void setAssigneds(List<AssignedMeasurer> assigneds) {
        this.assigneds = assigneds;
    }

    public void addAssigned(AssignedMeasurer assigned){
        assigneds = new ArrayList<>();
        this.assigneds.add(assigned);
    }

    @Override
    public String toString() {
        return "Assigned{" +
                "beneficiary=" + beneficiary +
                ", assigneds=" + assigneds +
                '}';
    }
}

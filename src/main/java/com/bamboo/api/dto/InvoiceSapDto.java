package com.bamboo.api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceSapDto extends InvoiceDto {
    private int id;
    private List<UptakeDto> detail;

    public InvoiceSapDto() {

    }

    public InvoiceSapDto(int invoiceId, int number, Date dateOfIssue, Double totalToPay, boolean payed, BeneficiaryDto beneficiary, UserDto debtcollector, int id, List<UptakeDto> detail) {
        super(invoiceId, number, dateOfIssue, totalToPay, payed, beneficiary, debtcollector);
        this.id = id;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<UptakeDto> getDetail() {
        return detail;
    }

    public void setDetail(List<UptakeDto> detail) {
        this.detail = detail;
    }

    public void addDetail(UptakeDto uptake) {
        this.detail = new ArrayList<>();
        this.detail.add(uptake);
    }
}

package com.bamboo.api.dto;

import com.bamboo.model.entity.Sap;
import com.bamboo.model.entity.Status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MeasurerDto {
    private int id;
    private String number;
    private Date installationDate;
    private SapDto sapDto;
    private StatusDto statusDto;
    private List<UptakeDto> uptakes;

    public MeasurerDto(){}

    public MeasurerDto(int id, String number, Date installationDate, SapDto sapDto, StatusDto statusDto, List<UptakeDto> uptakesDtos) {
        this.id = id;
        this.number = number;
        this.installationDate = installationDate;
        this.sapDto = sapDto;
        this.statusDto = statusDto;
        this.uptakes = uptakes;
    }

    public MeasurerDto(int id, String number, String installationDate, SapDto sapDto, StatusDto statusDto, List<UptakeDto> uptakes) throws ParseException {
        this.id = id;
        this.number = number;
        toDate(installationDate);
        this.sapDto = sapDto;
        this.statusDto = statusDto;
        this.uptakes = uptakes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }

    public void setInstallationDate(String installationDate) throws ParseException {
        toDate(installationDate);
    }

    public SapDto getSap() {
        return sapDto;
    }

    public void setSap(SapDto sapDto) {
        this.sapDto = sapDto;
    }

    public StatusDto getStatus() {
        return statusDto;
    }

    public void setStatus(StatusDto statusDto) {
        this.statusDto = statusDto;
    }

    public List<UptakeDto> getUptakes() {
        return uptakes;
    }

    public void setUptakes(List<UptakeDto> uptakes) {
        this.uptakes = uptakes;
    }

    private void toDate(String dateOfIssue) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.installationDate = sdf.parse(dateOfIssue);
        } catch (ParseException e) {
            throw e;
        }
    }
}

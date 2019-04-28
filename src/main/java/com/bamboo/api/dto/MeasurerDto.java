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
    private SapDto sap;
    private StatusDto status;
    private List<UptakeDto> uptakes;

    public MeasurerDto() {
    }

    public MeasurerDto(int id, String number, Date installationDate, SapDto sap, StatusDto status, List<UptakeDto> uptakesDtos) {
        this.id = id;
        this.number = number;
        this.installationDate = installationDate;
        this.sap = sap;
        this.status = status;
        this.uptakes = uptakes;
    }

    public MeasurerDto(int id, String number, String installationDate, SapDto sap, StatusDto status, List<UptakeDto> uptakes) throws ParseException {
        this.id = id;
        this.number = number;
        toDate(installationDate);
        this.sap = sap;
        this.status = status;
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
        return sap;
    }

    public void setSap(SapDto sap) {
        this.sap = sap;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "MeasurerDto{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", installationDate=" + installationDate +
                ", sap=" + sap +
                ", status=" + status +
                ", uptakes=" + uptakes +
                '}';
    }
}

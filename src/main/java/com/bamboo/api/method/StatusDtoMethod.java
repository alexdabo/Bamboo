package com.bamboo.api.method;

import com.bamboo.api.dto.StatusDto;
import com.bamboo.model.entity.Status;
import com.bamboo.model.entity.Status;
import com.bamboo.model.method.StatusImpl;

import java.util.ArrayList;
import java.util.List;

public class StatusDtoMethod {

    public boolean save(StatusDto statusDto) throws Exception {
        boolean affected = false;
        StatusImpl statusImpl = new StatusImpl();
        try {
            if (statusImpl.save(getStatus(statusDto))) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }


    public StatusDto findById(int id) throws Exception {
        StatusDto statusDto = null;
        StatusImpl statusImpl = new StatusImpl();
        try {
            statusDto = getStatusDto(statusImpl.findById(id));
        } catch (Exception e) {
            throw e;
        }
        return statusDto;
    }

    public List<StatusDto> find() throws Exception {
        List<StatusDto> list = new ArrayList<>();
        StatusImpl statusImpl = new StatusImpl();
        try {
            for (Status status : statusImpl.find()) {
                list.add(getStatusDto(status));
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public boolean update(StatusDto statusDto) throws Exception {
        boolean affected = false;
        StatusImpl statusImpl = new StatusImpl();
        try {
            if (statusImpl.update(getStatus(statusDto))) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }

    public boolean delete(StatusDto statusDto) throws Exception {
        boolean affected = false;
        StatusImpl statusImpl = new StatusImpl();
        try {
            if (statusImpl.delete(getStatus(statusDto))) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }

    private Status getStatus(StatusDto statusDto) {
        Status status = new Status();
        status.setId(statusDto.getId());
        status.setName(statusDto.getName());
        return status;
    }

    private StatusDto getStatusDto(Status status) {
        StatusDto statusDto = new StatusDto();
        statusDto.setId(status.getId());
        statusDto.setName(status.getName());
        return statusDto;
    }
}

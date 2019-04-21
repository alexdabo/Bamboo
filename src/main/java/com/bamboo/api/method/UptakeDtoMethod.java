package com.bamboo.api.method;

import com.bamboo.api.dto.UptakeDto;
import com.bamboo.model.entity.Uptake;
import com.bamboo.model.method.UptakeImpl;

import java.util.ArrayList;
import java.util.List;

public class UptakeDtoMethod {

    public List<UptakeDto> findByMeasurer(int measurerId) throws Exception {
        List<UptakeDto> uptakesDto = new ArrayList<>();

        UptakeImpl uptakeImpl = new UptakeImpl();
        for (Uptake uptake : uptakeImpl.findByMeasurer(measurerId)) {
            uptakesDto.add(getUptakeDto(uptake));
        }

        return uptakesDto;
    }

    public List<UptakeDto> findNotBilled(int measurerId) throws Exception {
        List<UptakeDto> uptakesDto = new ArrayList<>();

        UptakeImpl uptakeImpl = new UptakeImpl();
        for (Uptake uptake : uptakeImpl.findNotBilled(measurerId)) {
            uptakesDto.add(getUptakeDto(uptake));
        }

        return uptakesDto;
    }

    public List<UptakeDto> findByInvoice(int invoiceId) throws Exception {
        List<UptakeDto> uptakesDto = new ArrayList<>();

        UptakeImpl uptakeImpl = new UptakeImpl();
        for (Uptake uptake : uptakeImpl.findByInvoice(invoiceId)) {
            uptakesDto.add(getUptakeDto(uptake));
        }

        return uptakesDto;
    }

    public UptakeDto save(UptakeDto uptakeDto, int measurerId) throws Exception {
        UptakeDto newUptake = null;
        UptakeImpl uptakeImpl = new UptakeImpl();
        Uptake uptake = getUptake(uptakeDto);
        uptake.setMeasurer(measurerId);
        try {
            newUptake = getUptakeDto(uptakeImpl.save(uptake));
        } catch (Exception e) {
            throw e;
        }
        return newUptake;
    }

    public boolean update(UptakeDto uptakeDto) throws Exception {
        boolean updated = false;
        UptakeImpl uptakeImpl = new UptakeImpl();
        Uptake uptake = getUptake(uptakeDto);
        if (uptakeImpl.update(uptake)) {
            updated = true;
        }
        return updated;
    }

    public boolean delete(int id) throws Exception {
        boolean updated = false;
        UptakeImpl uptakeImpl = new UptakeImpl();
        if (uptakeImpl.delete(id)) {
            updated = true;
        }
        return updated;
    }

    private UptakeDto getUptakeDto(Uptake uptake) {
        UptakeDto uptakeDto = new UptakeDto();
        uptakeDto.setId(uptake.getId());
        uptakeDto.setDateTaked(uptake.getDatetaked());
        uptakeDto.setLastValueTaken(uptake.getLastValueTaken());
        uptakeDto.setCurrentValueTaken(uptake.getCurrentValueTaken());
        uptakeDto.setBaseVolume(uptake.getBaseVolume());
        uptakeDto.setBasePrice(uptake.getBasePrice());
        uptakeDto.setExtraPrice(uptake.getExtraPrice());
        uptakeDto.setVolumeExceeded(uptake.getVolumeExceeded());
        uptakeDto.setVolumeConsumed(uptake.getVolumeConsumed());
        uptakeDto.setTotalPrice(uptake.getTotalPrice());
        uptakeDto.setBilled(uptake.isBilled());
        return uptakeDto;
    }

    private Uptake getUptake(UptakeDto uptakeDto) {
        Uptake uptake = new Uptake();
        uptake.setId(uptakeDto.getId());
        uptake.setDatetaked(uptakeDto.getDateTaked());
        uptake.setLastValueTaken(uptakeDto.getLastValueTaken());
        uptake.setCurrentValueTaken(uptakeDto.getCurrentValueTaken());
        uptake.setBaseVolume(uptakeDto.getBaseVolume());
        uptake.setBasePrice(uptakeDto.getBasePrice());
        uptake.setExtraPrice(uptakeDto.getExtraPrice());
        uptake.setVolumeExceeded(uptakeDto.getVolumeExceeded());
        uptake.setVolumeConsumed(uptakeDto.getVolumeConsumed());
        uptake.setTotalPrice(uptakeDto.getTotalPrice());
        uptake.setBilled(uptakeDto.isBilled());
        return uptake;
    }

}

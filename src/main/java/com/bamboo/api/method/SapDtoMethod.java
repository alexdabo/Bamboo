package com.bamboo.api.method;

import com.bamboo.api.dto.SapDto;
import com.bamboo.model.entity.Sap;
import com.bamboo.model.method.SapImpl;

import java.util.ArrayList;
import java.util.List;

public class SapDtoMethod {

    public boolean save(SapDto sapDto) throws Exception {
        boolean affected = false;
        SapImpl sapImpl = new SapImpl();
        try {
            if (sapImpl.save(getSap(sapDto))) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }


    public SapDto findById(int id) throws Exception {
        SapDto sapDto = null;
        SapImpl sapImpl = new SapImpl();
        try {
            sapDto = getSapDto(sapImpl.findById(id));
        } catch (Exception e) {
            throw e;
        }
        return sapDto;
    }

    public List<SapDto> find() throws Exception {
        List<SapDto> list = new ArrayList<>();
        SapImpl sapImpl = new SapImpl();
        try {
            for (Sap sap : sapImpl.find()) {
                list.add(getSapDto(sap));
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public boolean update(SapDto sapDto) throws Exception {
        boolean affected = false;
        SapImpl sapImpl = new SapImpl();
        try {
            if (sapImpl.update(getSap(sapDto))) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }

    public boolean delete(SapDto sapDto) throws Exception {
        boolean affected = false;
        SapImpl sapImpl = new SapImpl();
        try {
            if (sapImpl.delete(getSap(sapDto))) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }

    private Sap getSap(SapDto sapDto) {
        Sap sap = new Sap();
        sap.setId(sapDto.getId());
        sap.setName(sapDto.getName());
        sap.setBasePrice(sapDto.getBasePrice());
        sap.setBaseVolume(sapDto.getBaseVolume());
        sap.setExtraPrice(sapDto.getExtraPrice());
        return sap;
    }

    private SapDto getSapDto(Sap sap) {
        SapDto sapDto = new SapDto();
        sapDto.setId(sap.getId());
        sapDto.setName(sap.getName());
        sapDto.setBasePrice(sap.getBasePrice());
        sapDto.setBaseVolume(sap.getBaseVolume());
        sapDto.setExtraPrice(sap.getExtraPrice());
        return sapDto;
    }
}

package com.bamboo.api.method;

import com.bamboo.api.dto.AnotherServiceDto;
import com.bamboo.model.entity.AnotherService;
import com.bamboo.model.method.AnotherServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class AnotherServiceDtoMethod {

    public boolean save(AnotherServiceDto anotherServiceDto) throws Exception {
        boolean affected = false;
        AnotherServiceImpl anotherServiceImpl = new AnotherServiceImpl();
        try {
            if (anotherServiceImpl.save(getAnotherService(anotherServiceDto))) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }


    public AnotherServiceDto findById(int id) throws Exception {
        AnotherServiceDto anotherServiceDto = null;
        AnotherServiceImpl anotherServiceImpl = new AnotherServiceImpl();
        try {
            anotherServiceDto = getAnotherServiceDto(anotherServiceImpl.findById(id));
        } catch (Exception e) {
            throw e;
        }
        return anotherServiceDto;
    }

    public List<AnotherServiceDto> find() throws Exception {
        List<AnotherServiceDto> list = new ArrayList<>();
        AnotherServiceImpl anotherServiceImpl = new AnotherServiceImpl();
        try {
            for (AnotherService anotherService : anotherServiceImpl.find()) {
                list.add(getAnotherServiceDto(anotherService));
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public boolean update(AnotherServiceDto anotherServiceDto) throws Exception {
        boolean affected = false;
        AnotherServiceImpl anotherServiceImpl = new AnotherServiceImpl();
        try {
            if (anotherServiceImpl.update(getAnotherService(anotherServiceDto))) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }

    public boolean delete(int id) throws Exception {
        boolean affected = false;
        AnotherServiceImpl anotherServiceImpl = new AnotherServiceImpl();
        try {
            if (anotherServiceImpl.delete(id)) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }

    private AnotherService getAnotherService(AnotherServiceDto anotherServiceDto) {
        AnotherService anotherService = new AnotherService();
        anotherService.setId(anotherServiceDto.getId());
        anotherService.setName(anotherServiceDto.getName());
        anotherService.setPrice(anotherServiceDto.getPrice());
        return anotherService;
    }

    private AnotherServiceDto getAnotherServiceDto(AnotherService anotherService) {
        AnotherServiceDto anotherServiceDto = new AnotherServiceDto();
        anotherServiceDto.setId(anotherService.getId());
        anotherServiceDto.setName(anotherService.getName());
        anotherServiceDto.setPrice(anotherService.getPrice());
        return anotherServiceDto;
    }
}

package com.bamboo.api.method;

import com.bamboo.api.dto.VillageDto;
import com.bamboo.model.entity.Village;
import com.bamboo.model.method.VillageImpl;

import java.util.ArrayList;
import java.util.List;

public class VillageDtoMethod {

    public boolean save(VillageDto villageDto) throws Exception {
        boolean affected = false;
        VillageImpl villageImpl = new VillageImpl();
        try {
            if (villageImpl.save(getVillage(villageDto))) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }


    public VillageDto findById(int id) throws Exception {
        VillageDto villageDto = null;
        VillageImpl villageImpl = new VillageImpl();
        try {
            villageDto = getVillageDto(villageImpl.findById(id));
        } catch (Exception e) {
            throw e;
        }
        return villageDto;
    }

    public List<VillageDto> find() throws Exception {
        List<VillageDto> list = new ArrayList<>();
        VillageImpl villageImpl = new VillageImpl();
        try {
            for (Village village : villageImpl.find()) {
                list.add(getVillageDto(village));
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public boolean update(VillageDto villageDto) throws Exception {
        boolean affected = false;
        VillageImpl villageImpl = new VillageImpl();
        try {
            if (villageImpl.update(getVillage(villageDto))) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }

    public boolean delete(int id) throws Exception {
        boolean affected = false;
        VillageImpl villageImpl = new VillageImpl();
        try {
            if (villageImpl.delete(id)) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }

    private Village getVillage(VillageDto villageDto) {
        Village village = new Village();
        village.setId(villageDto.getId());
        village.setName(villageDto.getName());
        return village;
    }

    private VillageDto getVillageDto(Village village) {
        VillageDto villageDto = new VillageDto();
        villageDto.setId(village.getId());
        villageDto.setName(village.getName());
        return villageDto;
    }
}

package com.bamboo.api.method;

import com.bamboo.api.dto.EntityDto;
import com.bamboo.model.entity.Entity;
import com.bamboo.model.method.EntityImpl;

import java.util.ArrayList;
import java.util.List;

public class EntityDtoMethod {

    public EntityDto find() throws Exception {
        EntityDto entityDto = null;
        EntityImpl entityImpl = new EntityImpl();
        try {
            entityDto= getEntityDto(entityImpl.find());
        } catch (Exception e) {
            throw e;
        }
        return entityDto;
    }

    public boolean update(EntityDto entityDto) throws Exception {
        boolean affected = false;
        EntityImpl entityImpl = new EntityImpl();
        try {
            affected = entityImpl.update(getEntity(entityDto));
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }


    private Entity getEntity(EntityDto entityDto) {
        Entity entity = new Entity();
        entity.setProvince(entityDto.getProvince());
        entity.setCanton(entityDto.getCanton());
        entity.setCommunity(entityDto.getCommunity());
        entity.setAddress(entityDto.getAddress());
        entity.setTelephone(entityDto.getTelephone());
        entity.setEmail(entityDto.getEmail());
        entity.setRuc(entityDto.getRuc());
        return entity;
    }

    private EntityDto getEntityDto(Entity entity) {
        EntityDto entityDto = new EntityDto();
        entityDto.setProvince(entity.getProvince());
        entityDto.setCanton(entity.getCanton());
        entityDto.setCommunity(entity.getCommunity());
        entityDto.setAddress(entity.getAddress());
        entityDto.setTelephone(entity.getTelephone());
        entityDto.setEmail(entity.getEmail());
        entityDto.setRuc(entity.getRuc());
        return entityDto;
    }
}

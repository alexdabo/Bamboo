package com.bamboo.api.method;

import com.bamboo.api.dto.RoleDto;
import com.bamboo.model.entity.Role;
import com.bamboo.model.method.RoleImpl;

import java.util.ArrayList;
import java.util.List;

public class RoleDtoMethod {

    public RoleDto findById(int id) throws Exception {
        RoleDto roleDto = null;
        RoleImpl roleImpl = new RoleImpl();
        try {
            roleDto = getRoleDto(roleImpl.findById(id));
        } catch (Exception e) {
            throw e;
        }
        return roleDto;
    }

    public List<RoleDto> find() throws Exception {
        List<RoleDto> list = new ArrayList<>();
        RoleImpl roleImpl = new RoleImpl();
        try {
            for (Role role : roleImpl.find()) {
                list.add(getRoleDto(role));
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }


    private Role getRole(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        return role;
    }

    private RoleDto getRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }
}

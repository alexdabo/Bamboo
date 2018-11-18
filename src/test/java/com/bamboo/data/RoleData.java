/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.data;

import com.bamboo.model.entity.independent.Role;
import com.bamboo.model.method.independent.RoleImpl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexander
 */
public final class RoleData {

    private final RoleImpl roleImpl = new RoleImpl();
    private final Role role = new Role(1000, "Auditor");

    public boolean save() {
        boolean saved = false;
        try {
            saved = roleImpl.save(role);
            if (saved) {
                System.out.println("Saved:   " + role);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return saved;
    }

    public List<Role> find() {
        List<Role> list = new ArrayList<>();
        try {
            list = roleImpl.find();
            if (list.size() > 0) {
                System.out.println("Found: ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t " + list.get(i));
                }
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Role findById() {
        Role role1 = null;
        try {
            role1 = roleImpl.findById(role.getId());
            if (role1 != null) {
                System.out.println("By Id:   " + role1);
            }
        } catch (Exception e) {
        }
        return role1;
    }

    public boolean update() {
        boolean updated = false;
        role.setName("Auditor Encargado");
        try {
            updated = roleImpl.update(role);
            if (updated) {
                System.out.println("Updated: " + role);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return updated;
    }

    public boolean delete() {
        boolean deleted = false;
        try {
            deleted = roleImpl.delete(role);
            if (deleted) {
                System.out.println("Deleted: " + role);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return deleted;

    }
}

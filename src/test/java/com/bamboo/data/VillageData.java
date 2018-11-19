/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.data;

import com.bamboo.model.entity.independent.Village;
import com.bamboo.model.method.independent.VillageImpl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexander
 */
public final class VillageData {

    private final VillageImpl villageImpl = new VillageImpl();
    private final Village village = new Village(1000, "San Miguel");

    public boolean save() {
        boolean saved = false;
        try {
            saved = villageImpl.save(village);
            if (saved) {
                System.out.println("Saved:   " + village);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return saved;
    }

    public List<Village> find() {
        List<Village> list = new ArrayList<>();
        try {
            list = villageImpl.find();
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

    public Village findById() {
        Village village1 = null;
        try {
            village1 = villageImpl.findById(village.getId());
            if (village1 != null) {
                System.out.println("By Id:   " + village1);
            }
        } catch (Exception e) {
        }
        return village1;
    }

    public boolean update() {
        boolean updated = false;
        village.setName("Langos San Miguel");
        try {
            updated = villageImpl.update(village);
            if (updated) {
                System.out.println("Updated: " + village);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return updated;
    }

    public boolean delete() {
        boolean deleted = false;
        try {
            deleted = villageImpl.delete(village);
            if (deleted) {
                System.out.println("Deleted: " + village);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return deleted;

    }
}

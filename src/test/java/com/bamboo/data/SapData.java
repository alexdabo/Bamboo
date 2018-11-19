/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.data;

import com.bamboo.model.entity.independent.Sap;
import com.bamboo.model.method.independent.SapImpl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexander
 */
public final class SapData {

    private final SapImpl sapImpl = new SapImpl();
    private final Sap sap = new Sap(1000, "SAP Empresarial", 10, 10.0, 0.15);

    public boolean save() {
        boolean saved = false;
        try {
            saved = sapImpl.save(sap);
            if (saved) {
                System.out.println("Saved:   " + sap);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return saved;
    }

    public List<Sap> find() {
        List<Sap> list = new ArrayList<>();
        try {
            list = sapImpl.find();
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

    public Sap findById() {
        Sap sap1 = null;
        try {
            sap1 = sapImpl.findById(sap.getId());
            if (sap1 != null) {
                System.out.println("By Id:   " + sap1);
            }
        } catch (Exception e) {
        }
        return sap1;
    }

    public boolean update() {
        boolean updated = false;
        sap.setName("SAP Empresarial premium");
        try {
            updated = sapImpl.update(sap);
            if (updated) {
                System.out.println("Updated: " + sap);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return updated;
    }

    public boolean delete() {
        boolean deleted = false;
        try {
            deleted = sapImpl.delete(sap);
            if (deleted) {
                System.out.println("Deleted: " + sap);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return deleted;

    }
}

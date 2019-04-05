/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.data;

import com.bamboo.model.entity.Uptake;
import com.bamboo.model.method.UptakeImpl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexander
 */
public final class UptakeData {

    private final UptakeImpl uptakeImpl = new UptakeImpl();
    private final MeasurerData measurerData = new MeasurerData();
    private final Uptake uptake;

    public UptakeData() {
        measurerData.save();
        measurerData.update();
        uptake = new Uptake();
        uptake.setId(1000000);
        uptake.setMeasurer(measurerData.findById().getId());
        uptake.setCurrentValueTaken(100);
    }

    public boolean save() {
        boolean saved = false;
        try {
            saved = uptakeImpl.save(uptake);
            if (saved) {
                System.out.println("Saved:   " + uptake);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return saved;
    }

    public List<Uptake> find() {
        List<Uptake> list = new ArrayList<>();
        try {
            list = uptakeImpl.find();
            if (list.size() > 0) {
                System.out.println("Found: ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t " + list.get(i));
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return list;
    }

    public Uptake findById() {
        Uptake uptake1 = null;
        try {
            uptake1 = uptakeImpl.findById(uptake.getId());
            if (uptake1 != null) {
                System.out.println("By Id:   " + uptake1);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return uptake1;
    }

    public boolean update() {
        boolean updated = false;
        uptake.setCurrentValueTaken(75);
        try {
            updated = uptakeImpl.update(uptake);
            if (updated) {
                System.out.println("Updated: " + uptake);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return updated;
    }

    public boolean delete() {
        boolean deleted = false;
        try {
            deleted = uptakeImpl.delete(uptake);
            if (deleted) {
                System.out.println("Deleted: " + uptake);
                measurerData.delete();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return deleted;

    }

    public List<Uptake> findNotBilled() {
        List<Uptake> list = new ArrayList<>();
        try {
            list = uptakeImpl.findNotBilled(1000000);
            if (list.size() > 0) {
                System.out.println("Not Billed: ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t " + list.get(i));
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return list;
    }
}

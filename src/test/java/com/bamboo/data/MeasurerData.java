/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.data;

import com.bamboo.model.entity.dependent.Measurer;
import com.bamboo.model.entity.independent.Role;
import com.bamboo.model.method.dependent.MeasurerImpl;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alexander
 */
public final class MeasurerData {

    private final MeasurerImpl measurerImpl = new MeasurerImpl();
    private final SapData sapData = new SapData();
    private final StatusData statusData = new StatusData();
    private Measurer measurer;

    public MeasurerData() {
        sapData.save();
        statusData.save();
        this.measurer = new Measurer(1000, "", new Date(), sapData.findById(), statusData.findById());
    }

    public Measurer save() {
        try {
            measurer = measurerImpl.save(measurer);
            if (measurer != null) {
                System.out.println("Saved:   " + measurer);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return measurer;
    }

    public List<Measurer> find() {
        List<Measurer> list = new ArrayList<>();
        try {
            list = measurerImpl.find();
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

    public Measurer findById() {
        Measurer measurer1 = null;
        try {
            measurer1 = measurerImpl.findById(this.measurer.getId());
            if (measurer1 != null) {
                System.out.println("By Id:   " + measurer1);
            }
        } catch (Exception e) {
        }
        return measurer1;
    }

    public boolean update() {
        boolean updated = false;
        measurer.setNumber(measurer.getId() + "");
        try {
            updated = measurerImpl.update(measurer);
            if (updated) {
                System.out.println("Updated: " + measurer);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return updated;
    }

    public boolean delete() {
        boolean deleted = false;
        try {
            deleted = measurerImpl.delete(measurer);
            if (deleted) {
                System.out.println("Deleted: " + measurer);
                statusData.delete();
                sapData.delete();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return deleted;
    }

    public List<Measurer> findBySap() {
        List<Measurer> list = new ArrayList<>();
        try {
            list = measurerImpl.findBySap(1000);
            if (list.size() > 0) {
                System.out.println("By Sap: ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t " + list.get(i));
                }
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Measurer> findByStatus() {
        List<Measurer> list = new ArrayList<>();
        try {
            list = measurerImpl.findByStatus(1000);
            if (list.size() > 0) {
                System.out.println("By Satus: ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t " + list.get(i));
                }
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Map<String, Object> findMeasurerPerService() {
        Map<String, Object> list = new HashMap<>();
        try {
            list = measurerImpl.findMeasurerPerService();
            if (list.size() > 0) {
                System.out.println("Sap:   " + new Gson().toJson(list));

            }
        } catch (Exception e) {
        }
        return list;
    }

    public Map<String, Object> findMeasurerPerStatus() {
        Map<String, Object> list = new HashMap<>();
        try {
            list = measurerImpl.findMeasurerPerStatus();
            if (list.size() > 0) {
                System.out.println("Status: " + new Gson().toJson(list));

            }
        } catch (Exception e) {
        }
        return list;
    }
}

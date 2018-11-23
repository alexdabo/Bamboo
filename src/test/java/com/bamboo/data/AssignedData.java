/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.data;

import com.bamboo.model.entity.Measurer;
import com.bamboo.model.entity.Assigned;
import com.bamboo.model.method.AssignedImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author alexander
 */
public final class AssignedData {

    private final AssignedImpl assignedImpl = new AssignedImpl();
    private final BeneficiaryData beneficiaryData = new BeneficiaryData();
    private final MeasurerData measurerData = new MeasurerData();
    private final SapData sapData = new SapData();
    private final StatusData statusData = new StatusData();
    private final Assigned assigned;

    public AssignedData() {
        beneficiaryData.save();
        this.assigned = new Assigned();
        assigned.setBeneficiary(beneficiaryData.findById());
        assigned.setMeasurer(new Measurer(1000000, "", new Date(), sapData.findById(), statusData.findById()));
        assigned.setAssignmentDate(new Date());
    }

    public Assigned save() {
        Assigned assigned1 = null;
        try {
            assigned1 = assignedImpl.save(assigned);
            if (assigned1 != null) {
                System.out.println("Saved:   " + assigned1);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return assigned1;
    }

    public List<Assigned> find() {
        List<Assigned> list = new ArrayList<>();
        try {
            list = assignedImpl.find();
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

    public boolean update() {
        boolean updated = false;
        assigned.setStatus("disable");
        try {
            updated = assignedImpl.update(assigned);
            if (updated) {
                System.out.println("Updated: " + assigned);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return updated;
    }

    public boolean delete() {
        boolean deleted = false;
        try {
            deleted = assignedImpl.delete(assigned);
            if (deleted) {
                System.out.println("Deleted: " + assigned);
                beneficiaryData.delete();
                measurerData.delete();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return deleted;

    }
}

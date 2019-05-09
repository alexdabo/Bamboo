/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test.data;

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

    private Assigned assigned = null;

    public AssignedData() {
        beneficiaryData.save();
        measurerData.save();
        this.assigned = new Assigned(1000000, beneficiaryData.findById().getId(), measurerData.findById().getId(), new Date(), "enable", 0);
    }

    public boolean save() {
        boolean saved = false;
        try {
            saved = assignedImpl.save(assigned) != null;
            if (saved) {
                System.out.println("Saved:   " + assigned);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return saved;
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

    public List<Assigned> findByBeneficiary() {
        List<Assigned> list = new ArrayList<>();
        try {
            list = assignedImpl.findByBeneficiary(assigned.getBeneficiary());
            if (list.size() > 0) {
                System.out.println("Found by beneficiary: ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t " + list.get(i));
                }
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Assigned findById() {
        Assigned assigned1 = null;
        try {
            assigned1 = assignedImpl.findById(assigned.getId());
            if (assigned1 != null) {
                System.out.println("By Id:   " + assigned1);
            }
        } catch (Exception e) {
        }
        return assigned1;
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
            deleted = assignedImpl.delete(assigned.getId());
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

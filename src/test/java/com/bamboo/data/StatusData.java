/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.data;

import com.bamboo.model.entity.independent.Status;
import com.bamboo.model.method.independent.StatusImpl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexander
 */
public final class StatusData {

    private final StatusImpl statusImpl = new StatusImpl();
    private final Status status = new Status(1000, "Perdido");

    public boolean save() {
        boolean saved = false;
        try {
            saved = statusImpl.save(status);
            if (saved) {
                System.out.println("Saved:   " + status);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return saved;
    }

    public List<Status> find() {
        List<Status> list = new ArrayList<>();
        try {
            list = statusImpl.find();
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

    public Status findById() {
        Status status1 = null;
        try {
            status1 = statusImpl.findById(status.getId());
            if (status1 != null) {
                System.out.println("By Id:   " + status1);
            }
        } catch (Exception e) {
        }
        return status1;
    }

    public boolean update() {
        boolean updated = false;
        status.setName("Perdido editado");
        try {
            updated = statusImpl.update(status);
            if (updated) {
                System.out.println("Updated: " + status);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return updated;
    }

    public boolean delete() {
        boolean deleted = false;
        try {
            deleted = statusImpl.delete(status);
            if (deleted) {
                System.out.println("Deleted: " + status);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return deleted;

    }
}

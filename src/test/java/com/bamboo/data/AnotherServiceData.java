/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.data;

import com.bamboo.model.entity.independent.AnotherService;
import com.bamboo.model.method.independent.AnotherServiceImpl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexander
 */
public final class AnotherServiceData {

    private final AnotherServiceImpl serviceImpl = new AnotherServiceImpl();
    private final AnotherService service = new AnotherService(1000, "Cambio de medidor", 20);

    public boolean save() {
        boolean saved = false;
        try {
            saved = serviceImpl.save(service);
            if (saved) {
                System.out.println("Saved:   " + service);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return saved;
    }

    public List<AnotherService> find() {
        List<AnotherService> list = new ArrayList<>();
        try {
            list = serviceImpl.find();
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

    public AnotherService findById() {
        AnotherService service1 = null;
        try {
            service1 = serviceImpl.findById(service.getId());
            if (service1 != null) {
                System.out.println("By Id:   " + service1);
            }
        } catch (Exception e) {
        }
        return service1;
    }

    public boolean update() {
        boolean updated = false;
        service.setName("Cambio de medidor industrial");
        service.setPrice(25);
        try {
            updated = serviceImpl.update(service);
            if (updated) {
                System.out.println("Updated: " + service);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return updated;
    }

    public boolean delete() {
        boolean deleted = false;
        try {
            deleted = serviceImpl.delete(service);
            if (deleted) {
                System.out.println("Deleted: " + service);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return deleted;

    }

}

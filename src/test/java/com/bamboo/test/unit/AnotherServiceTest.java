/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test.unit;

import com.bamboo.model.entity.AnotherService;
import com.bamboo.model.method.AnotherServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author alexander
 */
public class AnotherServiceTest {

    private final AnotherServiceImpl serviceImpl = new AnotherServiceImpl();
    private final AnotherService service = new AnotherService(1000, "Cambio de medidor", 20);

    @Test
    public void run() {
        printTitle("Another Service Test");
        assertTrue(save());
        assertTrue(find());
        assertTrue(findById());
        assertTrue(update());
        assertTrue(delete());
        System.out.println("\n\n");
    }

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

    public boolean find() {
        boolean found = false;
        List<AnotherService> list = new ArrayList<>();
        try {
            list = serviceImpl.find();
            if (list.size() > 0) {
                found = true;
                System.out.println("Found: ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t " + list.get(i));
                }
            }
        } catch (Exception e) {
        }
        return found;
    }

    public boolean findById() {
        boolean found = false;
        AnotherService service1;
        try {
            service1 = serviceImpl.findById(service.getId());
            if (service1 != null) {
                found = true;
                System.out.println("By Id:   " + service1);
            }
        } catch (Exception e) {
        }
        return found;
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

    private void printTitle(String title) {
        System.out.print("\n\n\033[1m----------------------------< \033[0m");
        System.out.print("\033[36m" + title + " \033[0m");
        System.out.println("\033[1m>----------------------------\033[0m\n");

    }
}

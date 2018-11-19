/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.data;

import com.bamboo.model.entity.dependent.Operator;
import com.bamboo.model.method.dependent.OperatorImpl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexander
 */
public final class OperatorData {

    private final OperatorImpl operatorImpl = new OperatorImpl();
    private final RoleData roleData = new RoleData();
    private final Operator operator;

    public OperatorData() {
        roleData.save();
        operator = new Operator(1000, "Alexanderda", "12345", "adbonilla@gmail.com", "0604059741", "Alexander David", "Bonilla Adriano", "0979728686", "Riobamba", roleData.findById());
    }

    public boolean save() {
        boolean saved = false;
        try {
            saved = operatorImpl.save(operator);
            if (saved) {
                System.out.println("Saved:   " + operator);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return saved;
    }

    public List<Operator> find() {
        List<Operator> list = new ArrayList<>();
        try {
            list = operatorImpl.find();
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

    public Operator findById() {
        Operator operator1 = null;
        try {
            operator1 = operatorImpl.findById(operator.getId());
            if (operator1 != null) {
                System.out.println("By Id:   " + operator1);
            }
        } catch (Exception e) {
        }
        return operator1;
    }

    public boolean update() {
        boolean updated = false;
        operator.setUserName("AlexDav");
        try {
            updated = operatorImpl.update(operator);
            if (updated) {
                System.out.println("Updated: " + operator);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return updated;
    }

    public boolean delete() {
        boolean deleted = false;
        try {
            deleted = operatorImpl.delete(operator);
            if (deleted) {
                System.out.println("Deleted: " + operator);
                roleData.delete();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return deleted;

    }
}

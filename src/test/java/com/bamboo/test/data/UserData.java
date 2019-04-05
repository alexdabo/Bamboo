/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test.data;

import com.bamboo.model.entity.User;
import com.bamboo.model.method.UserImpl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexander
 */
public final class UserData {

    private final UserImpl userImpl = new UserImpl();
    private final RoleData roleData = new RoleData();
    private final User user;

    public UserData() {
        roleData.save();
        user = new User(1000000, "Alexanderda", "12345", "adbonilla@gmail.com", "0604059741", "Alexander David", "Bonilla Adriano", "0979728686", "Riobamba", roleData.findById().getId());
    }

    public boolean save() {
        boolean saved = false;
        try {
            saved = userImpl.save(user);
            if (saved) {
                System.out.println("Saved:   " + user);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return saved;
    }

    public List<User> find() {
        List<User> list = new ArrayList<>();
        try {
            list = userImpl.find();
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

    public User findById() {
        User user1 = null;
        try {
            user1 = userImpl.findById(user.getId());
            if (user1 != null) {
                System.out.println("By Id:   " + user1);
            }
        } catch (Exception e) {
        }
        return user1;
    }

    public boolean update() {
        boolean updated = false;
        user.setUserName("AlexDav");
        try {
            updated = userImpl.update(user);
            if (updated) {
                System.out.println("Updated: " + user);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return updated;
    }

    public boolean delete() {
        boolean deleted = false;
        try {
            deleted = userImpl.delete(user);
            if (deleted) {
                System.out.println("Deleted: " + user);
                roleData.delete();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return deleted;

    }
}

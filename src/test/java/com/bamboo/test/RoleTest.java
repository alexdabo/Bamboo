/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

import com.bamboo.data.RoleData;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author alexander
 */
public class RoleTest {

    @Test
    public void run() {
        Message msg = new Message();
        msg.printTitle("Role");
        RoleData roleData = new RoleData();
        msg.printImplementation();

        assertTrue(roleData.save());
        assertTrue(roleData.find().size() > 0);
        assertTrue(roleData.findById() != null);
        assertTrue(roleData.update());
        assertTrue(roleData.delete());
        System.out.println("\n\n");
    }
}

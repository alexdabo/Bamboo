/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

import com.bamboo.test.data.AssignedData;
import com.bamboo.test.data.VillageData;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author alexander
 */
public class AssignedTest {

    @Test
    public void run() {
        Message msg = new Message();
        msg.printTitle("Village");
        AssignedData assignedData = new AssignedData();
        msg.printImplementation();

        assertTrue(assignedData.save());
        assertTrue(assignedData.find().size() > 0);
        assertTrue(assignedData.findByBeneficiary().size() > 0);
        assertTrue(assignedData.findById() != null);
        assertTrue(assignedData.update());
        assertTrue(assignedData.delete());
        System.out.println("\n\n");
    }
}

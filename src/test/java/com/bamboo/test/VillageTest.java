/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

import com.bamboo.test.data.VillageData;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author alexander
 */
public class VillageTest {

    @Test
    public void run() {
        Message msg = new Message();
        msg.printTitle("Village");
        VillageData villageData = new VillageData();
        msg.printImplementation();

        assertTrue(villageData.save());
        assertTrue(villageData.find().size() > 0);
        assertTrue(villageData.findById() != null);
        assertTrue(villageData.update());
        assertTrue(villageData.delete());
        System.out.println("\n\n");
    }
}

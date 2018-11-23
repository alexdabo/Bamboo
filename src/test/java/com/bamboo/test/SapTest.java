/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

import com.bamboo.data.SapData;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author alexander
 */
public class SapTest {

    @Test
    public void run() {
        Message msg = new Message();
        msg.printTitle("SAP");
        SapData sapData = new SapData();
        msg.printImplementation();

        assertTrue(sapData.save());
        assertTrue(sapData.find().size() > 0);
        assertTrue(sapData.findById() != null);
        assertTrue(sapData.update());
        assertTrue(sapData.delete());
        System.out.println("\n\n");
    }

}

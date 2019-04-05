/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

import com.bamboo.test.data.StatusData;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author alexander
 */
public class StatusTest {

    @Test
    public void run() {
        Message msg = new Message();
        msg.printTitle("Status");
        StatusData statusData = new StatusData();
        msg.printImplementation();

        assertTrue(statusData.save());
        assertTrue(statusData.find().size() > 0);
        assertTrue(statusData.findById() != null);
        assertTrue(statusData.update());
        assertTrue(statusData.delete());
        System.out.println("\n\n");
    }

}

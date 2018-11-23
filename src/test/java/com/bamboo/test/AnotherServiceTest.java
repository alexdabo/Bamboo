/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

import com.bamboo.data.AnotherServiceData;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author alexander
 */
public class AnotherServiceTest {

    @Test
    public void run() {
        Message msg = new Message();
        msg.printTitle("Another service");
        AnotherServiceData serviceData = new AnotherServiceData();
        msg.printImplementation();

        assertTrue(serviceData.save());
        assertTrue(serviceData.find().size() > 0);
        assertTrue(serviceData.findById() != null);
        assertTrue(serviceData.update());
        assertTrue(serviceData.delete());
        System.out.println("\n\n");
    }

}

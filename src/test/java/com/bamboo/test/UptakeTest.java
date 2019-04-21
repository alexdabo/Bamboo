/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

import com.bamboo.test.data.UptakeData;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author alexander
 */
public class UptakeTest {

    @Test
    public void run() {
        Message msg = new Message();
        msg.printTitle("Uptake");
        msg.printDependency();
        UptakeData uptakeData = new UptakeData();
        msg.printImplementation();

        assertTrue(uptakeData.save());
        assertTrue(uptakeData.find().size() > 0);
        assertTrue(uptakeData.findNotBilled().size() > 0);
        assertTrue(uptakeData.findById() != null);
        assertTrue(uptakeData.delete());
        System.out.println("\n\n");
    }

}

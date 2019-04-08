/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

import com.bamboo.test.data.SapDetailData;
import com.bamboo.test.data.VillageData;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author alexander
 */
public class SapDetailTest {

    @Test
    public void run() {
        Message msg = new Message();
        msg.printTitle("Sap Detail");
        SapDetailData sapDetailData = new SapDetailData();
        msg.printImplementation();

        assertTrue(sapDetailData.save());
        assertTrue(sapDetailData.find().size() > 0);
        assertTrue(sapDetailData.findByInvoice().size() > 0);
        assertTrue(sapDetailData.delete());
        System.out.println("\n\n");
    }
}

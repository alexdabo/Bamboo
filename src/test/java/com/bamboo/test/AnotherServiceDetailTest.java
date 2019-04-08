/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

import com.bamboo.test.data.AnotherServiceDetailData;
import com.bamboo.test.data.AssignedData;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author alexander
 */
public class AnotherServiceDetailTest {

    @Test
    public void run() {
        Message msg = new Message();
        msg.printTitle("Another Service Detail");
        AnotherServiceDetailData anotherServiceDetailData = new AnotherServiceDetailData();
        msg.printImplementation();

        assertTrue(anotherServiceDetailData.save());
        assertTrue(anotherServiceDetailData.find().size() > 0);
        assertTrue(anotherServiceDetailData.delete());
        System.out.println("\n\n");
    }
}

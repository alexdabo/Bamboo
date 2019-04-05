/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

import com.bamboo.test.data.MeasurerData;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author alexander
 */
public class MeasurerTest {

    @Test
    public void run() {

        Message msg = new Message();
        msg.printTitle("Measurer");
        msg.printDependency();
        MeasurerData measurerData = new MeasurerData();
        msg.printImplementation();

        assertTrue(measurerData.save() != null);
        assertTrue(measurerData.find().size() > 0);
        assertTrue(measurerData.findById() != null);
        assertTrue(measurerData.update());
        assertTrue(measurerData.findBySap().size() > 0);
        assertTrue(measurerData.findByStatus().size() > 0);
        assertTrue(measurerData.findMeasurerPerService().size() > 0);
        assertTrue(measurerData.findMeasurerPerStatus().size() > 0);
        assertTrue(measurerData.delete());
        System.out.println("\n\n");
    }

}

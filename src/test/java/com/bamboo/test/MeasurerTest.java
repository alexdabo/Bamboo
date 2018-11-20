/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

import com.bamboo.data.MeasurerData;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author alexander
 */
public class MeasurerTest {

    private final MeasurerData measurerData = new MeasurerData();

    @Test
    public void run() {
        printTitle("Measurer");
        assertTrue(measurerData.save() != null);
        assertTrue(measurerData.find().size() > 0);
        assertTrue(measurerData.findById() != null);
        assertTrue(measurerData.update());
        System.out.println("");
        assertTrue(measurerData.findBySap().size() > 0);
        assertTrue(measurerData.findByStatus().size() > 0);
        assertTrue(measurerData.findMeasurerPerService().size() > 0);
        assertTrue(measurerData.findMeasurerPerStatus().size() > 0);
        System.out.println("");
        assertTrue(measurerData.delete());
        System.out.println("\n\n");
    }

    private void printTitle(String title) {
        System.out.print("\n\n\033[1m----------------------------------< \033[0m");
        System.out.print("\033[36m" + title + " \033[0m");
        System.out.println("\033[1m>----------------------------------\033[0m\n");

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

import com.bamboo.data.OperatorData;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author alexander
 */
public class OperatorTest {

    private final OperatorData operatorData = new OperatorData();

    @Test
    public void run() {
        printTitle("Oeperator");
        assertTrue(operatorData.save());
        System.out.println("");
        assertTrue(operatorData.find().size() > 0);
        System.out.println("");
        assertTrue(operatorData.findById() != null);
        System.out.println("");
        assertTrue(operatorData.update());
        System.out.println("");
        assertTrue(operatorData.delete());
        System.out.println("\n\n");
    }

    private void printTitle(String title) {
        System.out.print("\n\n\033[1m------------------------------------< \033[0m");
        System.out.print("\033[36m" + title +" \033[0m");
        System.out.println("\033[1m >------------------------------------\033[0m\n");

    }
}

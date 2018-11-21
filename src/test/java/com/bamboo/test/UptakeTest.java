/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

import com.bamboo.data.UptakeData;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author alexander
 */
public class UptakeTest {

    private final UptakeData uptakeData = new UptakeData();

    @Test
    public void run() {
        printTitle("Uptake");
        assertTrue(uptakeData.save());
        assertTrue(uptakeData.find().size() > 0);
        assertTrue(uptakeData.findNotBilled().size() > 0);
        assertTrue(uptakeData.findById() != null);
        assertTrue(uptakeData.update());
        assertTrue(uptakeData.delete());
        System.out.println("\n\n");
    }

    private void printTitle(String title) {
        System.out.print("\n\n\033[1m-----------------------------------< \033[0m");
        System.out.print("\033[36m" + title + " \033[0m");
        System.out.println("\033[1m>-----------------------------------\033[0m\n");

    }
}

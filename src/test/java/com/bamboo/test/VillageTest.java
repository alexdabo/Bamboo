/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

import com.bamboo.data.VillageData;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author alexander
 */
public class VillageTest {

    private final VillageData villageData = new VillageData();

    @Test
    public void run() {
        printTitle("Village");
        assertTrue(villageData.save());
        assertTrue(villageData.find().size() > 0);
        assertTrue(villageData.findById() != null);
        assertTrue(villageData.update());
        assertTrue(villageData.delete());
        System.out.println("\n\n");
    }

    private void printTitle(String title) {
        System.out.print("\n\n\033[1m-----------------------------------< \033[0m");
        System.out.print("\033[36m" + title + " \033[0m");
        System.out.println("\033[1m>-----------------------------------\033[0m\n");

    }
}

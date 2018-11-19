/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

import com.bamboo.data.RoleData;
import com.bamboo.data.StatusData;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author alexander
 */
public class StatusTest {

    private final StatusData statusData = new StatusData();

    @Test
    public void run() {
        printTitle("Status");
        assertTrue(statusData.save());
        assertTrue(statusData.find().size() > 0);
        assertTrue(statusData.findById() != null);
        assertTrue(statusData.update());
        assertTrue(statusData.delete());
        System.out.println("\n\n");
    }

    private void printTitle(String title) {
        System.out.print("\n\n\033[1m-----------------------------------< \033[0m");
        System.out.print("\033[36m" + title + " \033[0m");
        System.out.println("\033[1m>-----------------------------------\033[0m\n");

    }
}

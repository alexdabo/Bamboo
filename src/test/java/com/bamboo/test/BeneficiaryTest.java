/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

import com.bamboo.data.BeneficiaryData;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author alexander
 */
public class BeneficiaryTest {

    private final BeneficiaryData beneficiaryData = new BeneficiaryData();

    @Test
    public void run() {
        printTitle("Beneficiary");
        assertTrue(beneficiaryData.save());
        assertTrue(beneficiaryData.find().size() > 0);
        assertTrue(beneficiaryData.findById() != null);
        assertTrue(beneficiaryData.update());
        assertTrue(beneficiaryData.findByData().size() > 0);
        assertTrue(beneficiaryData.peopleFromVillages().size() > 0);
        assertTrue(beneficiaryData.delete());
        System.out.println("\n\n");
    }

    private void printTitle(String title) {
        System.out.print("\n\n\033[1m---------------------------------< \033[0m");
        System.out.print("\033[36m" + title + " \033[0m");
        System.out.println("\033[1m>---------------------------------\033[0m\n");

    }
}

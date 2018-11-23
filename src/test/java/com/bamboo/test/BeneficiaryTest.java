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

    @Test
    public void run() {
        Message msg = new Message();
        msg.printTitle("Beneficiary");
        msg.printDependency();
        BeneficiaryData beneficiaryData = new BeneficiaryData();
        msg.printImplementation();

        assertTrue(beneficiaryData.save());
        assertTrue(beneficiaryData.find().size() > 0);
        assertTrue(beneficiaryData.findById() != null);
        assertTrue(beneficiaryData.update());
        assertTrue(beneficiaryData.findByData().size() > 0);
        assertTrue(beneficiaryData.peopleFromVillages().size() > 0);
        assertTrue(beneficiaryData.delete());
        System.out.println("\n\n");
    }

}

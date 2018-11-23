/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

import com.bamboo.data.InvoiceData;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author alexander
 */
public class InvoiceTest {

    @Test
    public void run() {
        Message msg = new Message();
        msg.printTitle("Invoice");
        msg.printDependency();
        InvoiceData invoiceData = new InvoiceData();
        msg.printImplementation();

        assertTrue(invoiceData.save() != null);
        assertTrue(invoiceData.update());
        assertTrue(invoiceData.find().size() > 0);
        assertTrue(invoiceData.findByBeneficiary().size() > 0);
        assertTrue(invoiceData.findByDate().size() > 0);
        assertTrue(invoiceData.findById() != null);
        assertTrue(invoiceData.delete());
        System.out.println("\n\n");
    }

}

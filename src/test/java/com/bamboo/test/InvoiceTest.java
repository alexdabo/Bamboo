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

    private final InvoiceData invoiceData = new InvoiceData();

    @Test
    public void run() {
        printTitle("Invoice");
        assertTrue(invoiceData.save() != null);
        assertTrue(invoiceData.update());
        assertTrue(invoiceData.find().size() > 0);
        assertTrue(invoiceData.findByBeneficiary().size() > 0);
        assertTrue(invoiceData.findByDate().size() > 0);
        assertTrue(invoiceData.findById() != null);
        assertTrue(invoiceData.delete());
        System.out.println("\n\n");
    }

    private void printTitle(String title) {
        System.out.print("\n\n\033[1m----------------------------------< \033[0m");
        System.out.print("\033[36m" + title + " \033[0m");
        System.out.println("\033[1m>----------------------------------\033[0m\n");

    }
}

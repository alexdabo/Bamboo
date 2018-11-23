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

    @Test
    public void run() {
        Message msg = new Message();
        msg.printTitle("Operator");
        msg.printDependency();
        OperatorData operatorData = new OperatorData();
        msg.printImplementation();

        assertTrue(operatorData.save());
        assertTrue(operatorData.find().size() > 0);
        assertTrue(operatorData.findById() != null);
        assertTrue(operatorData.update());
        assertTrue(operatorData.delete());
        System.out.println("\n\n");
    }

}

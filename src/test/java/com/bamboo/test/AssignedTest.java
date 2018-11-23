/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

import com.bamboo.data.AssignedData;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 *
 * @author alexander
 */
public class AssignedTest {

    

    @Test
    public void run() {
        Message msg = new Message();
        msg.printTitle("Assigned");
        msg.printDependency();
        AssignedData assignedData = new AssignedData();
        msg.printImplementation();
        
        assertTrue(assignedData.save() != null);
        assertTrue(assignedData.find().size() > 0);
        assertTrue(assignedData.update());
        assertTrue(assignedData.delete());
        System.out.println("\n\n");
    }

}

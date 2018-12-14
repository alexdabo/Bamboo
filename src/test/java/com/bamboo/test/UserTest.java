/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

import com.bamboo.data.UserData;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author alexander
 */
public class UserTest {

    @Test
    public void run() {
        Message msg = new Message();
        msg.printTitle("User");
        msg.printDependency();
        UserData userData = new UserData();
        msg.printImplementation();

        assertTrue(userData.save());
        assertTrue(userData.find().size() > 0);
        assertTrue(userData.findById() != null);
        assertTrue(userData.update());
        assertTrue(userData.delete());
        System.out.println("\n\n");
    }

}

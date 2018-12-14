/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

import com.bamboo.data.UserData;
import com.bamboo.model.entity.Audit;


import com.bamboo.model.method.AuditImpl;
import com.bamboo.model.entity.Beneficiary;
import com.bamboo.model.entity.User;

/**
 *
 * @author alexander
 */
public class AuditTest {

    private final AuditImpl audit = new AuditImpl(Beneficiary.class);
    private final UserData userData = new UserData();
    private final User user;

    public AuditTest() {
        Message msg = new Message();
        msg.printTitle("Audit");
        userData.save();
        user = userData.findById();

    }

//    @Test
    public void run() {

        audit.save(new Audit(user, "description"));
        System.out.println("");
        audit.update(new Audit(user, "description"));
        System.out.println("");
        audit.delete(new Audit(user, "description"));
        System.out.println("");
        userData.delete();

        System.out.println("\n\n");
    }

}

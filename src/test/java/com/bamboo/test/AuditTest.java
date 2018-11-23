/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

import com.bamboo.data.OperatorData;
import com.bamboo.model.entity.Audit;
import org.junit.Test;

import com.bamboo.model.method.AuditImpl;
import com.bamboo.model.entity.Beneficiary;
import com.bamboo.model.entity.Operator;

/**
 *
 * @author alexander
 */
public class AuditTest {

    private final AuditImpl audit = new AuditImpl(Beneficiary.class);
    private final OperatorData operatorData = new OperatorData();
    private final Operator operator;

    public AuditTest() {
        operatorData.save();
        operator = operatorData.findById();

    }

    @Test
    public void run() {
        printTitle("Audit");

        audit.save(new Audit(operator, "description"));
        System.out.println("");
        audit.Update(new Audit(operator, "description"));
        System.out.println("");
        audit.delete(new Audit(operator, "description"));
        System.out.println("");
        operatorData.delete();

        System.out.println("\n\n");
    }

    private void printTitle(String title) {
        System.out.print("\n\n\033[1m-----------------------------------< \033[0m");
        System.out.print("\033[36m" + title + " \033[0m");
        System.out.println("\033[1m>-----------------------------------\033[0m\n");

    }
}

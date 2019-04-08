/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test.data;

import com.bamboo.model.entity.AnotherServiceDetail;
import com.bamboo.model.entity.SapDetail;
import com.bamboo.model.method.AnotherServiceDetailImpl;
import com.bamboo.model.method.SapDetailImpl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexander
 */
public final class AnotherServiceDetailData {

    private final AnotherServiceDetailImpl serviceDetailImpl = new AnotherServiceDetailImpl();
    private final InvoiceData invoiceData = new InvoiceData();
    private final AnotherServiceData anotherServiceData = new AnotherServiceData();
    private AnotherServiceDetail serviceDetail = null;

    public AnotherServiceDetailData() {
        invoiceData.save();
        anotherServiceData.save();
        this.serviceDetail = new AnotherServiceDetail(1000000, invoiceData.findById().getId(), anotherServiceData.findById().getId(), 10);
    }

    public boolean save() {
        boolean saved = false;
        try {
            saved = serviceDetailImpl.save(serviceDetail);
            if (saved) {
                System.out.println("Saved:   " + serviceDetail);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return saved;
    }

    public List<AnotherServiceDetail> find() {
       List<AnotherServiceDetail> list = new ArrayList<>();
        try {
            list = serviceDetailImpl.find();
            if (list.size() > 0) {
                System.out.println("Found: ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t " + list.get(i));
                }
            }
        } catch (Exception e) {
        }
        return list;
    }



    public boolean delete() {
        boolean deleted = false;
        try {
            deleted = serviceDetailImpl.delete(serviceDetail);
            if (deleted) {
                System.out.println("Deleted: " + serviceDetail);
                invoiceData.delete();
                anotherServiceData.delete();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return deleted;
    }
}

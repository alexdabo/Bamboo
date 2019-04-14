/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test.data;

import com.bamboo.model.entity.SapDetail;
import com.bamboo.model.method.SapDetailImpl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexander
 */
public final class SapDetailData {

    private final SapDetailImpl sapDetailImpl = new SapDetailImpl();
    private final InvoiceData invoiceData = new InvoiceData();
    private final UptakeData uptakeData = new UptakeData();
    private SapDetail sapDetail = null;

    public SapDetailData() {
        invoiceData.save();
        uptakeData.save();
        this.sapDetail = new SapDetail(1000000, invoiceData.findById().getId(), uptakeData.findById().getId());
    }

    public boolean save() {
        boolean saved = false;
        try {
            saved = sapDetailImpl.save(sapDetail);
            if (saved) {
                System.out.println("Saved:   " + sapDetail);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return saved;
    }

    public List<SapDetail> find() {
        List<SapDetail> list = new ArrayList<>();
        try {
            list = sapDetailImpl.find();
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

    public List<SapDetail> findByInvoice() {
        List<SapDetail> list = new ArrayList<>();
        try {
            list = sapDetailImpl.findByInvoice(sapDetail.getInvoice());
            if (list.size() > 0) {
                System.out.println("Found by invoice: ");
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
            deleted = sapDetailImpl.delete(sapDetail.getId());
            if (deleted) {
                System.out.println("Deleted: " + sapDetail);
                invoiceData.delete();
                uptakeData.delete();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return deleted;
    }
}

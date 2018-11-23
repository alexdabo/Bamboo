/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.data;

import com.bamboo.model.entity.Invoice;
import com.bamboo.model.method.InvoiceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author alexander
 */
public final class InvoiceData {

    private final InvoiceImpl invoiceImpl = new InvoiceImpl();
    private final BeneficiaryData beneficiaryData = new BeneficiaryData();
    private final OperatorData collectorData = new OperatorData();
    private final Invoice invoice;

    public InvoiceData() {
        beneficiaryData.save();
        collectorData.save();

        this.invoice = new Invoice();
        this.invoice.setId(1000);
        this.invoice.setBeneficiary(beneficiaryData.findById());
        this.invoice.setDebtcollector(collectorData.findById());
        this.invoice.setTotalToPay(100.0);
    }

    public Invoice save() {
        Invoice invoice1 = null;
        try {
            invoice1 = invoiceImpl.save(invoice);
            if (invoice1 != null) {
                System.out.println("Saved:   " + invoice);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return invoice1;
    }

    public List<Invoice> find() {
        List<Invoice> list = new ArrayList<>();
        try {
            list = invoiceImpl.find();
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

    public Invoice findById() {
        Invoice invoice1 = null;
        try {
            invoice1 = invoiceImpl.findById(invoice.getId());
            if (invoice1 != null) {
                System.out.println("By Id:   " + invoice1);
            }
        } catch (Exception e) {
        }
        return invoice1;
    }

    public boolean update() {
        boolean updated = false;
        invoice.setNumber(Integer.toString(this.invoice.getId()));
        try {
            updated = invoiceImpl.update(invoice);
            if (updated) {
                System.out.println("Updated: " + invoice);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return updated;
    }

    public boolean delete() {
        boolean deleted = false;
        try {
            deleted = invoiceImpl.delete(invoice);
            if (deleted) {
                System.out.println("Deleted: " + invoice);
                beneficiaryData.delete();
                collectorData.delete();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return deleted;

    }

    public List<Invoice> findByBeneficiary() {
        List<Invoice> list = new ArrayList<>();
        try {
            list = invoiceImpl.findByBeneficiary(1000);
            if (list.size() > 0) {
                System.out.println("By beneficiary: ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t " + list.get(i));
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return list;
    }

    public List<Invoice> findByDate() {
        List<Invoice> list = new ArrayList<>();
        try {
            list = invoiceImpl.findByDate(new Date());
            if (list.size() > 0) {
                System.out.println("By date: ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t " + list.get(i));
                }
            }
        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        }
        return list;
    }
}

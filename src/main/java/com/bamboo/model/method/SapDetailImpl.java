package com.bamboo.model.method;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.contrat.SapDetailInterface;
import com.bamboo.model.entity.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SapDetailImpl implements SapDetailInterface {

    private final DBConnection DBC = new DBConnection();

    @Override
    public SapDetail create(SapDetail detail) throws Exception{
        SapDetail sapDetail = null;
        boolean affected= false;
        UptakeImpl uptakeImp = new UptakeImpl();
        InvoiceImpl invoiceImpl = new InvoiceImpl();
        String sql = "insert into public.sapdetail(invoiceid, uptakeid ) values(?, ?)";

        try {

            Invoice invoice =  invoiceImpl.save(detail.getInvoice());
            if(invoice.getId()!=0){
                for (Uptake uptake : detail.getUptakes()){
                    List<DBObject> dbos = new ArrayList<>();
                    dbos.add(new DBObject(1,invoice.getId()));
                    dbos.add(new DBObject(2,uptake.getId()));
                    if (DBC.querySet(sql, dbos)) {
                        affected = true;
                        System.out.println("step1");
                        uptake.setBilled(true);
                        uptakeImp.update(uptake);
                    }
                }
                if (affected){
                    System.out.println("step2");
                   sapDetail = findByInvoice(invoice.getId());
                    System.out.println("id: " + invoice.getId());
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return sapDetail;
    }


    @Override
    public boolean save(SapDetail detail) throws Exception {
        boolean affected = false;

        UptakeImpl uptakeImp = new UptakeImpl();
        InvoiceImpl invoiceImpl = new InvoiceImpl();
        String sql = "insert into public.sapdetail(invoiceid, uptakeid ) values(?, ?)";

        try {

            Invoice invoice =  invoiceImpl.save(detail.getInvoice());
            if(invoice.getId()!=0){
                for (Uptake uptake : detail.getUptakes()){
                    List<DBObject> dbos = new ArrayList<>();
                    dbos.add(new DBObject(1,invoice.getId()));
                    dbos.add(new DBObject(2,uptake.getId()));
                    if (DBC.querySet(sql, dbos)) {
                        affected = true;
                        uptake.setBilled(true);
                        uptakeImp.update(uptake);

                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }

    @Override
    public SapDetail findById(int id) throws Exception {
        SapDetail detail = null;
        InvoiceImpl invoiceImpl = new InvoiceImpl();
        UptakeImpl uptakeImp = new UptakeImpl();
        try {
            detail = new SapDetail();
            detail.setInvoice(invoiceImpl.findById(id));
            detail.setUptakes(uptakeImp.findByInvoice(detail.getInvoice().getId()));
        } catch (Exception e) {
            throw e;
        }
        return detail;
    }

    @Override
    public List<SapDetail> find() throws Exception {
        List<SapDetail> list = new ArrayList<>();
        BeneficiaryImpl beneficiaryImpl = new BeneficiaryImpl();
        InvoiceImpl invoiceImpl = new InvoiceImpl();
        UptakeImpl uptakeImp = new UptakeImpl();
        try {
            for (Invoice invoice : invoiceImpl.find()) {
                SapDetail detail = new SapDetail();
                detail.setInvoice(invoice);
                detail.setUptakes(uptakeImp.findByInvoice(invoice.getId()));
                list.add(detail);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }


    @Override
    public SapDetail findByInvoice(int invoiceId) throws Exception {
        SapDetail sapDetail = null;
        BeneficiaryImpl beneficiaryImpl = new BeneficiaryImpl();
        InvoiceImpl invoiceImpl = new InvoiceImpl();
        UptakeImpl uptakeImp = new UptakeImpl();
        try {
            sapDetail.setInvoice(invoiceImpl.findById(invoiceId));
            sapDetail.setUptakes(uptakeImp.findByInvoice(sapDetail.getInvoice().getId()));
            System.out.println(invoiceImpl.findById(invoiceId));
        } catch (Exception e) {
            //delete()
            throw e;
        }
        return sapDetail;
    }

    @Override
    public boolean update(SapDetail detail) throws Exception {
        return false;
    }

    @Override
    public boolean delete(SapDetail detail) throws Exception {
        return false;
    }
    private String random() {
        return UUID.randomUUID().toString().substring(0, 10);
    }

}

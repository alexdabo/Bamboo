package com.bamboo.model.method;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.contrat.SapDetailInterface;
import com.bamboo.model.entity.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SapDetailImpl implements SapDetailInterface {

    private final DBConnection DBC = new DBConnection();

    @Override
    public boolean save(SapDetail detail) throws Exception {
        return false;
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
    public boolean update(SapDetail detail) throws Exception {
        return false;
    }

    @Override
    public boolean delete(SapDetail detail) throws Exception {
        return false;
    }


}

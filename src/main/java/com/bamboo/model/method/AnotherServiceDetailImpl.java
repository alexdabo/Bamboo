package com.bamboo.model.method;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.contrat.AnotherServiceDetailInterface;
import com.bamboo.model.entity.AnotherService;
import com.bamboo.model.entity.AnotherServiceDetail;
import com.bamboo.model.entity.Invoice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnotherServiceDetailImpl implements AnotherServiceDetailInterface {
    private final DBConnection DBC = new DBConnection();

    @Override
    public boolean save(AnotherServiceDetail service) throws Exception {
        boolean affected = false;
        InvoiceImpl invoiceImpl = new InvoiceImpl();
        String sql = "INSERT INTO public.anotherservicedetail(invoiceid, anotherserviceid, price) VALUES (?, ?, ?);";
        List<DBObject> dbos = new ArrayList<>();


        try {

            Invoice invoice = invoiceImpl.save(service.getInvoice());
            if (invoice.getId() != 0) {
                dbos.add(new DBObject(1, invoice.getId()));
                dbos.add(new DBObject(2, service.getService().getId()));
                dbos.add(new DBObject(3, service.getTotal()));

                if (DBC.querySet(sql, dbos)) {
                    affected = true;
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return affected;
    }

    @Override
    public List<AnotherServiceDetail> findByBeneficiary(int beneficiaryId) throws Exception {
        return null;
    }


    @Override
    public List<AnotherServiceDetail> find() throws Exception {
        List<AnotherServiceDetail> details = new ArrayList<>();
        InvoiceImpl invoiceImpl = new InvoiceImpl();
        AnotherServiceImpl serviceImpl = new AnotherServiceImpl();
        String sql = "SELECT invoiceid, anotherserviceid, price FROM public.anotherservicedetail ORDER BY invoiceid DESC;";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                AnotherServiceDetail detail = new AnotherServiceDetail();
                detail.setInvoice(invoiceImpl.findById(result.getInt("invoiceid")));
                detail.setService(serviceImpl.findById(result.getInt("anotherserviceid")));
                detail.setTotal(result.getDouble("price"));
                details.add(detail);

            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return details;
    }


    @Override
    public boolean delete(AnotherServiceDetail detail) throws Exception {
        boolean affected = false;
        String sql = "DELETE FROM public.anotherservicedetail WHERE (invoiceid=? AND anotherserviceid=?);";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, detail.getInvoice().getId()));
        dbos.add(new DBObject(2, detail.getService().getId()));


        try {
            if (DBC.querySet(sql, dbos)) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }
}

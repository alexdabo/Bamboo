package com.bamboo.model.method;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.contrat.AnotherServiceDetailInterface;
import com.bamboo.model.entity.AnotherServiceDetail;
import com.bamboo.model.entity.Invoice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnotherServiceDetailImpl implements AnotherServiceDetailInterface {

    private final DBConnection DBC = new DBConnection();

    @Override
    public boolean save(AnotherServiceDetail serviceDetail) throws Exception {
        boolean affected = false;
        InvoiceImpl invoiceImpl = new InvoiceImpl();
        String sql = "INSERT INTO public.anotherservicedetail(invoiceid, anotherserviceid, price) VALUES (?, ?, ?);";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, serviceDetail.getInvoice()));
        dbos.add(new DBObject(2, serviceDetail.getService()));
        dbos.add(new DBObject(3, serviceDetail.getTotal()));
        if (serviceDetail.getId() != 0) {
            sql = "INSERT INTO public.anotherservicedetail(invoiceid, anotherserviceid, price, id) VALUES (?, ?, ?, ?);";
            dbos.add(new DBObject(4, serviceDetail.getId()));
        }

        try {
            if (DBC.querySet(sql, dbos)) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }

        return affected;
    }

    @Override
    public List<AnotherServiceDetail> find() throws Exception {
        List<AnotherServiceDetail> details = new ArrayList<>();
        String sql = "SELECT id, invoiceid, anotherserviceid, price FROM public.anotherservicedetail ORDER BY invoiceid DESC;";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                AnotherServiceDetail detailService = new AnotherServiceDetail();
                detailService.setId(result.getInt("id"));
                detailService.setInvoice(result.getInt("invoiceid"));
                detailService.setService(result.getInt("anotherserviceid"));
                detailService.setTotal(result.getDouble("price"));
                details.add(detailService);

            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return details;
    }

    @Override
    public boolean delete(AnotherServiceDetail detailService) throws Exception {
        boolean affected = false;
        String sql = "DELETE FROM public.anotherservicedetail WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, detailService.getId()));
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

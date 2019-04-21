package com.bamboo.model.method;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.contrat.SapDetailInterface;
import com.bamboo.model.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SapDetailImpl implements SapDetailInterface {

    private final DBConnection DBC = new DBConnection();

    @Override
    public SapDetail save(SapDetail detail) throws Exception {
        SapDetail newSapDetail = null;

        UptakeImpl uptakeImp = new UptakeImpl();
        String sql = "insert into public.sapdetail(invoiceid, uptakeid ) values(?, ?) "
                + "RETURNING id, invoiceid, uptakeid;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, detail.getInvoice()));
        dbos.add(new DBObject(2, detail.getUptake()));
        if (detail.getId() != 0) {
            sql = "insert into public.sapdetail(invoiceid, uptakeid, id) values(?, ?, ?) "
                    + "RETURNING id, invoiceid, uptakeid;";
            dbos.add(new DBObject(3, detail.getId()));
        }
        try {

            ResultSet result = DBC.queryResultSet(sql, dbos);
            while (result.next()) {
                newSapDetail = new SapDetail();
                newSapDetail.setId(result.getInt("id"));
                newSapDetail.setInvoice(result.getInt("invoiceid"));
                newSapDetail.setUptake(result.getInt("uptakeid"));
            }
            if (newSapDetail != null) {
                Uptake uptake = uptakeImp.findById(newSapDetail.getUptake());
                uptake.setBilled(true);
                uptakeImp.updateToBilled(uptake.getId());
            }

        } catch (Exception e) {
            throw e;
        }
        return newSapDetail;
    }

    @Override
    public List<SapDetail> findByInvoice(int invoiceId) throws Exception {
        List<SapDetail> SapDetails = new ArrayList<>();
        String sql = "SELECT id, invoiceid, uptakeid FROM public.sapdetail WHERE invoiceid=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, invoiceId));

        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                SapDetail detail = new SapDetail();
                detail.setId(result.getInt("id"));
                detail.setInvoice(result.getInt("invoiceid"));
                detail.setUptake(result.getInt("uptakeid"));
                SapDetails.add(detail);

            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }

        return SapDetails;
    }

    @Override
    public SapDetail findById(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SapDetail> find() throws Exception {
        List<SapDetail> SapDetails = new ArrayList<>();
        String sql = "SELECT id, invoiceid, uptakeid FROM public.sapdetail  ORDER BY invoiceid DESC;";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                SapDetail detail = new SapDetail();
                detail.setId(result.getInt("id"));
                detail.setInvoice(result.getInt("invoiceid"));
                detail.setUptake(result.getInt("uptakeid"));
                SapDetails.add(detail);

            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }

        return SapDetails;
    }

    @Override
    public boolean update(SapDetail element) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) throws Exception {
        boolean affected = false;
        MeasurerImpl measurerImpl = new MeasurerImpl();
        String sql = "DELETE FROM public.sapdetail WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, id));

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

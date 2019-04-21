package com.bamboo.model.method;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.contrat.UptakeInterface;
import com.bamboo.model.entity.Uptake;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UptakeImpl implements UptakeInterface {

    private final DBConnection DBC = new DBConnection();

    @Override
    public Uptake save(Uptake uptake) throws Exception {
        Uptake newUptake = null;
        String sql = "INSERT INTO public.uptake(measurerid, datetaked, currentvaluetaken) VALUES (?, ?, ?) "
                + "RETURNING  id, measurerid, datetaked, lastvaluetaken, currentvaluetaken, basevolume, baseprice, "
                + "extraprice, volumeconsumed, volumeexceeded, totalprice, billed;";
        if (uptake.getDatetaked() == null) {
            uptake.setDatetaked(new Date());
        }
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, uptake.getMeasurer()));
        dbos.add(new DBObject(2, uptake.getDatetaked()));
        dbos.add(new DBObject(3, uptake.getCurrentValueTaken()));

        if (uptake.getId() != 0) {
            sql = "INSERT INTO public.uptake(measurerid, datetaked, currentvaluetaken, id) VALUES (?, ?, ?, ?) "
                    + "RETURNING  id, measurerid, datetaked, lastvaluetaken, currentvaluetaken, basevolume, baseprice, "
                    + "extraprice, volumeconsumed, volumeexceeded, totalprice, billed;";
            dbos.add(new DBObject(4, uptake.getId()));
        }
        try {
            ResultSet result = DBC.queryResultSet(sql, dbos);
            while (result.next()) {
                newUptake = new Uptake();
                newUptake.setId(result.getInt("id"));
                newUptake.setMeasurer(result.getInt("measurerid"));
                newUptake.setDatetaked(result.getDate("datetaked"));
                newUptake.setLastValueTaken(result.getDouble("lastvaluetaken"));
                newUptake.setCurrentValueTaken(result.getDouble("currentvaluetaken"));
                newUptake.setBaseVolume(result.getDouble("basevolume"));
                newUptake.setBasePrice(result.getDouble("baseprice"));
                newUptake.setExtraPrice(result.getDouble("extraprice"));
                newUptake.setVolumeConsumed(result.getDouble("volumeconsumed"));
                newUptake.setVolumeExceeded(result.getDouble("volumeexceeded"));
                newUptake.setTotalPrice(result.getDouble("totalprice"));
                newUptake.setBilled(result.getBoolean("billed"));
            }
        } catch (Exception e) {
            throw e;
        }

        return newUptake;
    }

    @Override
    public Uptake findById(int id) throws Exception {
        Uptake uptake = null;
        String sql = "SELECT id, measurerid, datetaked, lastvaluetaken, currentvaluetaken, basevolume, baseprice, extraprice, volumeconsumed, volumeexceeded, totalprice, billed "
                + "FROM public.uptake where id = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, id));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                uptake = new Uptake();
                uptake.setId(result.getInt("id"));
                uptake.setMeasurer(result.getInt("measurerid"));
                uptake.setDatetaked(result.getDate("datetaked"));
                uptake.setLastValueTaken(result.getDouble("lastvaluetaken"));
                uptake.setCurrentValueTaken(result.getDouble("currentvaluetaken"));
                uptake.setBaseVolume(result.getDouble("basevolume"));
                uptake.setBasePrice(result.getDouble("baseprice"));
                uptake.setExtraPrice(result.getDouble("extraprice"));
                uptake.setVolumeConsumed(result.getDouble("volumeconsumed"));
                uptake.setVolumeExceeded(result.getDouble("volumeexceeded"));
                uptake.setTotalPrice(result.getDouble("totalprice"));
                uptake.setBilled(result.getBoolean("billed"));

            }
        } catch (Exception e) {
            throw e;
        }
        return uptake;
    }

    @Override
    public List<Uptake> find() throws Exception {
        List<Uptake> uptakes = new ArrayList<>();
        String sql = "SELECT id, measurerid, datetaked, lastvaluetaken, currentvaluetaken, basevolume, baseprice, extraprice, volumeconsumed, volumeexceeded, totalprice, billed "
                + "FROM public.uptake ORDER BY datetaked ASC;";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                Uptake uptake = new Uptake();
                uptake.setId(result.getInt("id"));
                uptake.setMeasurer(result.getInt("measurerid"));
                uptake.setDatetaked(result.getDate("datetaked"));
                uptake.setLastValueTaken(result.getDouble("lastvaluetaken"));
                uptake.setCurrentValueTaken(result.getDouble("currentvaluetaken"));
                uptake.setBaseVolume(result.getDouble("basevolume"));
                uptake.setBasePrice(result.getDouble("baseprice"));
                uptake.setExtraPrice(result.getDouble("extraprice"));
                uptake.setVolumeConsumed(result.getDouble("volumeconsumed"));
                uptake.setVolumeExceeded(result.getDouble("volumeexceeded"));
                uptake.setTotalPrice(result.getDouble("totalprice"));
                uptake.setBilled(result.getBoolean("billed"));
                uptakes.add(uptake);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return uptakes;
    }

    @Override
    public boolean update(Uptake uptake) throws Exception {
        throw new UnsupportedOperationException("Update uptake method is not supported yet.");
    }

    @Override
    public boolean delete(int id) throws Exception {
        boolean affected = false;
        String sql = "DELETE FROM public.uptake WHERE id=?;";
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

    @Override
    public List<Uptake> findNotBilled(int measurerId) throws Exception {
        List<Uptake> uptakes = new ArrayList<>();
        String sql = "SELECT id, measurerid, datetaked, lastvaluetaken, currentvaluetaken, basevolume, baseprice, extraprice, volumeconsumed, volumeexceeded, totalprice, billed "
                + "FROM public.uptake where (measurerid = ? and billed =false) ORDER BY datetaked ASC;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, measurerId));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                Uptake uptake = new Uptake();
                uptake.setId(result.getInt("id"));
                uptake.setMeasurer(result.getInt("measurerid"));
                uptake.setDatetaked(result.getDate("datetaked"));
                uptake.setLastValueTaken(result.getDouble("lastvaluetaken"));
                uptake.setCurrentValueTaken(result.getDouble("currentvaluetaken"));
                uptake.setBaseVolume(result.getDouble("basevolume"));
                uptake.setBasePrice(result.getDouble("baseprice"));
                uptake.setExtraPrice(result.getDouble("extraprice"));
                uptake.setVolumeConsumed(result.getDouble("volumeconsumed"));
                uptake.setVolumeExceeded(result.getDouble("volumeexceeded"));
                uptake.setTotalPrice(result.getDouble("totalprice"));
                uptake.setBilled(result.getBoolean("billed"));
                uptakes.add(uptake);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return uptakes;
    }

    @Override
    public List<Uptake> findByMeasurer(int measurerId) throws Exception {
        List<Uptake> uptakes = new ArrayList<>();
        String sql = "SELECT id, measurerid, datetaked, lastvaluetaken, currentvaluetaken, basevolume, baseprice, extraprice, volumeconsumed, volumeexceeded, totalprice, billed "
                + "FROM public.uptake where measurerid = ? ORDER BY id DESC lIMIT 10;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, measurerId));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                Uptake uptake = new Uptake();
                uptake.setId(result.getInt("id"));
                uptake.setMeasurer(result.getInt("measurerid"));
                uptake.setDatetaked(result.getDate("datetaked"));
                uptake.setLastValueTaken(result.getDouble("lastvaluetaken"));
                uptake.setCurrentValueTaken(result.getDouble("currentvaluetaken"));
                uptake.setBaseVolume(result.getDouble("basevolume"));
                uptake.setBasePrice(result.getDouble("baseprice"));
                uptake.setExtraPrice(result.getDouble("extraprice"));
                uptake.setVolumeConsumed(result.getDouble("volumeconsumed"));
                uptake.setVolumeExceeded(result.getDouble("volumeexceeded"));
                uptake.setTotalPrice(result.getDouble("totalprice"));
                uptake.setBilled(result.getBoolean("billed"));
                uptakes.add(uptake);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return uptakes;
    }

    @Override
    public List<Uptake> findByInvoice(int invoiceId) throws Exception {
        List<Uptake> uptakes = new ArrayList<>();
        String sql = "SELECT uptake.id, uptake.measurerid, uptake.datetaked, uptake.lastvaluetaken, uptake.currentvaluetaken, "
                + "uptake.basevolume, uptake.baseprice, uptake.extraprice, uptake.volumeconsumed, uptake.volumeexceeded, "
                + "uptake.totalprice, uptake.billed "
                + "FROM public.sapdetail as detail "
                + "INNER JOIN public.uptake on detail.uptakeid = uptake.id "
                + "INNER JOIN public.invoice on detail.invoiceid = invoice.id "
                + "WHERE invoice.id=?";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, invoiceId));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                Uptake uptake = new Uptake();
                uptake.setId(result.getInt("id"));
                uptake.setMeasurer(result.getInt("measurerid"));
                uptake.setDatetaked(result.getDate("datetaked"));
                uptake.setLastValueTaken(result.getDouble("lastvaluetaken"));
                uptake.setCurrentValueTaken(result.getDouble("currentvaluetaken"));
                uptake.setBaseVolume(result.getDouble("basevolume"));
                uptake.setBasePrice(result.getDouble("baseprice"));
                uptake.setExtraPrice(result.getDouble("extraprice"));
                uptake.setVolumeConsumed(result.getDouble("volumeconsumed"));
                uptake.setVolumeExceeded(result.getDouble("volumeexceeded"));
                uptake.setTotalPrice(result.getDouble("totalprice"));
                uptake.setBilled(result.getBoolean("billed"));
                uptakes.add(uptake);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return uptakes;
    }

    @Override
    public boolean updateToBilled(int uptakeId) throws Exception {
        boolean affected = false;
        String sql = "UPDATE public.uptake SET billed=true WHERE id=?";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, uptakeId));

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

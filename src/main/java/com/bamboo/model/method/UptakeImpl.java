package com.bamboo.model.method;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.contrat.UptakeInterface;
import com.bamboo.model.entity.Uptake;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UptakeImpl implements UptakeInterface {

    private final DBConnection DBC = new DBConnection();

    @Override
    public boolean save(Uptake uptake) throws Exception {
        boolean affected = false;
        String sql = "INSERT INTO public.uptake(measurerid, currentvaluetaken) VALUES (?, ?)";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, uptake.getMeasurer().getId()));
        dbos.add(new DBObject(2, uptake.getCurrentValueTaken()));

        if (uptake.getId() != 0) {
            sql = "INSERT INTO public.uptake(measurerid, currentvaluetaken, id) VALUES (?, ?, ?)";
            dbos.add(new DBObject(3, uptake.getId()));
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
    public Uptake findById(int id) throws Exception {
        Uptake uptake = null;
        MeasurerImpl measurerImpl = new MeasurerImpl();
        String sql = "SELECT id, measurerid, datetaked, lastvaluetaken, currentvaluetaken, basevolume, baseprice, extraprice, volumeconsumed, volumeexceeded, totalprice, billed "
                + "FROM public.uptake where id = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, id));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                uptake = new Uptake();
                uptake.setId(result.getInt("id"));
                uptake.setMeasurer(measurerImpl.findById(result.getInt("measurerid")));
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
        MeasurerImpl measurerImpl = new MeasurerImpl();
        String sql = "SELECT id, measurerid, datetaked, lastvaluetaken, currentvaluetaken, basevolume, baseprice, extraprice, volumeconsumed, volumeexceeded, totalprice, billed "
                + "FROM public.uptake ORDER BY datetaked ASC;";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                Uptake uptake = new Uptake();
                uptake.setId(result.getInt("id"));
                uptake.setMeasurer(measurerImpl.findById(result.getInt("measurerid")));
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
        } catch (Exception e) {
            throw e;
        }
        return uptakes;
    }

    @Override
    public boolean update(Uptake uptake) throws Exception {
        boolean affected = false;
        String sql = "UPDATE public.uptake SET measurerid =?, currentvaluetaken=? WHERE id=?";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, uptake.getMeasurer().getId()));
        dbos.add(new DBObject(2, uptake.getCurrentValueTaken()));
        dbos.add(new DBObject(3, uptake.getId()));

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
    public boolean delete(Uptake uptake) throws Exception {
        boolean affected = false;
        String sql = "DELETE FROM public.uptake WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, uptake.getId()));

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
        MeasurerImpl measurerImpl = new MeasurerImpl();
        String sql = "SELECT id, measurerid, datetaked, lastvaluetaken, currentvaluetaken, basevolume, baseprice, extraprice, volumeconsumed, volumeexceeded, totalprice, billed "
                + "FROM public.uptake where (measurerid = ? and billed =false) ORDER BY datetaked ASC;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, measurerId));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                Uptake uptake = new Uptake();
                uptake.setId(result.getInt("id"));
                uptake.setMeasurer(measurerImpl.findById(result.getInt("measurerid")));
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
        MeasurerImpl measurerImpl = new MeasurerImpl();
        String sql = "SELECT id, measurerid, datetaked, lastvaluetaken, currentvaluetaken, basevolume, baseprice, extraprice, volumeconsumed, volumeexceeded, totalprice, billed "
                + "FROM public.uptake where measurerid = ? ORDER BY id DESC lIMIT 10;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, measurerId));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                Uptake uptake = new Uptake();
                uptake.setId(result.getInt("id"));
                uptake.setMeasurer(measurerImpl.findById(result.getInt("measurerid")));
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
    public List<Uptake> findByInvoice(int InvoiceId) throws Exception {
        return null;
    }
}

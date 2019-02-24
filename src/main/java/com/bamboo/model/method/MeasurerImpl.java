package com.bamboo.model.method;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.contrat.MeasurerInterface;
import com.bamboo.model.entity.Measurer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MeasurerImpl implements MeasurerInterface {

    private final DBConnection DBC = new DBConnection();

    @Override
    public Measurer save(Measurer measurer) throws Exception {
        Measurer measurer1 = null;
        measurer.setNumber(random());
        String sql = "INSERT INTO public.measurer(number, sapid, installationdate ) VALUES (?, ?, ?);";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, measurer.getNumber()));
        dbos.add(new DBObject(2, measurer.getSap().getId()));
        dbos.add(new DBObject(3, measurer.getInstallationDate()));
        if (measurer.getId() != 0) {
            sql = "INSERT INTO public.measurer(number, sapid, installationdate, id) VALUES (?, ?, ?, ?);";
            dbos.add(new DBObject(4, measurer.getId()));
        }
        try {
            if (DBC.querySet(sql, dbos)) {
                measurer1 = findByNumber(measurer.getNumber());
            }
        } catch (Exception e) {
            throw e;
        }

        return measurer1;
    }

    @Override
    public Measurer findById(int id) throws Exception {
        Measurer measurer = null;

        SapImpl sapImpl = new SapImpl();
        StatusImpl statusImpl = new StatusImpl();
        String sql = "SELECT id, sapid, statusid, number, installationdate FROM public.measurer where id = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, id));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                measurer = new Measurer();
                measurer.setId(result.getInt("id"));
                measurer.setSap(sapImpl.findById(result.getInt("sapid")));
                measurer.setStatus(statusImpl.findById(result.getInt("statusid")));
                measurer.setInstallationDate(result.getDate("installationdate"));
                measurer.setNumber(result.getString("number"));

            }
        } catch (Exception e) {
            throw e;
        }
        return measurer;
    }
    
    @Override
    public Measurer findByNumber(String code) throws Exception {
        Measurer measurer = null;
        SapImpl sapImpl = new SapImpl();
        StatusImpl statusImpl = new StatusImpl();
        String sql = "SELECT id, sapid, statusid, number, installationdate FROM public.measurer where number = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, code));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                measurer = new Measurer();
                measurer.setId(result.getInt("id"));
                measurer.setSap(sapImpl.findById(result.getInt("sapid")));
                measurer.setStatus(statusImpl.findById(result.getInt("statusid")));
                measurer.setInstallationDate(result.getDate("installationdate"));
                measurer.setNumber(result.getString("number"));

            }
        } catch (Exception e) {
            throw e;
        }
        return measurer;
    }

    @Override
    public List<Measurer> find() throws Exception {
        List<Measurer> measurers = new ArrayList<>();
        SapImpl sapImpl = new SapImpl();
        StatusImpl statusImpl = new StatusImpl();
        String sql = "SELECT id, sapid, statusid, number, installationdate FROM public.measurer;";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                Measurer measurer = new Measurer();
                measurer.setId(result.getInt("id"));
                measurer.setSap(sapImpl.findById(result.getInt("sapid")));
                measurer.setStatus(statusImpl.findById(result.getInt("statusid")));
                measurer.setInstallationDate(result.getDate("installationdate"));
                measurer.setNumber(result.getString("number"));
                measurers.add(measurer);
            }
        } catch (Exception e) {
            throw e;
        }
        return measurers;
    }

    @Override
    public boolean update(Measurer measurer) throws Exception {
        boolean affected = false;
        String sql = "UPDATE public.measurer SET  sapid=?, statusid=?, number=?, installationdate=?  WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, measurer.getSap().getId()));
        dbos.add(new DBObject(2, measurer.getStatus().getId()));
        dbos.add(new DBObject(3, measurer.getNumber()));
        dbos.add(new DBObject(4, measurer.getInstallationDate()));
        dbos.add(new DBObject(5, measurer.getId()));

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
    public boolean delete(Measurer measurer) throws Exception {
        boolean affected = false;
        String sql = "DELETE FROM public.measurer WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, measurer.getId()));

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
    public List<Measurer> findBySap(int sapId) throws Exception {
        List<Measurer> measurers = new ArrayList<>();
        SapImpl sapImpl = new SapImpl();
        StatusImpl statusImpl = new StatusImpl();
        String sql = "SELECT id, sapid, statusid, number, installationdate FROM public.measurer where sapid=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, sapId));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                Measurer measurer = new Measurer();
                measurer.setId(result.getInt("id"));
                measurer.setSap(sapImpl.findById(result.getInt("sapid")));
                measurer.setStatus(statusImpl.findById(result.getInt("statusid")));
                measurer.setInstallationDate(result.getDate("installationdate"));
                measurer.setNumber(result.getString("number"));
                measurers.add(measurer);
            }
        } catch (Exception e) {
            throw e;
        }
        return measurers;
    }

    @Override
    public List<Measurer> findByStatus(int statusId) throws Exception {
        List<Measurer> measurers = new ArrayList<>();
        SapImpl sapImpl = new SapImpl();
        StatusImpl statusImpl = new StatusImpl();
        String sql = "SELECT id, sapid, statusid, number, installationdate FROM public.measurer where statusid=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, statusId));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                Measurer measurer = new Measurer();
                measurer.setId(result.getInt("id"));
                measurer.setSap(sapImpl.findById(result.getInt("sapid")));
                measurer.setStatus(statusImpl.findById(result.getInt("statusid")));
                measurer.setInstallationDate(result.getDate("installationdate"));
                measurer.setNumber(result.getString("number"));
                measurers.add(measurer);
            }
        } catch (Exception e) {
            throw e;
        }
        return measurers;
    }

    @Override
    public Map<String, Object> findMeasurerPerService() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<String> services = new ArrayList<>();
        List<Integer> amounts = new ArrayList<>();
        String sql = "SELECT s.name as service, COUNT(m.sapid) as amounts FROM sap s, measurer m WHERE s.id=m.sapid GROUP BY s.name;";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                services.add(result.getString("service"));
                amounts.add(result.getInt("amounts"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        if (services.size() > 0 && amounts.size() > 0 && services.size() == amounts.size()) {
            map.put("services", services);
            map.put("amounts", amounts);
        }
        return map;
    }

    @Override
    public Map<String, Object> findMeasurerPerStatus() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<String> status = new ArrayList<>();
        List<Integer> amounts = new ArrayList<>();
        String sql = "SELECT s.name as status, COUNT(m.statusid) as amounts FROM status s, measurer m WHERE s.id=m.statusid GROUP BY s.name;";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                status.add(result.getString("status"));
                amounts.add(result.getInt("amounts"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        if (status.size() > 0 && amounts.size() > 0 && status.size() == amounts.size()) {
            map.put("status", status);
            map.put("amounts", amounts);
        }
        return map;
    }

    private String random() {
        return UUID.randomUUID().toString().substring(0, 10);
    }

}

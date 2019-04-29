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
        Measurer newMeasurer = null;
        measurer.setNumber(random());
        String sql = "INSERT INTO public.measurer(number, sapid, installationdate ) VALUES (?, ?, ?) " +
                "RETURNING id, sapid, statusid, number, installationdate;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, measurer.getNumber()));
        dbos.add(new DBObject(2, measurer.getSap()));
        dbos.add(new DBObject(3, measurer.getInstallationDate()));
        if (measurer.getId() != 0) {
            sql = "INSERT INTO public.measurer(number, sapid, installationdate, id) VALUES (?, ?, ?, ?) " +
                    "RETURNING id, sapid, statusid, number, installationdate;";
            dbos.add(new DBObject(4, measurer.getId()));
        }
        try {
            ResultSet result = DBC.queryResultSet(sql, dbos);
            while (result.next()) {
                newMeasurer = new Measurer();
                newMeasurer.setId(result.getInt("id"));
                newMeasurer.setSap(result.getInt("sapid"));
                newMeasurer.setStatus(result.getInt("statusid"));
                newMeasurer.setInstallationDate(result.getDate("installationdate"));
                newMeasurer.setNumber(result.getString("number"));
            }
        } catch (Exception e) {
            throw e;
        }

        return newMeasurer;
    }

    @Override
    public Measurer findById(int id) throws Exception {
        Measurer measurer = null;

        String sql = "SELECT id, sapid, statusid, number, installationdate FROM public.measurer where id = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, id));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                measurer = new Measurer();
                measurer.setId(result.getInt("id"));
                measurer.setSap(result.getInt("sapid"));
                measurer.setStatus(result.getInt("statusid"));
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
        String sql = "SELECT id, sapid, statusid, number, installationdate FROM public.measurer where number = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, code));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                measurer = new Measurer();
                measurer.setId(result.getInt("id"));
                measurer.setSap(result.getInt("sapid"));
                measurer.setStatus(result.getInt("statusid"));
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
        String sql = "SELECT id, sapid, statusid, number, installationdate FROM public.measurer;";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                Measurer measurer = new Measurer();
                measurer.setId(result.getInt("id"));
                measurer.setSap(result.getInt("sapid"));
                measurer.setStatus(result.getInt("statusid"));
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
        dbos.add(new DBObject(1, measurer.getSap()));
        dbos.add(new DBObject(2, measurer.getStatus()));
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
        String sql = "SELECT id, sapid, statusid, number, installationdate FROM public.measurer where sapid=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, sapId));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                Measurer measurer = new Measurer();
                measurer.setId(result.getInt("id"));
                measurer.setSap(result.getInt("sapid"));
                measurer.setStatus(result.getInt("statusid"));
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
        String sql = "SELECT id, sapid, statusid, number, installationdate FROM public.measurer where statusid=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, statusId));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                Measurer measurer = new Measurer();
                measurer.setId(result.getInt("id"));
                measurer.setSap(result.getInt("sapid"));
                measurer.setStatus(result.getInt("statusid"));
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
    public List<Measurer> findByBeneficiary(int beneficiaryId) throws Exception {
        List<Measurer> measurers = new ArrayList<>();
        String sql = "SELECT measurer.id, measurer.sapid, measurer.statusid, measurer.number, measurer.installationdate " +
                "FROM public.assigned " +
                "inner join measurer on assigned.measurerid=measurer.id " +
                "where assigned.beneficiaryid=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, beneficiaryId));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                Measurer measurer = new Measurer();
                measurer.setId(result.getInt("id"));
                measurer.setSap(result.getInt("sapid"));
                measurer.setStatus(result.getInt("statusid"));
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
    public List<Map<String, Object>> findMeasurerPerService() throws Exception {

        List<Map<String, Object>> list = new ArrayList<>();

        String sql = "SELECT s.name as service, COUNT(m.sapid) as amounts FROM sap s, measurer m WHERE s.id=m.sapid GROUP BY s.name;";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("service", result.getString("service"));
                map.put("amount", result.getInt("amounts"));
                list.add(map);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }

        return list;
    }

    @Override
    public List<Map<String, Object>> findMeasurerPerStatus() throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "SELECT s.name as status, COUNT(m.statusid) as amounts FROM status s, measurer m WHERE s.id=m.statusid GROUP BY s.name;";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("status", result.getString("status"));
                map.put("amount", result.getInt("amounts"));
                list.add(map);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return list;
    }

    private String random() {
        return UUID.randomUUID().toString().substring(0, 10);
    }

}

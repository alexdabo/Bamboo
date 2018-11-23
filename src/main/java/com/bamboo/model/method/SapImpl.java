package com.bamboo.model.method;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.contrat.SapInterface;
import com.bamboo.model.entity.Sap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SapImpl implements SapInterface {

    private final DBConnection DBC = new DBConnection();

    @Override
    public boolean save(Sap sap) throws Exception {
        boolean affected = false;
        String sql = "insert into public.sap(name, basevolume, baseprice, extraprice) values(?, ?, ?, ?)";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, sap.getName()));
        dbos.add(new DBObject(2, sap.getBaseVolume()));
        dbos.add(new DBObject(3, sap.getBasePrice()));
        dbos.add(new DBObject(4, sap.getExtraPrice()));
        if (sap.getId() != 0) {
            sql = "insert into public.sap(name, basevolume, baseprice, extraprice, id) values(?, ?, ?, ?, ?)";
            dbos.add(new DBObject(5, sap.getId()));
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
    public Sap findById(int id) throws Exception {
        Sap sap = new Sap();
        String sql = "SELECT id, name, basevolume, baseprice, extraprice FROM public.sap where id = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, id));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                sap = new Sap();
                sap.setId(result.getInt("id"));
                sap.setName(result.getString("name"));
                sap.setBaseVolume(result.getInt("basevolume"));
                sap.setBasePrice(result.getDouble("baseprice"));
                sap.setExtraPrice(result.getDouble("extraprice"));
            }
        } catch (Exception e) {
            throw e;
        }
        return sap;
    }

    @Override
    public List<Sap> find() throws Exception {
        List<Sap> list = new ArrayList<>();
        String sql = "SELECT id, name, basevolume, baseprice, extraprice FROM public.sap order by name asc;";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                Sap sap = new Sap();
                sap.setId(result.getInt("id"));
                sap.setName(result.getString("name"));
                sap.setBaseVolume(result.getInt("basevolume"));
                sap.setBasePrice(result.getDouble("baseprice"));
                sap.setExtraPrice(result.getDouble("extraprice"));
                list.add(sap);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return list;
    }

    @Override
    public boolean update(Sap sap) throws Exception {
        boolean affected = false;
        String sql = "update public.sap set name=?,basevolume=?, baseprice=?, extraprice=? where id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, sap.getName()));
        dbos.add(new DBObject(2, sap.getBaseVolume()));
        dbos.add(new DBObject(3, sap.getBasePrice()));
        dbos.add(new DBObject(4, sap.getExtraPrice()));
        dbos.add(new DBObject(5, sap.getId()));
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
    public boolean delete(Sap sap) throws Exception {
        boolean affected = false;
        String sql = "DELETE FROM public.sap WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, sap.getId()));

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

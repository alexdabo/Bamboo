package com.bamboo.model.method;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.contrat.VillageInterface;
import com.bamboo.model.entity.Village;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VillageImpl implements VillageInterface {

    private final DBConnection DBC = new DBConnection();

    @Override
    public Village save(Village village) throws Exception {
        Village newVillage = null;
        String sql = "insert into public.village(name) values(?) RETURNING id, name;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, village.getName()));
        if (village.getId() != 0) {
            sql = "insert into public.village(name,id) values(?, ?)  RETURNING id, name;";
            dbos.add(new DBObject(2, village.getId()));
        }
        try {
            ResultSet result = DBC.queryResultSet(sql, dbos);
            while (result.next()) {
                newVillage = new Village();
                newVillage.setId(result.getInt("id"));
                newVillage.setName(result.getString("name"));
            }
        } catch (Exception e) {
            throw e;
        }

        return newVillage;
    }

    @Override
    public Village findById(int id) throws Exception {
        Village village = null;
        String sql = "SELECT id, name	FROM public.village where id = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, id));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                village = new Village();
                village.setId(result.getInt("id"));
                village.setName(result.getString("name"));
            }
        } catch (Exception e) {
            throw e;
        }
        return village;
    }

    @Override
    public List<Village> find() throws Exception {
        List<Village> villages = new ArrayList<>();
        String sql = "SELECT id, name	FROM public.village order by name asc;";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                Village village = new Village();
                village.setId(result.getInt("id"));
                village.setName(result.getString("name"));
                villages.add(village);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return villages;
    }

    @Override
    public boolean update(Village village) throws Exception {
        boolean affected = false;
        String sql = "update public.village set name=? where id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, village.getName()));
        dbos.add(new DBObject(2, village.getId()));
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
    public boolean delete(int id) throws Exception {
        boolean affected = false;
        String sql = "DELETE FROM village WHERE id=?;";
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

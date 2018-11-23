package com.bamboo.model.method;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.contrat.StatusInterface;
import com.bamboo.model.entity.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusImpl implements StatusInterface {

    private final DBConnection DBC = new DBConnection();

    @Override
    public boolean save(Status status) throws Exception {
        boolean affected = false;
        String sql = "insert into public.status(name) values(?)";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, status.getName()));
        if (status.getId() != 0) {
            sql = "insert into public.status(name,id) values(?, ?)";
            dbos.add(new DBObject(2, status.getId()));
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
    public Status findById(int id) throws Exception {
        Status status = null;
        String sql = "SELECT id, name	FROM public.status where id = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, id));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                status = new Status();
                status.setId(result.getInt("id"));
                status.setName(result.getString("name"));
            }
        } catch (Exception e) {
            throw e;
        }
        return status;
    }

    @Override
    public List<Status> find() throws Exception {
        List<Status> list = new ArrayList<>();
        String sql = "SELECT id, name	FROM public.status order by name asc;";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                Status status = new Status();
                status.setId(result.getInt("id"));
                status.setName(result.getString("name"));
                list.add(status);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return list;
    }

    @Override
    public boolean update(Status status) throws Exception {
        boolean affected = false;
        String sql = "update public.status set name=? where id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, status.getName()));
        dbos.add(new DBObject(2, status.getId()));
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
    public boolean delete(Status status) throws Exception {
        boolean affected = false;
        String sql = "DELETE FROM status WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, status.getId()));

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

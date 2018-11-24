package com.bamboo.model.method;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.contrat.AnotherServiceInterface;
import com.bamboo.model.entity.AnotherService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnotherServiceImpl implements AnotherServiceInterface {

    private final DBConnection DBC = new DBConnection();

    @Override
    public boolean save(AnotherService service) throws Exception {
        boolean affected = false;
        String sql = "INSERT INTO public.anotherservice(name, price) VALUES (?, ?);";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, service.getName()));
        dbos.add(new DBObject(2, service.getPrice()));
        if (service.getId() != 0) {
            sql = "INSERT INTO public.anotherservice(name, price, id)	VALUES (?, ?, ?);";
            dbos.add(new DBObject(3, service.getId()));
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
    public AnotherService findById(int id) throws Exception {
        AnotherService service = null;
        String sql = "SELECT id, name, price FROM public.anotherservice where id = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, id));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                service = new AnotherService();
                service.setId(result.getInt("id"));
                service.setName(result.getString("name"));
                service.setPrice(result.getDouble("price"));
            }
        } catch (Exception e) {
            throw e;
        }
        return service;
    }

    @Override
    public List<AnotherService> find() throws Exception {
        List<AnotherService> services = new ArrayList<>();
        String sql = "SELECT id, name, price FROM public.anotherservice;";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                AnotherService service = new AnotherService();
                service.setId(result.getInt("id"));
                service.setName(result.getString("name"));
                service.setPrice(result.getDouble("price"));
                services.add(service);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return services;
    }

    @Override
    public boolean update(AnotherService service) throws Exception {
        boolean affected = false;
        String sql = "update public.anotherservice set name=?, price=? where id=?;";
        service.setName(service.getName().substring(0, 1).toUpperCase() + service.getName().substring(1).toLowerCase());
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, service.getName()));
        dbos.add(new DBObject(2, service.getPrice()));
        dbos.add(new DBObject(3, service.getId()));
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
    public boolean delete(AnotherService service) throws Exception {
        boolean affected = false;
        String sql = "DELETE FROM public.anotherservice WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, service.getId()));

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

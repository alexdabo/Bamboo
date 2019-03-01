package com.bamboo.model.method;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.contrat.EntityInterface;
import com.bamboo.model.entity.Entity;
import com.bamboo.model.entity.Role;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements EntityInterface {
    private final DBConnection DBC = new DBConnection();

    @Override
    public Entity find() throws Exception {
        Entity entity = null;
        String sql = "SELECT province, canton, community, address, telephone, email, ruc FROM public.entity;";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                entity = new Entity();
                entity.setProvince(result.getString("province"));
                entity.setCanton(result.getString("canton"));
                entity.setCommunity(result.getString("community"));
                entity.setAddress(result.getString("address"));
                entity.setTelephone(result.getString("telephone"));
                entity.setEmail(result.getString("email"));
                entity.setRuc(result.getString("ruc"));
            }
        } catch (Exception e) {
            throw e;
        }
        return entity;
    }

    @Override
    public boolean update(Entity entity) throws Exception {
        boolean affected = false;
        String sql = "UPDATE public.entity SET province=?, canton=?, community=?, address=?, telephone=?, email=?, ruc=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, entity.getProvince()));
        dbos.add(new DBObject(2, entity.getCanton()));
        dbos.add(new DBObject(3, entity.getCommunity()));
        dbos.add(new DBObject(4, entity.getAddress()));
        dbos.add(new DBObject(5, entity.getTelephone()));
        dbos.add(new DBObject(6, entity.getEmail()));
        dbos.add(new DBObject(7, entity.getRuc()));


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

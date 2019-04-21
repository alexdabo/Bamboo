package com.bamboo.model.method;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.contrat.RoleInterface;
import com.bamboo.model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleImpl implements RoleInterface {

    private final DBConnection DBC = new DBConnection();

    @Override
    public Role save(Role role) throws Exception {
        Role newRole = null;
        String sql = "insert into public.role(name) values(?)  RETURNING id, name;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, role.getName()));
        if (role.getId() != 0) {
            sql = "insert into public.role(name,id) values(?, ?) RETURNING id, name;";
            dbos.add(new DBObject(2, role.getId()));
        }
        try {
            ResultSet result = DBC.queryResultSet(sql, dbos);
            while (result.next()) {
                newRole = new Role();
                newRole.setId(result.getInt("id"));
                newRole.setName(result.getString("name"));
            }
        } catch (Exception e) {
            throw e;
        }

        return newRole;
    }

    @Override
    public Role findById(int id) throws Exception {
        Role role = null;
        String sql = "SELECT id, name	FROM public.role where id = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, id));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                role = new Role();
                role.setId(result.getInt("id"));
                role.setName(result.getString("name"));
            }
        } catch (Exception e) {
            throw e;
        }
        return role;
    }

    @Override
    public List<Role> find() throws Exception {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT id, name	FROM public.role order by name asc;";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                Role role = new Role();
                role.setId(result.getInt("id"));
                role.setName(result.getString("name"));
                roles.add(role);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return roles;
    }

    @Override
    public boolean update(Role role) throws Exception {
        boolean affected = false;
        String sql = "update role set name=? where id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, role.getName()));
        dbos.add(new DBObject(2, role.getId()));
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
        String sql = "DELETE FROM public.role WHERE id=?;";
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

package com.bamboo.model.method;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bamboo.model.contrat.UserInterface;

public class UserImpl implements UserInterface {

    private final DBConnection DBC = new DBConnection();
    private final RoleImpl roleImpl = new RoleImpl();

    @Override
    public boolean save(User user) throws Exception {
        boolean affected = false;
        String sql = "INSERT INTO public.operator"
                + "(roleid, username, password, email, dni, firstname, lastname, telephone, address) "
                + "VALUES (?, ?, md5(?), ?, ?, ?, ?, ?, ?);";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, user.getRole().getId()));
        dbos.add(new DBObject(2, user.getUserName().toLowerCase()));
        dbos.add(new DBObject(3, user.getPassword()));
        dbos.add(new DBObject(4, user.getEmail()));
        dbos.add(new DBObject(5, user.getDni()));
        dbos.add(new DBObject(6, user.getFirstName().toLowerCase()));
        dbos.add(new DBObject(7, user.getLastName().toLowerCase()));
        dbos.add(new DBObject(8, user.getTelephone()));
        dbos.add(new DBObject(9, user.getAddress()));

        if (user.getId() != 0) {
            sql = "INSERT INTO public.operator"
                    + "(roleid, username, password, email, dni, firstname, lastname, telephone, address, id) "
                    + "VALUES (?, ?, md5(?), ?, ?, ?, ?, ?, ?, ?);";
            dbos.add(new DBObject(10, user.getId()));
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
    public User findById(int id) throws Exception {
        User user = null;
        String sql = "SELECT id, roleid, username, password, email, dni, firstname, lastname, telephone, address "
                + "FROM public.operator where id = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, id));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                user = new User();
                user.setId(result.getInt("id"));
                user.setRole(roleImpl.findById(result.getInt("roleid")));
                user.setUserName(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
                user.setDni(result.getString("dni"));
                user.setFirstName(capitalize(result.getString("firstname")));
                user.setLastName(capitalize(result.getString("lastname")));
                user.setTelephone(result.getString("telephone"));
                user.setAddress(result.getString("address"));
            }
        } catch (Exception e) {
            throw e;
        }
        return user;
    }

    @Override
    public List<User> find() throws Exception {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, roleid, username, password, email, dni, firstname, lastname, telephone, address "
                + "FROM public.operator  order by lastname ASC";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                User user = new User();
                user.setId(result.getInt("id"));
                user.setRole(roleImpl.findById(result.getInt("roleid")));
                user.setUserName(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
                user.setDni(result.getString("dni"));
                user.setFirstName(capitalize(result.getString("firstname")));
                user.setLastName(capitalize(result.getString("lastname")));
                user.setTelephone(result.getString("telephone"));
                user.setAddress(result.getString("address"));
                users.add(user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return users;
    }

    @Override
    public boolean update(User user) throws Exception {
        boolean affected = false;
        String sql = "UPDATE public.operator SET "
                + "roleid=?, username=?, password=?, email=?, dni=?, firstname=?, lastname=?, telephone=?, address=? "
                + "WHERE id = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, user.getRole().getId()));
        dbos.add(new DBObject(2, user.getUserName().toLowerCase()));
        dbos.add(new DBObject(3, user.getPassword()));
        dbos.add(new DBObject(4, user.getEmail()));
        dbos.add(new DBObject(5, user.getDni()));
        dbos.add(new DBObject(6, user.getFirstName().toLowerCase()));
        dbos.add(new DBObject(7, user.getLastName().toLowerCase()));
        dbos.add(new DBObject(8, user.getTelephone()));
        dbos.add(new DBObject(9, user.getAddress()));
        dbos.add(new DBObject(10, user.getId()));

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
    public boolean delete(User user) throws Exception {
        boolean affected = false;
        String sql = "DELETE FROM public.operator WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, user.getId()));

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
    public User findAndLogin(String data, String password) throws Exception {
        User user = null;
        String sql = "SELECT id, roleid, username, password, email, dni, firstname, lastname, telephone, address "
                + "FROM public.operator where (username=? or email=?) and password = md5(?);";
        data = data.toLowerCase();
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, data));
        dbos.add(new DBObject(2, data));
        dbos.add(new DBObject(3, password));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                user = new User();
                user.setId(result.getInt("id"));
                user.setRole(roleImpl.findById(result.getInt("roleid")));
                user.setUserName(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
                user.setDni(result.getString("dni"));
                user.setFirstName(capitalize(result.getString("firstname")));
                user.setLastName(capitalize(result.getString("lastname")));
                user.setTelephone(result.getString("telephone"));
                user.setAddress(result.getString("address"));

            }
        } catch (Exception e) {
            throw e;
        }
        return user;
    }

    @Override
    public List<User> findByData(String data) throws Exception {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, roleid, username, password, email, dni, firstname, lastname, telephone, address "
                + "FROM public.operator where dni like ? or lastname like ? or firstname like ? "
                + "order by lastname ASC";
        data = data.toLowerCase();
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, data.concat("%")));
        dbos.add(new DBObject(2, "%".concat(data.concat("%"))));
        dbos.add(new DBObject(3, "%".concat(data.concat("%"))));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                User user = new User();
                user.setId(result.getInt("id"));
                user.setRole(roleImpl.findById(result.getInt("roleid")));
                user.setUserName(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
                user.setDni(result.getString("dni"));
                user.setFirstName(capitalize(result.getString("firstname")));
                user.setLastName(capitalize(result.getString("lastname")));
                user.setTelephone(result.getString("telephone"));
                user.setAddress(result.getString("address"));
                users.add(user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return users;
    }

    @Override
    public boolean updatePass(User user) throws Exception {
        boolean affected = false;
        String sql = "UPDATE public.operator SET password=md5(?) WHERE id = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, user.getPassword()));
        dbos.add(new DBObject(2, user.getId()));
        try {
            if (DBC.querySet(sql, dbos)) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }

    private String capitalize(final String text) {
        String[] arr = text.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String arr1 : arr) {
            sb.append(Character.toUpperCase(arr1.charAt(0))).append(arr1.substring(1)).append(" ");
        }
        return sb.toString().trim();
    }
}

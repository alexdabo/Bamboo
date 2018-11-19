package com.bamboo.model.method.dependent;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.contrat.dependent.OperatorInterface;
import com.bamboo.model.entity.dependent.Operator;
import com.bamboo.model.method.independent.RoleImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperatorImpl implements OperatorInterface {

    private final DBConnection DBC = new DBConnection();
    private final RoleImpl roleImpl = new RoleImpl();

    @Override
    public boolean save(Operator operator) throws Exception {
        boolean affected = false;
        String sql = "INSERT INTO public.operator"
                + "(roleid, username, password, email, dni, firstname, lastname, telephone, address) "
                + "VALUES (?, ?, md5(?), ?, ?, ?, ?, ?, ?);";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, operator.getRole().getId()));
        dbos.add(new DBObject(2, operator.getUserName().toLowerCase()));
        dbos.add(new DBObject(3, operator.getPassword()));
        dbos.add(new DBObject(4, operator.getEmail()));
        dbos.add(new DBObject(5, operator.getDni()));
        dbos.add(new DBObject(6, operator.getFirstName().toLowerCase()));
        dbos.add(new DBObject(7, operator.getLastName().toLowerCase()));
        dbos.add(new DBObject(8, operator.getTelephone()));
        dbos.add(new DBObject(9, operator.getAddress()));


        if (operator.getId() != 0) {
            sql = "INSERT INTO public.operator"
                    + "(roleid, username, password, email, dni, firstname, lastname, telephone, address, id) "
                    + "VALUES (?, ?, md5(?), ?, ?, ?, ?, ?, ?, ?);";
            dbos.add(new DBObject(10, operator.getId()));
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
    public Operator findById(int id) throws Exception {
        Operator operator = null;
        String sql = "SELECT id, roleid, username, password, email, dni, firstname, lastname, telephone, address "
                + "FROM public.operator where id = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, id));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                operator = new Operator();
                operator.setId(result.getInt("id"));
                operator.setRole(roleImpl.findById(result.getInt("roleid")));
                operator.setUserName(result.getString("username"));
                operator.setPassword(result.getString("password"));
                operator.setEmail(result.getString("email"));
                operator.setDni(result.getString("dni"));
                operator.setFirstName(capitalize(result.getString("firstname")));
                operator.setLastName(capitalize(result.getString("lastname")));
                operator.setTelephone(result.getString("telephone"));
                operator.setAddress(result.getString("address"));
            }
        } catch (Exception e) {
            throw e;
        }
        return operator;
    }

    @Override
    public List<Operator> find() throws Exception {
        List<Operator> operators = new ArrayList<>();
        String sql = "SELECT id, roleid, username, password, email, dni, firstname, lastname, telephone, address "
                + "FROM public.operator  order by lastname ASC";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                Operator operator = new Operator();
                operator.setId(result.getInt("id"));
                operator.setRole(roleImpl.findById(result.getInt("roleid")));
                operator.setUserName(result.getString("username"));
                operator.setPassword(result.getString("password"));
                operator.setEmail(result.getString("email"));
                operator.setDni(result.getString("dni"));
                operator.setFirstName(capitalize(result.getString("firstname")));
                operator.setLastName(capitalize(result.getString("lastname")));
                operator.setTelephone(result.getString("telephone"));
                operator.setAddress(result.getString("address"));
                operators.add(operator);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return operators;
    }

    @Override
    public boolean update(Operator operator) throws Exception {
        boolean affected = false;
        String sql = "UPDATE public.operator SET "
                + "roleid=?, username=?, password=?, email=?, dni=?, firstname=?, lastname=?, telephone=?, address=? "
                + "WHERE id = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, operator.getRole().getId()));
        dbos.add(new DBObject(2, operator.getUserName().toLowerCase()));
        dbos.add(new DBObject(3, operator.getPassword()));
        dbos.add(new DBObject(4, operator.getEmail()));
        dbos.add(new DBObject(5, operator.getDni()));
        dbos.add(new DBObject(6, operator.getFirstName().toLowerCase()));
        dbos.add(new DBObject(7, operator.getLastName().toLowerCase()));
        dbos.add(new DBObject(8, operator.getTelephone()));
        dbos.add(new DBObject(9, operator.getAddress()));
        dbos.add(new DBObject(10, operator.getId()));

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
    public boolean delete(Operator operator) throws Exception {
        boolean affected = false;
        String sql = "DELETE FROM public.operator WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, operator.getId()));

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
    public Operator findAndLogin(String data, String password) throws Exception {
        Operator operator = null;
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
                operator = new Operator();
                operator.setId(result.getInt("id"));
                operator.setRole(roleImpl.findById(result.getInt("roleid")));
                operator.setUserName(result.getString("username"));
                operator.setPassword(result.getString("password"));
                operator.setEmail(result.getString("email"));
                operator.setDni(result.getString("dni"));
                operator.setFirstName(capitalize(result.getString("firstname")));
                operator.setLastName(capitalize(result.getString("lastname")));
                operator.setTelephone(result.getString("telephone"));
                operator.setAddress(result.getString("address"));

            }
        } catch (Exception e) {
            throw e;
        }
        return operator;
    }

    @Override
    public List<Operator> findByData(String data) throws Exception {
        List<Operator> operators = new ArrayList<>();
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
                Operator operator = new Operator();
                operator.setId(result.getInt("id"));
                operator.setRole(roleImpl.findById(result.getInt("roleid")));
                operator.setUserName(result.getString("username"));
                operator.setPassword(result.getString("password"));
                operator.setEmail(result.getString("email"));
                operator.setDni(result.getString("dni"));
                operator.setFirstName(capitalize(result.getString("firstname")));
                operator.setLastName(capitalize(result.getString("lastname")));
                operator.setTelephone(result.getString("telephone"));
                operator.setAddress(result.getString("address"));
                operators.add(operator);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return operators;
    }

    @Override
    public boolean updatePass(Operator operator) throws Exception {
        boolean affected = false;
        String sql = "UPDATE public.operator SET password=md5(?) WHERE id = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, operator.getPassword()));
        dbos.add(new DBObject(2, operator.getId()));
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

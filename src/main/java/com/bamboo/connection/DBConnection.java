/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.connection;

import java.sql.*;
import java.util.*;

/**
 *
 * @author usuario
 */
public class DBConnection {

    private Connection connnection;
    final String DRIVER = "org.postgresql.Driver";
    final String URL = "jdbc:postgresql://localhost:5432/bamboo";
    final String USER = "postgres";
    final String PASSWORD = "postgres";

    private void connect() {
        try {
            Class.forName(DRIVER);
            try {
                connnection = DriverManager.getConnection(URL, USER, PASSWORD);

            } catch (SQLException e) {
                System.out.println("Error when connecting database");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error when loading driver.");
        }
    }

    public ResultSet queryGet(String sql) throws ClassNotFoundException, SQLException {
        ResultSet result = null;

        try {
            connect();
            Statement stm = connnection.createStatement();
            result = stm.executeQuery(sql);
        } catch (SQLException e) {
            throw e;
        } finally {
            disconnect();
        }
        return result;
    }

    public ResultSet queryGet(String sql, List<DBObject> list) throws Exception {
        ResultSet result = null;
        try {
            connect();
            PreparedStatement statement = connnection.prepareStatement(sql);
            for (DBObject par : list) {
                if (par.getValor() instanceof java.util.Date) {
                    statement.setObject(par.getPosicion(),
                            new java.sql.Date(((java.util.Date) par.getValor()).getTime()));
                } else {
                    statement.setObject(par.getPosicion(), par.getValor());
                }
            }
            result = statement.executeQuery();
        } catch (SQLException e) {
            throw e;
        } finally {
            disconnect();
        }
        return result;
    }

    public boolean querySet(String sql) throws Exception {
        boolean affected = false;
        try {
            connect();
            Statement stm = connnection.createStatement();
            if (stm.executeUpdate(sql) > 0) {
                affected = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            disconnect();
        }
        return affected;
    }

    public boolean querySet(String sql, List<DBObject> lst) throws Exception {
        boolean affected = false;
        try {
            connect();
            PreparedStatement statement = connnection.prepareStatement(sql);
            for (DBObject prm : lst) {
                if (prm.getValor() instanceof java.util.Date) {
//                    java.sql.Date fechaBD;
//                    java.util.Date fechaOriginal = (java.util.Date) prm.getValor();
//                    fechaBD = new java.sql.Date(fechaOriginal.getTime());
//                    statement.setObject(prm.getPosicion(), fechaBD);
                    statement.setObject(prm.getPosicion(),
                            new java.sql.Date(((java.util.Date) prm.getValor()).getTime()));
                } else {
                    statement.setObject(prm.getPosicion(), prm.getValor());
                }
            }
            if (statement.executeUpdate() >= 0) {
                affected = true;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            disconnect();
        }
        return affected;
    }

    public void disconnect() {
        if (connnection != null) {
            try {
                connnection.close();
            } catch (SQLException e) {
                System.err.println("Database cannot be disconnected.");
            }
        }
    }

    public Connection connectReport() {
        try {
            Class.forName(DRIVER);
            try {
                connnection = DriverManager.getConnection(URL, USER, PASSWORD);

            } catch (SQLException e) {
                System.out.println("Error when connecting database");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error when loading driver.");
        }
        return connnection;
    }
}

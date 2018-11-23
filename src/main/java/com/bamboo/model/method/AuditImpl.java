package com.bamboo.model.method;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.contrat.AuditInterface;
import com.bamboo.model.entity.Audit;

import java.util.ArrayList;
import java.util.List;

public class AuditImpl implements AuditInterface {

    private final DBConnection DBC = new DBConnection();
    private final String TABLE;

    public AuditImpl(Class E) {
        this.TABLE = E.getSimpleName();
    }

    @Override
    public void save(Audit audit) {
        audit.setActionName("Save");
        registrate(audit);
    }

    @Override
    public void Update(Audit audit) {
        audit.setActionName("Update");
        registrate(audit);
    }

    @Override
    public void delete(Audit audit) {
        audit.setActionName("Delete");
        registrate(audit);
    }

    @Override
    public List<Audit> find() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Audit> findByDate(String date) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Audit> findByOperator(int operatorId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Audit> findByOperatorDate(int operatorId, String date) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void registrate(Audit audit) {
        String sql = "INSERT INTO public.audit( operatorid, tableaffected, actionname, description) VALUES (?, ?, ?, ?);";
        audit.setTableAffected(TABLE);
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, audit.getOperator().getId()));
        dbos.add(new DBObject(2, audit.getTableAffected()));
        dbos.add(new DBObject(3, audit.getActionName()));
        dbos.add(new DBObject(4, audit.getDescription()));
        try {
            if (DBC.querySet(sql, dbos)) {
                System.out.println("Registered: " + audit);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

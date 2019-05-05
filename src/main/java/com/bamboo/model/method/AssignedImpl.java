package com.bamboo.model.method;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.contrat.AssignedInterface;
import com.bamboo.model.entity.Assigned;
import com.bamboo.model.entity.Measurer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssignedImpl implements AssignedInterface {

    private final DBConnection DBC = new DBConnection();

    @Override
    public Assigned save(Assigned assigned) throws Exception {
        Assigned newAssigned = null;
        String sql = "insert into public.assigned(beneficiaryid, measurerid ) values(?, ?) "
                + "RETURNING id, beneficiaryid, measurerid, debt, assignmentdate, status;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, assigned.getBeneficiary()));
        dbos.add(new DBObject(2, assigned.getMeasurer()));
        if (assigned.getId() != 0) {
            sql = "insert into public.assigned(beneficiaryid, measurerid, id) values(?, ?, ?) "
                    + "RETURNING id, beneficiaryid, measurerid, debt, assignmentdate, status;";
            dbos.add(new DBObject(3, assigned.getId()));
        }
        try {
            ResultSet result = DBC.queryResultSet(sql, dbos);
            while (result.next()) {
                newAssigned = new Assigned();
                newAssigned.setId(result.getInt("id"));
                newAssigned.setBeneficiary(result.getInt("beneficiaryid"));
                newAssigned.setMeasurer(result.getInt("measurerid"));
                newAssigned.setAssignmentDate(result.getDate("assignmentdate"));
                newAssigned.setStatus(result.getString("status"));
                newAssigned.setDebt(result.getDouble("debt"));
            }
        } catch (Exception e) {
            throw e;
        }
        return newAssigned;
    }

    @Override
    public Assigned findById(int id) throws Exception {
        Assigned assigned = null;
        String sql = "SELECT id, beneficiaryid, measurerid, debt, assignmentdate, status FROM public.assigned WHERE id=? ;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, id));

        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                assigned = new Assigned();
                assigned.setId(result.getInt("id"));
                assigned.setBeneficiary(result.getInt("beneficiaryid"));
                assigned.setMeasurer(result.getInt("measurerid"));
                assigned.setAssignmentDate(result.getDate("assignmentdate"));
                assigned.setStatus(result.getString("status"));
                assigned.setDebt(result.getDouble("debt"));

            }
        } catch (Exception e) {
            throw e;
        }
        return assigned;
    }

    @Override
    public List<Assigned> find() throws Exception {
        List<Assigned> assigneds = new ArrayList<>();
        String sql = "SELECT id, beneficiaryid, measurerid, debt, assignmentdate, status FROM public.assigned;";

        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                Assigned assigned = new Assigned();
                assigned.setId(result.getInt("id"));
                assigned.setBeneficiary(result.getInt("beneficiaryid"));
                assigned.setMeasurer(result.getInt("measurerid"));
                assigned.setAssignmentDate(result.getDate("assignmentdate"));
                assigned.setStatus(result.getString("status"));
                assigned.setDebt(result.getDouble("debt"));
                assigneds.add(assigned);

            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return assigneds;
    }

    @Override
    public List<Assigned> findDistinctBeneficiary() throws Exception {
        List<Assigned> assigneds = new ArrayList<>();
        String sql = "SELECT DISTINCT ON (beneficiaryid) id, beneficiaryid, measurerid, debt, assignmentdate, status FROM public.assigned;";

        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                Assigned assigned = new Assigned();
                assigned.setId(result.getInt("id"));
                assigned.setBeneficiary(result.getInt("beneficiaryid"));
                assigned.setMeasurer(result.getInt("measurerid"));
                assigned.setAssignmentDate(result.getDate("assignmentdate"));
                assigned.setStatus(result.getString("status"));
                assigned.setDebt(result.getDouble("debt"));
                assigneds.add(assigned);

            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return assigneds;
    }

    @Override
    public boolean update(Assigned assigned) throws Exception {
        boolean affected = false;
        String sql = "UPDATE public.assigned SET debt=?, assignmentdate=?, status=? WHERE beneficiaryid=? and measurerid=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, assigned.getDebt()));
        dbos.add(new DBObject(2, assigned.getAssignmentDate()));
        dbos.add(new DBObject(3, assigned.getStatus()));
        dbos.add(new DBObject(4, assigned.getBeneficiary()));
        dbos.add(new DBObject(5, assigned.getMeasurer()));
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
        MeasurerImpl measurerImpl = new MeasurerImpl();
        String sql = "DELETE FROM assigned WHERE id=?;";
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

    @Override
    public List<Assigned> findByBeneficiary(int beneficiaryId) throws Exception {
        List<Assigned> assigneds = new ArrayList<>();
        String sql = "SELECT id, beneficiaryid, measurerid, debt, assignmentdate, status FROM public.assigned WHERE beneficiaryid=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, beneficiaryId));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                Assigned assigned = new Assigned();
                assigned.setId(result.getInt("id"));
                assigned.setBeneficiary(result.getInt("beneficiaryid"));
                assigned.setMeasurer(result.getInt("measurerid"));
                assigned.setAssignmentDate(result.getDate("assignmentdate"));
                assigned.setStatus(result.getString("status"));
                assigned.setDebt(result.getDouble("debt"));
                assigneds.add(assigned);

            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return assigneds;
    }

    @Override
    public boolean disableByMeasurer(int measurerId) throws Exception {
        boolean affected = false;
        String sql = "UPDATE public.assigned SET status='disable' WHERE measurerid=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, measurerId));
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
    public Assigned findByActiveMeasurer(int measurerId) throws Exception {
        Assigned assigned = null;
        String sql = "SELECT id, beneficiaryid, measurerid, debt, assignmentdate, status FROM public.assigned WHERE measurerid=? and status='enable' ;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, measurerId));

        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                assigned = new Assigned();
                assigned.setId(result.getInt("id"));
                assigned.setBeneficiary(result.getInt("beneficiaryid"));
                assigned.setMeasurer(result.getInt("measurerid"));
                assigned.setAssignmentDate(result.getDate("assignmentdate"));
                assigned.setStatus(result.getString("status"));
                assigned.setDebt(result.getDouble("debt"));

            }
        } catch (Exception e) {
            throw e;
        }
        return assigned;
    }

}

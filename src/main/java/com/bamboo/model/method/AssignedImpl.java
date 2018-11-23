package com.bamboo.model.method;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.contrat.AssignedInterface;
import com.bamboo.model.entity.Assigned;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AssignedImpl implements AssignedInterface {

    private final DBConnection DBC = new DBConnection();

    @Override
    public Assigned save(Assigned assigned) throws Exception {
        MeasurerImpl measurerImpl = new MeasurerImpl();
        String sql = "insert into public.assigned(beneficiaryid, measurerid, assignmentdate ) values(?, ?, ?)";
        List<DBObject> dbos = new ArrayList<>();

        try {
            assigned.setMeasurer(measurerImpl.save(assigned.getMeasurer()));

            if (assigned.getMeasurer() != null) {
                dbos.add(new DBObject(1, assigned.getBeneficiary().getId()));
                dbos.add(new DBObject(2, assigned.getMeasurer().getId()));
                dbos.add(new DBObject(3, assigned.getAssignmentDate()));
            }
            if (DBC.querySet(sql, dbos)) {
                assigned = findById(assigned.getBeneficiary().getId(), assigned.getMeasurer().getId());
            }
        } catch (Exception e) {
            throw e;
        }
        return assigned;
    }

    @Override
    public Assigned findById(int beneficiaryId, int measurerId) throws Exception {
        Assigned assigned = null;
        BeneficiaryImpl beneficiary = new BeneficiaryImpl();
        MeasurerImpl measurer = new MeasurerImpl();
        String sql = "SELECT beneficiaryid, measurerid, assignmentdate, status FROM public.assigned WHERE beneficiaryid=? AND measurerid=? ;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, beneficiaryId));
        dbos.add(new DBObject(2, measurerId));

        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                assigned = new Assigned();
                assigned.setBeneficiary(beneficiary.findById(result.getInt("beneficiaryid")));
                assigned.setMeasurer(measurer.findById(result.getInt("measurerid")));
                assigned.setAssignmentDate(result.getDate("assignmentdate"));
                assigned.setStatus(result.getString("status"));
            }
        } catch (Exception e) {
            throw e;
        }
        return assigned;
    }

    @Override
    public List<Assigned> find() throws Exception {
        List<Assigned> list = new ArrayList<>();
        BeneficiaryImpl beneficiary = new BeneficiaryImpl();
        MeasurerImpl measurer = new MeasurerImpl();
        String sql = "SELECT beneficiaryid, measurerid, assignmentdate, status FROM public.assigned ORDER BY assignmentdate ASC;";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                Assigned assigned = new Assigned();
                assigned.setBeneficiary(beneficiary.findById(result.getInt("beneficiaryid")));
                assigned.setMeasurer(measurer.findById(result.getInt("measurerid")));
                assigned.setAssignmentDate(result.getDate("assignmentdate"));
                assigned.setStatus(result.getString("status"));
                list.add(assigned);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    @Override
    public boolean update(Assigned assigned) throws Exception {
        boolean affected = false;
        MeasurerImpl measurer = new MeasurerImpl();
        String sql = "UPDATE public.assigned set status=? WHERE  beneficiaryid=? and measurerid=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, assigned.getStatus()));
        dbos.add(new DBObject(2, assigned.getBeneficiary().getId()));
        dbos.add(new DBObject(3, assigned.getMeasurer().getId()));

        try {
            if (DBC.querySet(sql, dbos)) {
                affected = measurer.update(assigned.getMeasurer());
            }
        } catch (Exception e) {
            throw e;
        }

        return affected;
    }

    @Override
    public boolean delete(Assigned assigned) throws Exception {
        boolean affected = false;
        MeasurerImpl measurer = new MeasurerImpl();
        String sql = "DELETE FROM public.assigned WHERE beneficiaryid=? and measurerid=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, assigned.getBeneficiary().getId()));
        dbos.add(new DBObject(2, assigned.getMeasurer().getId()));

        try {
            if (DBC.querySet(sql, dbos)) {
                affected = measurer.delete(assigned.getMeasurer());
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }
}

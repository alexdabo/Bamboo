package com.bamboo.model.method;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.contrat.AssignedInterface;
import com.bamboo.model.entity.Assigned;
import com.bamboo.model.entity.AssignedMeasurer;
import com.bamboo.model.entity.Beneficiary;
import com.bamboo.model.entity.Measurer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AssignedImpl implements AssignedInterface {

    private final DBConnection DBC = new DBConnection();

    @Override
    public boolean save(Assigned assigned) throws Exception {
        boolean affected = false;
        MeasurerImpl measurerImpl = new MeasurerImpl();
        String sql = "insert into public.assigned(beneficiaryid, measurerid ) values(?, ?)";

        try {
            for (AssignedMeasurer item : assigned.getAssigneds()) {
                Measurer measurer = null;
                //Cuando se asigna un nuevo medidor a un beneficiario
                if (item.getMeasurer().getId() == 0) {
                    measurer = measurerImpl.save(item.getMeasurer());
                }
                //Cuando se realiza un cambio de propietario "beneficiario"
                else {
                    measurer = item.getMeasurer();
                }

                // Assignar el mediror al beneficiario
                if (measurer != null) {
                    List<DBObject> dbos = new ArrayList<>();
                    dbos.add(new DBObject(1, assigned.getBeneficiary().getId()));
                    dbos.add(new DBObject(2, measurer.getId()));
                    if (DBC.querySet(sql, dbos)) {
                        affected = true;
                    }
                }

            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }

    @Override
    public Assigned findById(int beneficiaryId, int measurerId) throws Exception {
        Assigned assigned = null;
        BeneficiaryImpl beneficiaryImpl = new BeneficiaryImpl();
        MeasurerImpl measurerImpl = new MeasurerImpl();
        String sql = "SELECT beneficiaryid, measurerid, debt, assignmentdate, status FROM public.assigned WHERE beneficiaryid=? AND measurerid=? ;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, beneficiaryId));
        dbos.add(new DBObject(2, measurerId));

        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                assigned = new Assigned();
                assigned.setBeneficiary(beneficiaryImpl.findById(result.getInt("beneficiaryid")));

                AssignedMeasurer measurer = new AssignedMeasurer();
                measurer.setMeasurer(measurerImpl.findById(result.getInt("measurerid")));
                measurer.setAssignmentDate(result.getDate("assignmentdate"));
                measurer.setStatus(result.getString("status"));
                measurer.setDebt(result.getDouble("debt"));
                assigned.addAssigned(measurer);


            }
        } catch (Exception e) {
            throw e;
        }
        return assigned;
    }

    @Override
    public Assigned findByBeneficiary(int beneficiaryId) throws Exception {
        Assigned assigned = null;
        BeneficiaryImpl beneficiaryImpl = new BeneficiaryImpl();
        try {
            assigned = new Assigned();
            assigned.setBeneficiary(beneficiaryImpl.findById(beneficiaryId));
            assigned.setAssigneds(assignedToBeneficiary(beneficiaryId));
        } catch (Exception e) {
            throw e;
        }
        return assigned;
    }


    @Override
    public List<Assigned> find() throws Exception {
        List<Assigned> list = new ArrayList<>();
        BeneficiaryImpl beneficiaryImpl = new BeneficiaryImpl();
        try {
            for (Beneficiary beneficiary : beneficiaryImpl.find()) {
                Assigned assigned = new Assigned();
                assigned.setBeneficiary(beneficiary);
                assigned.setAssigneds(assignedToBeneficiary(beneficiary.getId()));
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
        MeasurerImpl measurerImpl = new MeasurerImpl();
        String sql = "UPDATE public.assigned SET debt=?, assignmentdate=?, status=? WHERE beneficiaryid=? and measurerid=?;";

        try {
            for (AssignedMeasurer item : assigned.getAssigneds()) {
                //Actualizar los datos del medidor
                if (measurerImpl.update(item.getMeasurer())) {
                    affected = true;
                }

                // Actualizar datos del medidor asignado

                List<DBObject> dbos = new ArrayList<>();
                dbos.add(new DBObject(1, item.getDebt()));
                dbos.add(new DBObject(2, item.getAssignmentDate()));
                dbos.add(new DBObject(3, item.getStatus()));
                dbos.add(new DBObject(4, assigned.getBeneficiary().getId()));
                dbos.add(new DBObject(5, item.getMeasurer().getId()));

                // Actualiza la tabla assigned y verifica que medidores se actualizo
                if (DBC.querySet(sql, dbos)) {
                    affected = affected && true;
                }else{
                    affected = affected && false;
                }

            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }

    @Override
    public boolean delete(Assigned assigned) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<AssignedMeasurer> assignedToBeneficiary(int beneficiaryId) {
        List<AssignedMeasurer> list = new ArrayList<>();
        MeasurerImpl measurerImpl = new MeasurerImpl();
        String sql = "SELECT measurerid, assignmentdate, debt, status FROM assigned WHERE beneficiaryid = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, beneficiaryId));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                AssignedMeasurer assigned = new AssignedMeasurer();
                assigned.setMeasurer(measurerImpl.findById(result.getInt("measurerid")));
                assigned.setAssignmentDate(result.getDate("assignmentdate"));
                assigned.setStatus(result.getString("status"));
                assigned.setDebt(result.getDouble("debt"));

                list.add(assigned);
            }

        } catch (Exception e) {
        }
        return list;
    }

}

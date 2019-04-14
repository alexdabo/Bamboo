package com.bamboo.model.method;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.contrat.BeneficiaryInterface;
import com.bamboo.model.entity.Beneficiary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeneficiaryImpl implements BeneficiaryInterface {

    private DBConnection DBC = new DBConnection();

    @Override
    public boolean save(Beneficiary beneficiary) throws Exception {
        boolean affected = false;
        String sql = "INSERT INTO public.beneficiary(dni, lastname, firstname, address, placereference, villageid, telephone) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?);";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, beneficiary.getDni()));
        dbos.add(new DBObject(2, beneficiary.getLastName().toLowerCase()));
        dbos.add(new DBObject(3, beneficiary.getFirstName().toLowerCase()));
        dbos.add(new DBObject(4, beneficiary.getAddress()));
        dbos.add(new DBObject(5, beneficiary.getPlaceReference()));
        dbos.add(new DBObject(6, beneficiary.getVillage()));
        dbos.add(new DBObject(7, beneficiary.getTelephone()));

        if (beneficiary.getId() != 0) {
            sql = "INSERT INTO public.beneficiary(dni, lastname, firstname, address, placereference, villageid, telephone, id) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            dbos.add(new DBObject(8, beneficiary.getId()));
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
    public Beneficiary findById(int id) throws Exception {
        Beneficiary beneficiary = null;
        String sql = "SELECT id, dni, lastname, firstname, address, placereference, villageid, telephone FROM public.beneficiary where id = ?";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, id));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                beneficiary = new Beneficiary();
                beneficiary.setId(result.getInt("id"));
                beneficiary.setDni(result.getString("dni"));
                beneficiary.setLastName(capitalize(result.getString("lastname")));
                beneficiary.setFirstName(capitalize(result.getString("firstname")));
                beneficiary.setAddress(result.getString("address"));
                beneficiary.setPlaceReference(result.getString("placereference"));
                try {
                    beneficiary.setVillage(result.getInt("villageid"));
                } catch (Exception e) {
                }
                beneficiary.setTelephone(result.getString("telephone"));
            }
        } catch (Exception e) {
            throw e;
        }
        return beneficiary;
    }

    @Override
    public List<Beneficiary> find() throws Exception {
        List<Beneficiary> beneficiaries = new ArrayList<>();
        String sql = "SELECT id, dni, lastname, firstname, address, placereference, villageid, telephone "
                + "FROM public.beneficiary  order by lastname ASC;";

        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                Beneficiary beneficiary = new Beneficiary();
                beneficiary.setId(result.getInt("id"));
                beneficiary.setDni(result.getString("dni"));
                beneficiary.setLastName(capitalize(result.getString("lastname")));
                beneficiary.setFirstName(capitalize(result.getString("firstname")));
                beneficiary.setAddress(result.getString("address"));
                beneficiary.setPlaceReference(result.getString("placereference"));
                try {
                    beneficiary.setVillage(result.getInt("villageid"));
                } catch (Exception e) {
                }
                beneficiary.setTelephone(result.getString("telephone"));
                beneficiaries.add(beneficiary);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return beneficiaries;
    }

    @Override
    public boolean update(Beneficiary beneficiary) throws Exception {
        boolean affected = false;
        String sql = "UPDATE public.beneficiary SET dni=?, lastname=?, firstname=?, address=?, placereference=?, villageid=?, telephone=? WHERE id = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, beneficiary.getDni()));
        dbos.add(new DBObject(2, beneficiary.getLastName().toLowerCase()));
        dbos.add(new DBObject(3, beneficiary.getFirstName().toLowerCase()));
        dbos.add(new DBObject(4, beneficiary.getAddress()));
        dbos.add(new DBObject(5, beneficiary.getPlaceReference()));
        dbos.add(new DBObject(6, beneficiary.getVillage()));
        dbos.add(new DBObject(7, beneficiary.getTelephone()));
        dbos.add(new DBObject(8, beneficiary.getId()));
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
        String sql = "DELETE FROM public.beneficiary WHERE id=?;";
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
    public List<Beneficiary> findByData(String data, int villageId) throws Exception {
        List<Beneficiary> beneficiaries = new ArrayList<>();
        VillageImpl villageImpl = new VillageImpl();
        List<DBObject> dbos = new ArrayList<>();
        data = data.toLowerCase();
        dbos.add(new DBObject(1, data.concat("%")));
        dbos.add(new DBObject(2, "%".concat(data.concat("%"))));
        dbos.add(new DBObject(3, "%".concat(data.concat("%"))));

        String sql = "SELECT id, dni, lastname, firstname, address, placereference, villageid, telephone "
                + "FROM public.beneficiary where dni like ? or lastname like ? or firstname like ? order by lastName asc;";
        if (villageId != 0) {
            sql = "SELECT id, dni, lastname, firstname, address, placereference, villageid, telephone "
                    + "FROM public.beneficiary where (dni like ? or lastname like ? or firstname like ?) and villageid=? "
                    + "order by lastName asc;";
            dbos.add(new DBObject(4, villageId));
        }

        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                Beneficiary beneficiary = new Beneficiary();
                beneficiary.setId(result.getInt("id"));
                beneficiary.setDni(result.getString("dni"));
                beneficiary.setLastName(capitalize(result.getString("lastname")));
                beneficiary.setFirstName(capitalize(result.getString("firstname")));
                beneficiary.setAddress(result.getString("address"));
                beneficiary.setPlaceReference(result.getString("placereference"));
                beneficiary.setVillage(result.getInt("villageid"));
                beneficiary.setTelephone(result.getString("telephone"));
                beneficiaries.add(beneficiary);
            }
        } catch (Exception e) {
            throw e;
        }
        return beneficiaries;
    }

    @Override
    public List<Map<String, Object>> peopleFromVillages() throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "Select v.name as village, COUNT(b.villageid) as amounts From village v, beneficiary b where v.id=b.villageid group by v.name";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("village", result.getString("village"));
                map.put("amount", result.getInt("amounts"));
                list.add(map);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return list;
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

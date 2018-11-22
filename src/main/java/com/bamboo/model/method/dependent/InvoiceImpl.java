package com.bamboo.model.method.dependent;

import com.bamboo.connection.DBConnection;
import com.bamboo.connection.DBObject;
import com.bamboo.model.contrat.dependent.InvoiceInterface;
import com.bamboo.model.entity.dependent.Invoice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class InvoiceImpl implements InvoiceInterface {

    private DBConnection DBC = new DBConnection();

    @Override
    public Invoice save(Invoice invoice) throws Exception {
        Invoice invoice1 = null;
        invoice.setNumber(random());
        String sql = "INSERT INTO public.invoice( beneficiaryid, debtcollectorid, number, totaltopay, payed) VALUES (?, ?, ?, ?, ?);";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, invoice.getBeneficiary().getId()));
        dbos.add(new DBObject(2, invoice.getDebtcollector().getId()));
        dbos.add(new DBObject(3, invoice.getNumber()));
        dbos.add(new DBObject(4, invoice.getTotalToPay()));
        dbos.add(new DBObject(5, invoice.isPayed()));

        if (invoice.getId() != 0) {
            sql = "INSERT INTO public.invoice( beneficiaryid, debtcollectorid, number, totaltopay, payed, id )	VALUES (?, ?, ?, ?, ?, ?);";
            dbos.add(new DBObject(6, invoice.getId()));
        }
        try {
            if (DBC.querySet(sql, dbos)) {
                invoice1 = findByCode(invoice.getNumber());
            }
        } catch (Exception e) {
            throw e;
        }

        return invoice1;
    }

    @Override
    public Invoice findById(int id) throws Exception {
        Invoice invoice = null;
        BeneficiaryImpl beneficiaryImpl = new BeneficiaryImpl();
        OperatorImpl operatorImpl = new OperatorImpl();
        String sql = "SELECT id, beneficiaryid, debtcollectorid, number, TO_CHAR(dateofissue, 'yyyy-MM-dd HH24:MI:SS') as dateofissue, totaltopay, payed FROM public.invoice where id = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, id));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                invoice = new Invoice();
                invoice.setId(result.getInt("id"));
                invoice.setBeneficiary(beneficiaryImpl.findById(result.getInt("beneficiaryid")));
                invoice.setDebtcollector(operatorImpl.findById(result.getInt("debtcollectorid")));
                invoice.setNumber(result.getString("number"));
                invoice.setDateOfIssue(result.getString("dateofissue"));
                invoice.setTotalToPay(result.getDouble("totaltopay"));
                invoice.setIsPayed(result.getBoolean("payed"));
            }
        } catch (Exception e) {
            throw e;
        }
        return invoice;
    }

    @Override
    public List<Invoice> find() throws Exception {
        List<Invoice> invoices = new ArrayList<>();
        BeneficiaryImpl beneficiaryImpl = new BeneficiaryImpl();
        OperatorImpl operatorImpl = new OperatorImpl();
        String sql = "SELECT id, beneficiaryid, debtcollectorid, number, TO_CHAR(dateofissue, 'yyyy-MM-dd HH24:MI:SS') as dateofissue, totaltopay, payed FROM public.invoice ORDER BY dateofissue ASC;";
        try {
            ResultSet result = DBC.queryGet(sql);
            while (result.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(result.getInt("id"));
                invoice.setBeneficiary(beneficiaryImpl.findById(result.getInt("beneficiaryid")));
                invoice.setDebtcollector(operatorImpl.findById(result.getInt("debtcollectorid")));
                invoice.setNumber(result.getString("number"));
                invoice.setDateOfIssue(result.getString("dateofissue"));
                invoice.setTotalToPay(result.getDouble("totaltopay"));
                invoice.setIsPayed(result.getBoolean("payed"));
                invoices.add(invoice);
            }
        } catch (Exception e) {
            throw e;
        }
        return invoices;
    }

    @Override
    public boolean update(Invoice invoice) throws Exception {
        boolean affected = false;
        String sql = "UPDATE public.invoice set  beneficiaryid=?, debtcollectorid=?, number=?, totaltopay=?, payed=? WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, invoice.getBeneficiary().getId()));
        dbos.add(new DBObject(2, invoice.getDebtcollector().getId()));
        dbos.add(new DBObject(3, invoice.getNumber()));
        dbos.add(new DBObject(4, invoice.getTotalToPay()));
        dbos.add(new DBObject(5, invoice.isPayed()));
        dbos.add(new DBObject(6, invoice.getId()));
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
    public boolean delete(Invoice invoice) throws Exception {
        boolean affected = false;
        String sql = "DELETE FROM public.invoice WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, invoice.getId()));

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
    public List<Invoice> findByBeneficiary(int beneficiaryId) throws Exception {
        List<Invoice> invoices = new ArrayList<>();
        BeneficiaryImpl beneficiaryImpl = new BeneficiaryImpl();
        OperatorImpl operatorImpl = new OperatorImpl();
        String sql = " SELECT id, beneficiaryid, debtcollectorid, number, TO_CHAR(dateofissue, 'yyyy-MM-dd HH24:MI:SS') as dateofissue, totaltopay, payed FROM PUBLIC.invoice WHERE  beneficiaryid=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, beneficiaryId));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(result.getInt("id"));
                invoice.setBeneficiary(beneficiaryImpl.findById(result.getInt("beneficiaryid")));
                invoice.setDebtcollector(operatorImpl.findById(result.getInt("debtcollectorid")));
                invoice.setNumber(result.getString("number"));
                invoice.setDateOfIssue(result.getString("dateofissue"));
                invoice.setTotalToPay(result.getDouble("totaltopay"));
                invoice.setIsPayed(result.getBoolean("payed"));
                invoices.add(invoice);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }

        return invoices;
    }

    @Override
    public List<Invoice> findByDate(Object date) throws Exception {
        List<Invoice> invoices = new ArrayList<>();
        BeneficiaryImpl beneficiaryImpl = new BeneficiaryImpl();
        OperatorImpl operatorImpl = new OperatorImpl();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateOfIssue = "";
        if (date instanceof Date) {
            dateOfIssue = sdf.format(date);
        } else {
            dateOfIssue = (String) date;
        }

        String sql = "SELECT id, beneficiaryid, debtcollectorid, number, TO_CHAR(dateofissue, 'yyyy-MM-dd HH24:MI:SS') as dateofissue, totaltopay, payed "
                + "FROM public.invoice where TO_CHAR(dateofissue, 'yyyy-MM-dd')=?";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, dateOfIssue));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(result.getInt("id"));
                invoice.setBeneficiary(beneficiaryImpl.findById(result.getInt("beneficiaryid")));
                invoice.setDebtcollector(operatorImpl.findById(result.getInt("debtcollectorid")));
                invoice.setNumber(result.getString("number"));
                invoice.setDateOfIssue(result.getString("dateofissue"));
                invoice.setTotalToPay(result.getDouble("totaltopay"));
                invoice.setIsPayed(result.getBoolean("payed"));
                invoices.add(invoice);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return invoices;
    }

    private Invoice findByCode(String code) throws Exception {
        Invoice invoice = null;
        BeneficiaryImpl beneficiaryImpl = new BeneficiaryImpl();
        OperatorImpl operatorImpl = new OperatorImpl();
        String sql = "SELECT id, beneficiaryid, debtcollectorid, number, TO_CHAR(dateofissue, 'yyyy-MM-dd HH24:MI:SS') as dateofissue, totaltopay, payed FROM public.invoice where number = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, code));
        try {
            ResultSet result = DBC.queryGet(sql, dbos);
            while (result.next()) {
                invoice = new Invoice();
                invoice.setId(result.getInt("id"));
                invoice.setBeneficiary(beneficiaryImpl.findById(result.getInt("beneficiaryid")));
                invoice.setDebtcollector(operatorImpl.findById(result.getInt("debtcollectorid")));
                invoice.setNumber(result.getString("number"));
                invoice.setDateOfIssue(result.getString("dateofissue"));
                invoice.setTotalToPay(result.getDouble("totaltopay"));
                invoice.setIsPayed(result.getBoolean("payed"));
            }
        } catch (Exception e) {
            throw e;
        }
        return invoice;
    }

    private String random() {
        return UUID.randomUUID().toString().substring(0, 10);
    }
}

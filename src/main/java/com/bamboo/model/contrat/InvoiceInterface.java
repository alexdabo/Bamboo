package com.bamboo.model.contrat;

import com.bamboo.model.entity.Invoice;

import java.util.List;

public interface InvoiceInterface {

    public Invoice save(Invoice invoice) throws Exception;

    public Invoice findById(int id) throws Exception;

    public List<Invoice> find() throws Exception;

    public List<Invoice> findByDate(String date) throws Exception;

    public boolean update(Invoice invoice) throws Exception;

    public boolean delete(Invoice invoice) throws Exception;

    public List<Invoice> findByBeneficiary(int beneficiaryId) throws Exception;

    public List<Invoice> findByDate(Object date) throws Exception;
}

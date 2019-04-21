package com.bamboo.model.contrat;

import com.bamboo.model.entity.Uptake;

import java.util.List;

public interface UptakeInterface extends CRUD<Uptake> {

    public boolean updateToBilled(int uptakeId) throws Exception;

    public List<Uptake> findNotBilled(int measurerId) throws Exception;

    public List<Uptake> findByMeasurer(int measurerId) throws Exception;

    public List<Uptake> findByInvoice(int InvoiceId) throws Exception;

}

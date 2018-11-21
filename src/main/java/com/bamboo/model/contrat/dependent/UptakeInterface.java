package com.bamboo.model.contrat.dependent;

import com.bamboo.model.contrat.CRUD;
import com.bamboo.model.entity.dependent.Uptake;

import java.util.List;

public interface UptakeInterface  extends CRUD<Uptake> {

    public List<Uptake> findNotBilled(int measurerId) throws Exception;

    public List<Uptake> findByInvoice(int InvoiceId) throws Exception;

}

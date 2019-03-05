package com.bamboo.model.contrat;

import com.bamboo.model.entity.SapDetail;

public interface SapDetailInterface extends CRUD<SapDetail> {
    public SapDetail create(SapDetail detail) throws Exception;
    public SapDetail findByInvoice(int invoiceId) throws Exception;

}

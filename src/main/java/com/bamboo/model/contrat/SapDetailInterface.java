package com.bamboo.model.contrat;

import com.bamboo.model.entity.SapDetail;
import java.util.List;

public interface SapDetailInterface extends CRUD<SapDetail> {
    
    public List<SapDetail> findByInvoice(int invoiceId) throws Exception;

}

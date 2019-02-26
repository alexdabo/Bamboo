package com.bamboo.model.contrat;

import com.bamboo.model.entity.SapDetail;

public interface SapDetailInterface extends CRUD<SapDetail> {
    public SapDetail create(SapDetail detail) throws Exception;
}

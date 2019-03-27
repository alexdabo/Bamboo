package com.bamboo.model.contrat;

import com.bamboo.model.entity.AnotherService;
import com.bamboo.model.entity.AnotherServiceDetail;

import java.util.List;

public interface AnotherServiceDetailInterface {

    public boolean save(AnotherServiceDetail service) throws Exception;

    public List<AnotherServiceDetail> findByBeneficiary(int beneficiaryId) throws Exception;

    public List<AnotherServiceDetail> find() throws Exception;

    public boolean delete(AnotherServiceDetail service) throws Exception;
}

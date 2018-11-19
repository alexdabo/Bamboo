package com.bamboo.model.contrat.dependent;

import com.bamboo.model.contrat.CRUD;
import com.bamboo.model.entity.dependent.Beneficiary;

import java.util.List;
import java.util.Map;

public interface BeneficiaryInterface extends CRUD<Beneficiary> {

    public List<Beneficiary> findByData(String data, int villageId) throws Exception;

    public Map<String, Object> peopleFromVillages() throws Exception;
}

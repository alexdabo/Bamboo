package com.bamboo.model.contrat;

import com.bamboo.model.entity.Assigned;
import com.bamboo.model.entity.Beneficiary;

import java.util.List;

public interface AssignedInterface extends CRUD<Assigned> {


    public boolean disableByMeasurer(int measurerId) throws Exception;

    public Assigned findByActiveMeasurer(int measurerId) throws Exception;

    public List<Assigned> findByBeneficiary(int beneficiaryId) throws Exception;

    public List<Assigned> findDistinctBeneficiary() throws Exception;

}

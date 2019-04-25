package com.bamboo.model.contrat;

import com.bamboo.model.entity.Assigned;

import java.util.List;

public interface AssignedInterface extends CRUD<Assigned> {

    public List<Assigned> findByBeneficiary(int beneficiaryId) throws Exception;

    public List<Assigned> findDistinctBeneficiary() throws Exception;

}

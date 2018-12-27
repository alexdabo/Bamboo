package com.bamboo.model.contrat;

import com.bamboo.model.entity.Assigned;

import java.util.List;

public interface AssignedInterface {

    public Assigned save(Assigned assigned) throws Exception;

    public Assigned findById(int beneficiaryId, int measurerId) throws Exception;

    public Assigned findByBeneficiary(int beneficiaryId) throws Exception;

    public List<Assigned> find() throws Exception;

    public boolean update(Assigned assigned) throws Exception;

    public boolean delete(Assigned assigned) throws Exception;
}

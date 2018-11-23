package com.bamboo.model.contrat;

import com.bamboo.model.entity.Audit;

import java.util.List;

public interface AuditInterface {

    public void save(Audit audit);

    public void Update(Audit audit);

    public void delete(Audit audit);

    public List<Audit> find() throws Exception;

    public List<Audit> findByOperator(int operatorId) throws Exception;

    public List<Audit> findByDate(String date) throws Exception;

    public List<Audit> findByOperatorDate(int operatorId, String date) throws Exception;
}

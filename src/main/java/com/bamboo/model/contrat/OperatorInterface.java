/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.model.contrat;

import com.bamboo.model.entity.Operator;
import java.util.List;

/**
 *
 * @author alexander
 */
public interface OperatorInterface extends CRUD<Operator> {

    public Operator findAndLogin(String data, String password) throws Exception;

    public List<Operator> findByData(String data) throws Exception;

    public boolean updatePass(Operator operator) throws Exception;
}

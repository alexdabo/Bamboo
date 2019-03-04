/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.model.contrat;

import com.bamboo.model.entity.Entity;

import java.util.List;

/**
 * @author alexander
 */
public interface EntityInterface {

    public Entity find() throws Exception;

    public boolean update(Entity entity) throws Exception;
}

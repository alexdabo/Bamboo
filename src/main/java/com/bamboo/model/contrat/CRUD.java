/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.model.contrat;

import java.util.List;

/**
 *
 * @author alexander
 */
public interface CRUD<E> {

    public boolean save(E element) throws Exception;

    public E findById(int id) throws Exception;

    public List<E> find() throws Exception;

    public boolean update(E element) throws Exception;

    public boolean delete(E element) throws Exception;

}

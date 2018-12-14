/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.model.contrat;

import com.bamboo.model.entity.User;
import java.util.List;

/**
 *
 * @author alexander
 */
public interface UserInterface extends CRUD<User> {

    public User findAndLogin(String data, String password) throws Exception;

    public List<User> findByData(String data) throws Exception;

    public boolean updatePass(User user) throws Exception;
}

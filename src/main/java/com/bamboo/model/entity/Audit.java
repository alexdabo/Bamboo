/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.model.entity;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Audit {

    private int id;
    private User user;
    private Date executedDate;
    private String tableAffected;
    private String actionName;
    private String description;

    public Audit() {
    }

    public Audit(User user, String description) {
        this.user = user;
        this.description = description;
    }

    public Audit(int userId, String description) {
        this.user = new User();
        this.user.setId(userId);
        this.description = description;
    }

    public Audit(int id, User user, Date executedDate, String tableAffected, String actionName, String description) {
        this.id = id;
        this.user = user;
        this.executedDate = executedDate;
        this.tableAffected = tableAffected;
        this.actionName = actionName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOperator() {
        return user;
    }

    public void setOperator(User user) {
        this.user = user;
    }

    public Date getExecutedDate() {
        return executedDate;
    }

    public void setExecutedDate(Date executedDate) {
        this.executedDate = executedDate;
    }

    public String getTableAffected() {
        return tableAffected;
    }

    public void setTableAffected(String tableAffected) {
        this.tableAffected = tableAffected;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Audit{"
                + "id=" + id
                + ", user=" + user.getUserName()
                + ", executedDate=" + executedDate
                + ", tableAffected='" + tableAffected + '\''
                + ", actionName='" + actionName + '\''
                + ", description='" + description + '\''
                + '}';
    }
}

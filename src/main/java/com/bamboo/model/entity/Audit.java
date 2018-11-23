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
    private Operator operator;
    private Date executedDate;
    private String tableAffected;
    private String actionName;
    private String description;

    public Audit() {
    }

    public Audit(Operator operator, String description) {
        this.operator = operator;
        this.description = description;
    }

    public Audit(int operatorId, String description) {
        this.operator = new Operator();
        this.operator.setId(operatorId);
        this.description = description;
    }

    public Audit(int id, Operator operator, Date executedDate, String tableAffected, String actionName, String description) {
        this.id = id;
        this.operator = operator;
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

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
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
                + ", operator=" + operator
                + ", executedDate=" + executedDate
                + ", tableAffected='" + tableAffected + '\''
                + ", actionName='" + actionName + '\''
                + ", description='" + description + '\''
                + '}';
    }
}

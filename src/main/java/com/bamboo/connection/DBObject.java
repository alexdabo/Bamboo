/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.connection;

public class DBObject {

    private int position;
    private Object value;

    public DBObject(int position, Object value) {
        this.position = position;
        this.value = value;
    }

    public Object getValor() {
        return value;
    }

    public void setValor(Object value) {
        this.value = value;
    }

    public int getPosicion() {
        return position;
    }

    public void setPosicion(int position) {
        this.position = position;
    }

}

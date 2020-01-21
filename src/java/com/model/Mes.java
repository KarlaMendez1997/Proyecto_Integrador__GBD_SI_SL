/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

/**
 *
 * @author Ideapad 330
 */
public class Mes {
    private int id;
    private String decrip;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the decrip
     */
    public String getDecrip() {
        return decrip;
    }

    /**
     * @param decrip the decrip to set
     */
    public void setDecrip(String decrip) {
        this.decrip = decrip;
    }

    public Mes(int id, String decrip) {
        this.id = id;
        this.decrip = decrip;
    }
    
}

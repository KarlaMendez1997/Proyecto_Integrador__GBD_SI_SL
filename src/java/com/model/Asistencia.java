/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Ideapad 330
 */
@ManagedBean(name = "asis")
@ViewScoped
public class Asistencia implements Serializable{
    private String id;
    private String descrip;
    private List<String> list_asis;
    
    @PostConstruct
    public void inicializador(){
        setList_asis(new ArrayList<>());
        Conexion c = new Conexion();
        ResultSet rs = c.Consulta("select * from d_asistido_por");
        try {
            while(rs.next()){
               getList_asis().add(rs.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Asistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the descrip
     */
    public String getDescrip() {
        return descrip;
    }

    /**
     * @param descrip the descrip to set
     */
    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    /**
     * @return the list_asis
     */
    public List<String> getList_asis() {
        return list_asis;
    }

    /**
     * @param list_asis the list_asis to set
     */
    public void setList_asis(List<String> list_asis) {
        this.list_asis = list_asis;
    }
            
            
            
}

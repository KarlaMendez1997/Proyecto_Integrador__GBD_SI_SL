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
 * @author Lenovo 330S
 */

@ManagedBean(name = "mes")
@ViewScoped
public class Tiempo implements Serializable{
     private String mes;
    
    private List<Mes> list_mes;
    private String año;
    private String trimestre;

    @PostConstruct
    public void incializar(){
        setList_mes(new ArrayList());
        Conexion c = new Conexion();
        ResultSet rs = c.Consulta("select * from tb_mes");
        try {
            while (rs.next()){
                getList_mes().add(new Mes(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoCivil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    
    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    
    public String getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

 

    public List<Mes> getList_mes() {
        return list_mes;
    }

    public void setList_mes(List<Mes> list_mes) {
        this.list_mes = list_mes;
    }
    
    
    

    
}

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
@ManagedBean(name = "civil")
@ViewScoped
public class EstadoCivil implements Serializable{
    private String descripcion;
    private List list_descripcion;

    @PostConstruct
    public void incializar(){
        setList_descripcion(new ArrayList());
        Conexion c = new Conexion();
        ResultSet rs = c.Consulta("select * from tb_est_civil");
        try {
            while (rs.next()){
                getList_descripcion().add(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoCivil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the list_descripcion
     */
    public List getList_descripcion() {
        return list_descripcion;
    }

    /**
     * @param list_descripcion the list_descripcion to set
     */
    public void setList_descripcion(List list_descripcion) {
        this.list_descripcion = list_descripcion;
    }
    
    
     
}

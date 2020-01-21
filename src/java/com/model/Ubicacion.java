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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "ubicacion")
@ViewScoped
public class Ubicacion implements Serializable{
    private String nom_provincia;
    private String nom_canton;
    private String nom_parroquia;
    
    private List<String> list_provincia;
    private List<String> list_canton;
    private List<String> list_parroquia;

    @PostConstruct
    public void inicializador(){ 
        setList_provincia(new ArrayList<>());
        Conexion c = new Conexion();
        ResultSet rs = c.Consulta("select * from tb_provincia");
        
        try {
            while(rs.next()){ 
                getList_provincia().add(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ubicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void inicializador_parroquia(){  

        setList_parroquia(new ArrayList<>());
        Conexion c = new Conexion();
        ResultSet rs = c.Consulta("select * from tb_parroquia,tb_canton,tb_provincia \n" +
        "where tb_parroquia.cod_canton = tb_canton.cod_canton \n" +
        "and tb_parroquia.cod_provincia = tb_provincia.cod_provincia\n" +
        "and tb_provincia.nombre_provincia = '"+nom_provincia+"'\n" +
        "and tb_canton.nombre_canton = '"+nom_canton+"'");
       
        try {
            while(rs.next()){ 
                getList_parroquia().add(rs.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ubicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void inicializador_canton(){  

          setList_canton(new ArrayList<>());
        Conexion c = new Conexion();
        ResultSet rs = c.Consulta("select * from tb_canton,tb_provincia where tb_canton.cod_provincia = tb_provincia.cod_provincia and tb_provincia.nombre_provincia = '" +nom_provincia+"'");
        
        try {
            while(rs.next()){ 
                getList_canton().add(rs.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ubicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * @return the nom_provincia
     */
    public String getNom_provincia() {
        return nom_provincia;
    }

    /**
     * @param nom_provincia the nom_provincia to set
     */
    public void setNom_provincia(String nom_provincia) {
        this.nom_provincia = nom_provincia;
    }

    /**
     * @return the nom_canton
     */
    public String getNom_canton() {
  
        return nom_canton;
    }

    /**
     * @param nom_canton the nom_canton to set
     */
    public void setNom_canton(String nom_canton) {
        this.nom_canton = nom_canton;
    }

    /**
     * @return the nom_parroquia
     */
    public String getNom_parroquia() {
        return nom_parroquia;
    }

    /**
     * @param nom_parroquia the nom_parroquia to set
     */
    public void setNom_parroquia(String nom_parroquia) {
        this.nom_parroquia = nom_parroquia;
    }

    /**
     * @return the list_provincia
     */
    public List<String> getList_provincia() {
        return list_provincia;
    }

    /**
     * @param list_provincia the list_provincia to set
     */
    public void setList_provincia(List<String> list_provincia) {
        this.list_provincia = list_provincia;
    }

    /**
     * @return the list_canton
     */
    public List<String> getList_canton() {
        return list_canton;
    }

    /**
     * @param list_canton the list_canton to set
     */
    public void setList_canton(List<String> list_canton) {
        this.list_canton = list_canton;
    }

    /**
     * @return the list_parroquia
     */
    public List<String> getList_parroquia() {
        return list_parroquia;
    }

    /**
     * @param list_parroquia the list_parroquia to set
     */
    public void setList_parroquia(List<String> list_parroquia) {
        this.list_parroquia = list_parroquia;
    }

    
}

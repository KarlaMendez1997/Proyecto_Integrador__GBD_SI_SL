package com.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
@ManagedBean(name="US")
@ViewScoped
public class Usuario implements Serializable{
    private String cedula;
    private int rol;
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;
    private List<Usuario> list_usuario;

    public List<Usuario> getList_usuario() {
        return list_usuario;
    }

    public void setList_usuario(List<Usuario> list_usuario) {
        this.list_usuario = list_usuario;
    }
            
   
    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getRol() {
        return rol;
    }

    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the contraseña
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * @param contraseña the contraseña to set
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Usuario() {
    }
    
    public Usuario(String cedula, int rol, String nombre, String apellido, String correo, String contraseña) {
        this.cedula = cedula;
        this.rol = rol;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
    }
   public void consultaUsuario(){
       setList_usuario(new ArrayList<>());
       Conexion c  = new Conexion();
       ResultSet rs = c.Consulta("select * from usuario");
        System.out.println("consultabd ");
        try {
            while(rs.next()){ System.out.println(rs.getString(1));
                getList_usuario().add(new Usuario(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
 
   public void eliminarUsuario(String ced){
       Conexion c = new Conexion();System.out.println("eliminando "+ced);
  String temp=     c.Ejecutar("delete from usuario where ced_usuario = '"+ced+"'");
        System.out.println(temp);
   }
    
}

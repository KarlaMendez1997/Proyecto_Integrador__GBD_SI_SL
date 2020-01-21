/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.IOException;
import java.io.PrintWriter;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lenovo 330S
 */
public class registro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Conexion c = new Conexion();
        String cedula=request.getParameter("cedula");
        int rol = 2;
        String nombre=request.getParameter("nombre");
        String apellido=request.getParameter("apellido");
        String correo=request.getParameter("correo");
        String contraseña=request.getParameter("passwd");

        String re = c.Ejecutar("insert into usuario values ('" + cedula + "'," + rol + ",'" + nombre + "','" + apellido + "','" + correo + "','" + contraseña + "')");
        System.out.println(re);
        if (re.equals("Datos insertados")) {
            response.sendRedirect("Views/index.jsf");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No se registro Correctamente", "Ingrese datos correctos"));
        }

    }

}

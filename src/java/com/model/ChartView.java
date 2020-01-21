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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.*;

@ManagedBean(name = "cv")
@ViewScoped
public class ChartView implements Serializable {

    private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;
    private PieChartModel pieModel1;
    private  ArrayList<madre>obj;
  
    @PostConstruct
    public void init() {
        
        setObj(new ArrayList<>());
        obj.add(new madre("",0));
        createAnimatedModels();
        //createPieModel1();
    }

    public ArrayList<madre> getObj() {
        return obj;
    }

    public void setObj(ArrayList<madre> obj) {
        this.obj = obj;
    }

   

    
    

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }

    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

    /*private void createPieModel1() {
        pieModel1 = new PieChartModel();
        for (madre obj1 : obj) {
            pieModel1.set(obj1.getDescripcion(),obj1.getCantidad());
        }

        pieModel1.setTitle("Reporte en Pastel");
        pieModel1.setLegendPosition("w");
        pieModel1.setShadow(false);
    }*/

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Madre");        
        for (madre obj1 : obj) {
            boys.set(obj1.getDescripcion(),obj1.getCantidad());
        }
        

        model.addSeries(boys);

        return model;
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Madre");
        for (madre obj1 : obj) {
            series1.set(obj1.getDescripcion(),obj1.getCantidad());
        }
    
        model.addSeries(series1);
        return model;
    }

    private void createAnimatedModels() {
        animatedModel1 = initLinearModel();
        animatedModel1.setTitle("Reporte Lineal");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setMin(1);
      //  yAxis.setMax(1500);
                 yAxis.setMax(600000);
        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Reporte en Barras");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(1);
        //yAxis.setMax(1500);
        yAxis.setMax(600000);
    }

    public void dibujar(String año,String trim,String mes,String prov,String cant,String parro,String civil,String asis){
    Conexion x = new Conexion();
    String con1 = " periodo = '"+año+"";
    String con2 = " "+trim+"";
    String con3 = (mes.equals(".. Seleccione") || mes.isEmpty() )?"":" "+mes+"' ";
    String con4 = (prov.equals(".. Seleccione") || prov.isEmpty())?"":" and ubicacion = '"+prov+"";
    String con5 = (cant.equals(".. Seleccione") || cant.isEmpty())?"":" "+cant+"";
    String con6 = (parro.equals(".. Seleccione") || parro.isEmpty())?"":" "+parro+"' ";
    String con7 = (civil.equals(".. Seleccione") || civil.isEmpty())?"":" and descr_civil = '"+civil+"' ";
    String con8 = (asis.equals(".. Seleccione") || asis.isEmpty())?"":" and descripcion_asistencia = '"+asis+"' ";
    String con9 = " group by descripcion_prod_embarazo";
   // String sql = "select descripcion_prod_embarazo,count(descripcion_prod_embarazo) from th_madre_vista where "+con1+con2+con3+con4+con5+con6+con7+con8+con9 ;
    String sql = "select tb_est_civil.desc_civil as Provincia,\n" +
" sum(total) as Total_de_nacidos\n" +
" from tb_est_civil\n" +
" inner join th_nacimientos1 \n" +
" on tb_est_civil.id_est_civil = th_nacimientos1.id_est_civil\n" +
" group by provincia";
   
   
   
//String sql1="select descripcion_prod_embarazo,count(descripcion_prod_embarazo) from th_madre_vista  group by descripcion_prod_embarazo";
    System.out.println(sql);
    ResultSet rs =x.Consulta(sql);
    obj.clear();
        try {
            while(rs.next()){System.out.println("conultando para dibujae");
               obj.add(new madre(rs.getString(1),rs.getInt(2)));
            }   
        } catch (SQLException ex) {
            Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
        }
        createAnimatedModels();
    }
}

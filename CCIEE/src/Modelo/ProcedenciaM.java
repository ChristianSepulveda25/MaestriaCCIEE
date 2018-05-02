/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.ProcedenciaC;
import Vista.ProcedenciaV;
import com.mysql.jdbc.Connection;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author INFORMATICA-05
 */
public class ProcedenciaM {
    
    private int id_pro;
    private String ins_pro;
    private String sig_pro;
    private String nom_pro;
    private String ape_pat_pro;
    private String ape_mat_pro;
    private String car_pro;
    private String tel_pro;
    private String busqueda;
    private int tipo_pro;
    ProcedenciaV pv = new ProcedenciaV();
    DefaultTableModel modelo = new DefaultTableModel();

    public int getId_pro() {
        return id_pro;
    }

    public void setId_pro(int id_pro) {
        this.id_pro = id_pro;
    }

    public String getIns_pro() {
        return ins_pro;
    }

    public void setIns_pro(String ins_pro) {
        this.ins_pro = ins_pro;
    }

    public String getSig_pro() {
        return sig_pro;
    }

    public void setSig_pro(String sig_pro) {
        this.sig_pro = sig_pro;
    }

    public String getNom_pro() {
        return nom_pro;
    }

    public void setNom_pro(String nom_pro) {
        this.nom_pro = nom_pro;
    }

    public String getApe_pat_pro() {
        return ape_pat_pro;
    }

    public void setApe_pat_pro(String ape_pat_pro) {
        this.ape_pat_pro = ape_pat_pro;
    }

    public String getApe_mat_pro() {
        return ape_mat_pro;
    }

    public void setApe_mat_pro(String ape_mat_pro) {
        this.ape_mat_pro = ape_mat_pro;
    }

    public String getCar_pro() {
        return car_pro;
    }

    public void setCar_pro(String car_pro) {
        this.car_pro = car_pro;
    }

    public String getTel_pro() {
        return tel_pro;
    }

    public void setTel_pro(String tel_pro) {
        this.tel_pro = tel_pro;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public int getTipo_pro() {
        return tipo_pro;
    }

    public void setTipo_pro(int tipo_pro) {
        this.tipo_pro = tipo_pro;
    }
    
    public boolean AltaProcedencia(String institucion, String siglas, String nombre, String apepaterno, String apematerno, String cargo, String telefono, int tipo_pro){
       Conexion cc = new Conexion();
       Connection cn = cc.getConexion();
       
       try {
            CallableStatement proc = cn.prepareCall("CALL AltaProcedencia(?,?,?,?,?,?,?,?)");
            proc.setString(1, getIns_pro());
            proc.setString(2, getSig_pro());
            proc.setString(3, getNom_pro());
            proc.setString(4, getApe_pat_pro());
            proc.setString(5, getApe_mat_pro());
            proc.setString(6, getCar_pro());
            proc.setString(7, getTel_pro());
            proc.setInt(8, getTipo_pro());
            
            proc.execute();
            
            return true;
        }catch (SQLException e) {                  
            System.out.println(e);
            return false;
        }finally{
            try{
                cn.close();
            }catch(SQLException e){
                System.out.println(e);
            }
        }
    }
    
    public boolean ModificarProcedencia(int id, String institucion, String siglas, String nombre, String apepaterno, String apematerno, String cargo, String telefono, int tipo_pro){
        Conexion cc = new Conexion();
        Connection cn = cc.getConexion();
       
        try {
            CallableStatement proc = cn.prepareCall("CALL ModificarProcedencia(?,?,?,?,?,?,?,?,?)");
            proc.setInt(1, getId_pro());
            proc.setString(2, getIns_pro());
            proc.setString(3, getSig_pro());
            proc.setString(4, getNom_pro());
            proc.setString(5, getApe_pat_pro());
            proc.setString(6, getApe_mat_pro());
            proc.setString(7, getCar_pro());
            proc.setString(8, getTel_pro());
            proc.setInt(9, getTipo_pro());
            
            proc.execute();
            
            return true;
        }catch (SQLException e) {                  
            System.out.println(e);
            return false;
        }finally{
            try{
                cn.close();
            }catch(SQLException e){
                System.out.println(e);
            }
        }
    }
     
    public ArrayList<ProcedenciaM> listPersona(){
    Conexion cc = new Conexion();
    Connection cn = cc.getConexion();
        
    ArrayList listaPersona = new ArrayList();
    ProcedenciaM persona;
        try{
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM procedencia");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                persona = new ProcedenciaM();
                
                persona.setId_pro(rs.getInt(1));
                persona.setIns_pro(rs.getString(2));
                persona.setSig_pro(rs.getString(3));
                persona.setNom_pro(rs.getString(4));
                persona.setApe_pat_pro(rs.getString(5));
                persona.setApe_mat_pro(rs.getString(6));
                persona.setCar_pro(rs.getString(7));
                persona.setTel_pro(rs.getString(8));  
                persona.setTipo_pro(rs.getInt(9));
                listaPersona.add(persona);
            }           
        }catch(Exception e){
        }
    return listaPersona;
    }
    
    public boolean EliminarProcedencia(int id){
        Conexion cc = new Conexion();
        Connection cn = cc.getConexion();
       
        try {
            CallableStatement proc = cn.prepareCall("CALL EliminarProcedencia(?)");
            proc.setInt(1, getId_pro());
            
            proc.execute();
            
            return true;
        }catch (SQLException e) {                  
            System.out.println(e);
            return false;
        }finally{
            try{
                cn.close();
            }catch(SQLException e){
                System.out.println(e);
            }
        }
    }
    
    public ArrayList<ProcedenciaM> BuscPersona(){
    Conexion cc = new Conexion();
    Connection cn = cc.getConexion();
        
    ArrayList listaPersona = new ArrayList();
    ProcedenciaM persona;
        try{
            String var = null;
            var = getBusqueda();
            Statement ps = cn.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM procedencia WHERE CONCAT(ins_pro, sig_pro) LIKE '%"+var+"%'");
            while (rs.next()){
                persona = new ProcedenciaM();
                
                persona.setId_pro(rs.getInt(1));
                persona.setIns_pro(rs.getString(2));
                persona.setSig_pro(rs.getString(3));
                persona.setNom_pro(rs.getString(4));
                persona.setApe_pat_pro(rs.getString(5));
                persona.setApe_mat_pro(rs.getString(6));
                persona.setCar_pro(rs.getString(7));
                persona.setTel_pro(rs.getString(8));  
                listaPersona.add(persona);
            }           
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            try{
                cn.close();
            }catch(SQLException e){
                System.out.println(e);
            }
        }
    return listaPersona;
    }
    
}

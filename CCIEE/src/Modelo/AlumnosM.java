/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.AlumnosV;
import com.mysql.jdbc.Connection;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author INFORMATICA-05
 */
public class AlumnosM {
    
    AlumnosV av = new AlumnosV();
    
    /*=== ALUMNO ===*/
    private int id_alu;
    private String mat_alu;
    private String nom_alu;
    private String ape_pat_alu;
    private String ape_mat_alu;
    private java.sql.Date fec_nac_alu;
    private String curp_alu;
    private int edad_alu;
    private boolean sexo_alu;
    private String tel_alu;
    private String cel_alu;
    private String email_alu;
    private String est_civ_alu;
    private FileInputStream foto_al;
    private InputStream foto_al1;
    private int tip_pag_alu;
    private int pag_alu_alu;
    private int pag_pro_alu;
    private int sta_alu;
    private String nom_pro;
    
    private String ins_pro;
    private String sig_pro;
    
    /*=== Domicilio ===*/
    private String calle_dom;
    private String no_dom;
    private String col_dom;
    private int cp_dom;
    private String mun_dom;
    private String est_dom;
    
    /*=== Documentación ===*/
    
    private boolean sol_doc;
    private boolean act_nac_doc;
    private boolean cred_ele_doc;
    private boolean curp_doc;
    private boolean cart_exp_mot_doc;
    private boolean foto_doc;
    private boolean tit_doc;
    private boolean ced_doc;
    private String obs_doc;
    
    /*=== Escolaridad ===*/
    
    private int niv_esc;
    private String nom_uni_esc;
    private String ciu_uni_esc;
    private String est_uni_esc;
    private String nom_est_esc;
    private boolean fin_esc;
    private boolean tit_esc;
    private String obs_esc;

    public int getId_alu() {
        return id_alu;
    }

    public void setId_alu(int id_alu) {
        this.id_alu = id_alu;
    }

    public String getMat_alu() {
        return mat_alu;
    }

    public void setMat_alu(String mat_alu) {
        this.mat_alu = mat_alu;
    }

    public String getNom_alu() {
        return nom_alu;
    }

    public void setNom_alu(String nom_alu) {
        this.nom_alu = nom_alu;
    }

    public String getApe_pat_alu() {
        return ape_pat_alu;
    }

    public void setApe_pat_alu(String ape_pat_alu) {
        this.ape_pat_alu = ape_pat_alu;
    }

    public String getApe_mat_alu() {
        return ape_mat_alu;
    }

    public void setApe_mat_alu(String ape_mat_alu) {
        this.ape_mat_alu = ape_mat_alu;
    }

    public java.sql.Date getFec_nac_alu() {
        return fec_nac_alu;
    }

    public void setFec_nac_alu(java.sql.Date fec_nac_alu) {
        this.fec_nac_alu = fec_nac_alu;
    }

    public String getCurp_alu() {
        return curp_alu;
    }

    public void setCurp_alu(String curp_alu) {
        this.curp_alu = curp_alu;
    }

    public int getEdad_alu() {
        return edad_alu;
    }

    public void setEdad_alu(int edad_alu) {
        this.edad_alu = edad_alu;
    }

    public boolean isSexo_alu() {
        return sexo_alu;
    }

    public void setSexo_alu(boolean sexo_alu) {
        this.sexo_alu = sexo_alu;
    }

    public String getTel_alu() {
        return tel_alu;
    }

    public void setTel_alu(String tel_alu) {
        this.tel_alu = tel_alu;
    }

    public String getCel_alu() {
        return cel_alu;
    }

    public void setCel_alu(String cel_alu) {
        this.cel_alu = cel_alu;
    }

    public String getEmail_alu() {
        return email_alu;
    }

    public void setEmail_alu(String email_alu) {
        this.email_alu = email_alu;
    }

    public String getEst_civ_alu() {
        return est_civ_alu;
    }

    public void setEst_civ_alu(String est_civ_alu) {
        this.est_civ_alu = est_civ_alu;
    }

    public FileInputStream getFoto_al() {
        return foto_al;
    }

    public void setFoto_al(FileInputStream foto_al) {
        this.foto_al = foto_al;
    }

    public InputStream getFoto_al1() {
        return foto_al1;
    }

    public void setFoto_al1(InputStream foto_al1) {
        this.foto_al1 = foto_al1;
    }

    public int getTip_pag_alu() {
        return tip_pag_alu;
    }

    public void setTip_pag_alu(int tip_pag_alu) {
        this.tip_pag_alu = tip_pag_alu;
    }

    public int getPag_alu_alu() {
        return pag_alu_alu;
    }

    public void setPag_alu_alu(int pag_alu_alu) {
        this.pag_alu_alu = pag_alu_alu;
    }

    public int getPag_pro_alu() {
        return pag_pro_alu;
    }

    public void setPag_pro_alu(int pag_pro_alu) {
        this.pag_pro_alu = pag_pro_alu;
    }

    public int getSta_alu() {
        return sta_alu;
    }

    public void setSta_alu(int sta_alu) {
        this.sta_alu = sta_alu;
    }

    public String getNom_pro() {
        return nom_pro;
    }

    public void setNom_pro(String nom_pro) {
        this.nom_pro = nom_pro;
    }
    
    ////////////////////////////////////////////////////////////////////////////

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
    
    ////////////////////////////////////////////////////////////////////////////

    public String getCalle_dom() {
        return calle_dom;
    }

    public void setCalle_dom(String calle_dom) {
        this.calle_dom = calle_dom;
    }

    public String getNo_dom() {
        return no_dom;
    }

    public void setNo_dom(String no_dom) {
        this.no_dom = no_dom;
    }

    public String getCol_dom() {
        return col_dom;
    }

    public void setCol_dom(String col_dom) {
        this.col_dom = col_dom;
    }

    public int getCp_dom() {
        return cp_dom;
    }

    public void setCp_dom(int cp_dom) {
        this.cp_dom = cp_dom;
    }

    public String getMun_dom() {
        return mun_dom;
    }

    public void setMun_dom(String mun_dom) {
        this.mun_dom = mun_dom;
    }

    public String getEst_dom() {
        return est_dom;
    }

    public void setEst_dom(String est_dom) {
        this.est_dom = est_dom;
    }

    public boolean isSol_doc() {
        return sol_doc;
    }

    public void setSol_doc(boolean sol_doc) {
        this.sol_doc = sol_doc;
    }

    public boolean isAct_nac_doc() {
        return act_nac_doc;
    }

    public void setAct_nac_doc(boolean act_nac_doc) {
        this.act_nac_doc = act_nac_doc;
    }

    public boolean isCred_ele_doc() {
        return cred_ele_doc;
    }

    public void setCred_ele_doc(boolean cred_ele_doc) {
        this.cred_ele_doc = cred_ele_doc;
    }

    public boolean isCurp_doc() {
        return curp_doc;
    }

    public void setCurp_doc(boolean curp_doc) {
        this.curp_doc = curp_doc;
    }

    public boolean isCart_exp_mot_doc() {
        return cart_exp_mot_doc;
    }

    public void setCart_exp_mot_doc(boolean cart_exp_mot_doc) {
        this.cart_exp_mot_doc = cart_exp_mot_doc;
    }

    public boolean isFoto_doc() {
        return foto_doc;
    }

    public void setFoto_doc(boolean foto_doc) {
        this.foto_doc = foto_doc;
    }

    public boolean isTit_doc() {
        return tit_doc;
    }

    public void setTit_doc(boolean tit_doc) {
        this.tit_doc = tit_doc;
    }

    public boolean isCed_doc() {
        return ced_doc;
    }

    public void setCed_doc(boolean ced_doc) {
        this.ced_doc = ced_doc;
    }

    public String getObs_doc() {
        return obs_doc;
    }

    public void setObs_doc(String obs_doc) {
        this.obs_doc = obs_doc;
    }

    public int getNiv_esc() {
        return niv_esc;
    }

    public void setNiv_esc(int niv_esc) {
        this.niv_esc = niv_esc;
    }

    public String getNom_uni_esc() {
        return nom_uni_esc;
    }

    public void setNom_uni_esc(String nom_uni_esc) {
        this.nom_uni_esc = nom_uni_esc;
    }

    public String getCiu_uni_esc() {
        return ciu_uni_esc;
    }

    public void setCiu_uni_esc(String ciu_uni_esc) {
        this.ciu_uni_esc = ciu_uni_esc;
    }

    public String getEst_uni_esc() {
        return est_uni_esc;
    }

    public void setEst_uni_esc(String est_uni_esc) {
        this.est_uni_esc = est_uni_esc;
    }

    public String getNom_est_esc() {
        return nom_est_esc;
    }

    public void setNom_est_esc(String nom_est_esc) {
        this.nom_est_esc = nom_est_esc;
    }

    public boolean isFin_esc() {
        return fin_esc;
    }

    public void setFin_esc(boolean fin_esc) {
        this.fin_esc = fin_esc;
    }

    public boolean isTit_esc() {
        return tit_esc;
    }

    public void setTit_esc(boolean tit_esc) {
        this.tit_esc = tit_esc;
    }

    public String getObs_esc() {
        return obs_esc;
    }

    public void setObs_esc(String obs_esc) {
        this.obs_esc = obs_esc;
    }
    
    public boolean AltaAlumnos(String mat_alu, String nom_alu, String ape_pat_alu, String ape_mat_alu, java.sql.Date fec_nac_alu, String curp_alu, int edad_alu, boolean sexo_alu, 
    String tel_alu, String cel_alu, String email_alu, String est_civ_alu, FileInputStream foto_al, int tip_pag_alu, int pag_alu_alu,
    int pag_pro_alu, String sta_alu, String nom_pro, String calle_dom, String no_dom, String col_dom, int cp_dom, String mun_dom, String est_dom, boolean sol_doc, boolean act_nac_doc,
    boolean cred_ele_doc, boolean curp_doc, boolean cart_exp_mot_doc, boolean foto_doc, boolean tit_doc, boolean ced_doc, String obs_doc, int niv_esc, String nom_uni_esc, String ciu_uni_esc,
    String est_uni_esc, String nom_est_esc, boolean fin_esc, boolean tit_esc, String obs_esc){
        
       Conexion cc = new Conexion();
       Connection cn = cc.getConexion();
       
       try {
            CallableStatement proc = cn.prepareCall("CALL AltaAlumno(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            /*=== Alumnos ===*/
            proc.setString(1, getMat_alu());
            proc.setString(2, getNom_alu());
            proc.setString(3, getApe_pat_alu());
            proc.setString(4, getApe_mat_alu());
            proc.setDate(5, getFec_nac_alu());
            proc.setString(6, getCurp_alu());
            proc.setInt(7, getEdad_alu());
            proc.setBoolean(8, isSexo_alu());
            proc.setString(9, getTel_alu());
            proc.setString(10, getCel_alu());
            proc.setString(11, getEmail_alu());
            proc.setString(12, getEst_civ_alu());
            proc.setBinaryStream(13, getFoto_al());
            proc.setInt(14, getTip_pag_alu());
            proc.setInt(15, getPag_alu_alu());
            proc.setInt(16, getPag_pro_alu());
            proc.setInt(17, getSta_alu());
            proc.setString(18, getNom_pro());
            
            /*=== Domicilio ===*/
            proc.setString(19, getCalle_dom());
            proc.setString(20, getNo_dom());
            proc.setString(21, getCol_dom());
            proc.setInt(22, getCp_dom());
            proc.setString(23, getMun_dom());
            proc.setString(24, getEst_dom());
            
            /*=== Documentación ===*/
            proc.setBoolean(25, isSol_doc());
            proc.setBoolean(26, isAct_nac_doc());
            proc.setBoolean(27, isCred_ele_doc());
            proc.setBoolean(28, isCurp_doc());
            proc.setBoolean(29, isCart_exp_mot_doc());
            proc.setBoolean(30, isFoto_doc());
            proc.setBoolean(31, isTit_doc());
            proc.setBoolean(32, isCed_doc());
            proc.setString(33, getObs_doc());
            
            /*=== Escolaridad ===*/
            proc.setInt(34, getNiv_esc());
            proc.setString(35, getNom_uni_esc());
            proc.setString(36, getCiu_uni_esc());
            proc.setString(37, getEst_uni_esc());
            proc.setString(38, getNom_est_esc());
            proc.setBoolean(39, isFin_esc());
            proc.setBoolean(40, isTit_esc());
            proc.setString(41, getObs_esc());
            
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
    
    public ArrayList<AlumnosM> listAlumnos(){
    Conexion cc = new Conexion();
    Connection cn = cc.getConexion();
        
    ArrayList listaAlumnos = new ArrayList();
    AlumnosM persona;
        try{
            PreparedStatement ps = cn.prepareStatement("SELECT a.id_alu, a.mat_alu, a.nom_alu, a.ape_pat_alu, a.ape_mat_alu, " +
"a.fec_nac_alu, a.curp_alu, a.edad_alu, a.sexo_alu, a.tel_alu, a.cel_alu, a.email_alu, a.est_civ_alu, " +
"a.tip_pag_alu, a.pag_alu_alu, a.pag_pro_alu, p.ins_pro, p.sig_pro, dom.calle_dom, dom.no_dom, dom.col_dom, dom.cp_dom, dom.mun_dom, dom.est_dom, " +
"doc.sol_doc, doc.act_nac_doc, doc.cred_ele_doc, doc.curp_doc, doc.cart_exp_mot_doc, doc.foto_doc, doc.tit_doc, doc.ced_doc, doc.obs_doc, " +
"es.niv_esc, es.nom_uni_esc, es.ciu_uni_esc, es.est_uni_esc, es.nom_est_esc, es.fin_esc, es.tit_esc, es.obs_esc " +
"FROM alumnos a, procedencia p, domicilio dom, documentacion doc, escolaridad es " +
"WHERE a.id_pro=p.id_pro AND a.id_alu=dom.id_alu AND a.id_alu=doc.id_alu AND a.id_alu=es.id_alu AND niv_esc=0 ORDER BY ES.CIU_UNI_ESC DESC LIMIT 0,1;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                persona = new AlumnosM();
                
                persona.setId_alu(rs.getInt(1));
                persona.setMat_alu(rs.getString(2));
                persona.setNom_alu(rs.getString(3));
                persona.setApe_pat_alu(rs.getString(4));
                persona.setApe_mat_alu(rs.getString(5));
                persona.setFec_nac_alu(rs.getDate(6));
                persona.setCurp_alu(rs.getString(7));
                persona.setEdad_alu(rs.getInt(8));
                persona.setSexo_alu(rs.getBoolean(9));
                persona.setTel_alu(rs.getString(10));
                persona.setCel_alu(rs.getString(11));
                persona.setEmail_alu(rs.getString(12));
                persona.setEst_civ_alu(rs.getString(13));
                persona.setTip_pag_alu(rs.getInt(14));
                persona.setPag_alu_alu(rs.getInt(15));
                persona.setPag_pro_alu(rs.getInt(16));
                persona.setIns_pro(rs.getString(17));
                persona.setSig_pro(rs.getString(18));
                ///////////////////////Domiclio////////////////////////////////
                persona.setCalle_dom(rs.getString(19));
                persona.setNo_dom(rs.getString(20));
                persona.setCol_dom(rs.getString(21));
                persona.setCp_dom(rs.getInt(22));
                persona.setMun_dom(rs.getString(23));
                persona.setEst_dom(rs.getString(24));
                ///////////////////////Documentación////////////////////////////
                persona.setSol_doc(rs.getBoolean(25));
                persona.setAct_nac_doc(rs.getBoolean(26));
                persona.setCred_ele_doc(rs.getBoolean(27));
                persona.setCurp_doc(rs.getBoolean(28));
                persona.setCart_exp_mot_doc(rs.getBoolean(29));
                persona.setFoto_doc(rs.getBoolean(30));
                persona.setTit_doc(rs.getBoolean(31));
                persona.setCed_doc(rs.getBoolean(32));
                persona.setObs_doc(rs.getString(33));
                ///////////////////////Escolaridad//////////////////////////////
                persona.setNiv_esc(rs.getInt(34));
                persona.setNom_uni_esc(rs.getString(35));
                persona.setCiu_uni_esc(rs.getString(36));
                persona.setEst_uni_esc(rs.getString(37));
                persona.setNom_est_esc(rs.getString(38));
                persona.setFin_esc(rs.getBoolean(39));
                persona.setTit_esc(rs.getBoolean(40));
                persona.setObs_esc(rs.getString(41));
                listaAlumnos.add(persona);
            }           
        }catch(Exception e){
        }
    return listaAlumnos;
    }
    
    public ArrayList<ProcedenciaM> BuscPersona(){
    Conexion cc = new Conexion();
    Connection cn = cc.getConexion();
        
    ArrayList listaPersona = new ArrayList();
    ProcedenciaM persona;
        try{
            String var = null;
            //var = getBusqueda();
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
    
    public boolean AltaAlumnosEscolaridad(int niv_esc, String nom_uni_esc, String nom_est_esc, boolean fin_esc, boolean tit_esc, String curp_alu){
        
       Conexion cc = new Conexion();
       Connection cn = cc.getConexion();
       
       try {
            CallableStatement proc = cn.prepareCall("CALL AltaAlumnosEscolaridad(?,?,?,?,?,?)");
                        
            /*=== Escolaridad ===*/
            proc.setInt(1, getNiv_esc());
            proc.setString(2, getNom_uni_esc());
            proc.setString(3, getNom_est_esc());
            proc.setBoolean(4, isFin_esc());
            proc.setBoolean(5, isTit_esc());
            proc.setString(6, getCurp_alu());
            
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
    
}

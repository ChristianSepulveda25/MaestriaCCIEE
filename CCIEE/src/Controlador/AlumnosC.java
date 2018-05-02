/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.AlumnosM;
import Modelo.Conexion;
import Modelo.ProcedenciaM;
import Vista.AlumnosV;
import Vista.CargarFoto;
import Vista.ProcedenciaV;
import com.mysql.jdbc.Connection;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Region;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.AncestorListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author INFORMATICA-05
 */
public class AlumnosC implements ActionListener, KeyListener {
    
    private final AlumnosM am;
    private final AlumnosV av;
    File fichero;
    public int fila;
    
    public AlumnosC(AlumnosM am, AlumnosV av){
        
        this.am = am;//Hola
        this.av = av;
        this.av.btnnuevo.addActionListener(this);
        this.av.btnguardar.addActionListener(this);
        this.av.btneditar.addActionListener(this);
        this.av.btneliminar.addActionListener(this);
        this.av.btncancelar.addActionListener(this);
        this.av.btnsalir.addActionListener(this);
        this.av.txtbuscaralu.addKeyListener((KeyListener) this);
        this.av.btnagregar.addActionListener(this);
        this.av.btned.addActionListener(this);
        this.av.btnel.addActionListener(this);
        this.av.btnfoto.addActionListener(this);
    }  
    
    public void iniciar(){
        av.setTitle("Alta Procedencias");
        av.setSize(1024, 555);
        llenartabla(av.tblalumnos);
        bloquear();
        av.btnguardar.setEnabled(false);
        av.btncancelar.setEnabled(false);
        av.btnfoto.setEnabled(false);
        av.rbpormix.setSelected(true);
        av.rblicno.setSelected(true);
        av.rbposno.setSelected(true);
        av.rbtitno.setSelected(true);
    }
    
    public void bloquear(){
        /*=== Alumno ===*/
        av.txtmatricula.setEnabled(false);
        av.txtnombre.setEnabled(false);
        av.txtappat.setEnabled(false);
        av.txtapmat.setEnabled(false);
        av.txtestciv.setEnabled(false);
        av.txtcurp.setEnabled(false);
        av.txtfecnac.setEnabled(false);
        av.txtedad.setEnabled(false);
        av.cmbsexo.setEnabled(false);
        av.txtfijo.setEnabled(false);
        av.txtcel.setEnabled(false);
        av.txtemail.setEnabled(false);
        av.cmbpro.setEnabled(false);
        av.rbpormix.setEnabled(false);
        av.rbporpro.setEnabled(false);
        av.rbporper.setEnabled(false);
        av.txtporpro.setEnabled(false);
        av.txtporper.setEnabled(false);
        
        /*=== Domicilio ===*/
        av.txtcalle.setEnabled(false);
        av.txtnum.setEnabled(false);
        av.txtcol.setEnabled(false);
        av.txtcp.setEnabled(false);
        av.txtmun.setEnabled(false);
        av.txtest.setEnabled(false);
        
        /*=== Documentación ===*/
        av.jCheckBox1.setEnabled(false);
        av.jCheckBox2.setEnabled(false);
        av.jCheckBox3.setEnabled(false);
        av.jCheckBox4.setEnabled(false);
        av.jCheckBox5.setEnabled(false);
        av.jCheckBox6.setEnabled(false);
        av.jCheckBox7.setEnabled(false);
        av.jCheckBox8.setEnabled(false);
        av.txtobs.setEnabled(false);
        
        /*=== Licenciatura ===*/
        av.txtnomuni.setEnabled(false);
        av.txtuniciu.setEnabled(false);
        av.txtuniest.setEnabled(false);
        av.txtlic.setEnabled(false);
        av.rblicsi.setEnabled(false);
        av.rblicno.setEnabled(false);
        av.txtotro.setEnabled(false);
        
        /*=== Posgrados ===*/
        av.cmbpos.setEnabled(false);
        av.txtpos.setEnabled(false);
        av.txtposuni.setEnabled(false);
        av.rbpossi.setEnabled(false);
        av.rbposno.setEnabled(false);
        av.rbtitsi.setEnabled(false);
        av.rbtitno.setEnabled(false);
        av.btnagregar.setEnabled(false);
        av.tblposgrado.setEnabled(false);
        av.btned.setEnabled(false);
        av.btnel.setEnabled(false);
        
    }
    
    public void desbloquear(){
        /*=== Alumno ===*/
        av.txtmatricula.setEnabled(true);
        av.txtnombre.setEnabled(true);
        av.txtappat.setEnabled(true);
        av.txtapmat.setEnabled(true);
        av.txtestciv.setEnabled(true);
        av.txtcurp.setEnabled(true);
        av.txtfecnac.setEnabled(true);
        av.txtedad.setEnabled(true);
        av.cmbsexo.setEnabled(true);
        av.txtfijo.setEnabled(true);
        av.txtcel.setEnabled(true);
        av.txtemail.setEnabled(true);
        av.cmbpro.setEnabled(true);
        av.rbpormix.setEnabled(true);
        av.rbporpro.setEnabled(true);
        av.rbporper.setEnabled(true);
        av.txtporpro.setEnabled(true);
        av.txtporper.setEnabled(true);
        
        /*=== Domicilio ===*/
        av.txtcalle.setEnabled(true);
        av.txtnum.setEnabled(true);
        av.txtcol.setEnabled(true);
        av.txtcp.setEnabled(true);
        av.txtmun.setEnabled(true);
        av.txtest.setEnabled(true);
        
        /*=== Documentación ===*/
        av.jCheckBox1.setEnabled(true);
        av.jCheckBox2.setEnabled(true);
        av.jCheckBox3.setEnabled(true);
        av.jCheckBox4.setEnabled(true);
        av.jCheckBox5.setEnabled(true);
        av.jCheckBox6.setEnabled(true);
        av.jCheckBox7.setEnabled(true);
        av.jCheckBox8.setEnabled(true);
        av.txtobs.setEnabled(true);
        
        /*=== Licenciatura ===*/
        av.txtnomuni.setEnabled(true);
        av.txtuniciu.setEnabled(true);
        av.txtuniest.setEnabled(true);
        av.txtlic.setEnabled(true);
        av.rblicsi.setEnabled(true);
        av.rblicno.setEnabled(true);
        av.txtotro.setEnabled(true);
        
        /*=== Posgrados ===*/
        av.cmbpos.setEnabled(true);
        av.txtpos.setEnabled(true);
        av.txtposuni.setEnabled(true);
        av.rbpossi.setEnabled(true);
        av.rbposno.setEnabled(true);
        av.rbtitsi.setEnabled(true);
        av.rbtitno.setEnabled(true);
        av.btnagregar.setEnabled(true);
        av.tblposgrado.setEnabled(true);
        av.btned.setEnabled(true);
        av.btnel.setEnabled(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == av.btnnuevo){
            desbloquear();
            av.btnguardar.setEnabled(true);
            av.btnfoto.setEnabled(true);
        }else if(e.getSource() == av.btnguardar){
            String a = av.txtid.getText();
            if(a.equals("")){
                try {
                    altaalumnos();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AlumnosC.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                actualizaralumnos();
            }
        }else if(e.getSource() == av.btneditar){
            
            fila = av.tblalumnos.getSelectedRow();
            if(fila < 0){
                JOptionPane.showMessageDialog(av, "Seleccione una fila");
            }else{
                av.btnguardar.setText("Actualizar");
                ImageIcon img = new ImageIcon("src/img/actualizar.png");
                av.btnguardar.setIcon(img);
                
                try {
                    BuscarFotoAlumno();
                } catch (IOException ex) {
                    Logger.getLogger(AlumnosV.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                av.txtid.setText(av.tblalumnos.getValueAt(fila, 2).toString());
                av.txtmatricula.setText(av.tblalumnos.getValueAt(fila, 3).toString());
                av.txtnombre.setText(av.tblalumnos.getValueAt(fila, 4).toString());
                av.txtappat.setText(av.tblalumnos.getValueAt(fila, 5).toString());
                av.txtapmat.setText(av.tblalumnos.getValueAt(fila, 6).toString());
                av.txtfecnac.setText(av.tblalumnos.getValueAt(fila, 7).toString());
                av.txtcurp.setText(av.tblalumnos.getValueAt(fila, 8).toString());
                av.txtedad.setText(av.tblalumnos.getValueAt(fila, 9).toString());
                String s = av.tblalumnos.getValueAt(fila, 10).toString();
                if(s.equals("false")){
                    av.cmbsexo.setSelectedIndex(0);
                }else{
                    av.cmbsexo.setSelectedIndex(1);
                }
                av.txtfijo.setText(av.tblalumnos.getValueAt(fila, 11).toString());
                av.txtcel.setText(av.tblalumnos.getValueAt(fila, 12).toString());
                av.txtemail.setText(av.tblalumnos.getValueAt(fila, 13).toString());
                av.txtestciv.setText(av.tblalumnos.getValueAt(fila, 14).toString());
                int tp;
                tp = Integer.parseInt(av.tblalumnos.getValueAt(fila, 15).toString());
                if(tp == 0){
                    av.rbpormix.setSelected(false);
                    av.txtporper.setVisible(false);
                    av.txtporpro.setVisible(false);
                    av.jLabel16.setVisible(false);
                    av.jLabel17.setVisible(false);
                    av.jLabel50.setVisible(false);
                    av.jLabel51.setVisible(false);
                    av.rbporpro.setSelected(true);
                    av.txtporper.setText("");
                    av.txtporper.setText("");
                }else if(tp == 1){
                    av.rbpormix.setSelected(false);
                    av.txtporper.setVisible(false);
                    av.txtporpro.setVisible(false);
                    av.jLabel16.setVisible(false);
                    av.jLabel17.setVisible(false);
                    av.jLabel50.setVisible(false);
                    av.jLabel51.setVisible(false);
                    av.rbporper.setSelected(true);
                    av.txtporper.setText("");
                    av.txtporper.setText("");
                }else{
                    av.rbpormix.setSelected(true);
                    av.txtporper.setVisible(true);
                    av.txtporpro.setVisible(true);
                    av.jLabel16.setVisible(true);
                    av.jLabel17.setVisible(true);
                    av.jLabel50.setVisible(true);
                    av.jLabel51.setVisible(true);
                    av.txtporper.setText(av.tblalumnos.getValueAt(fila, 17).toString());
                    av.txtporper.setText(av.tblalumnos.getValueAt(fila, 16).toString());
                }
                av.cmbpro.setSelectedItem(av.tblalumnos.getValueAt(fila, 18).toString());
                ///////////////////////Domicilio///////////////////////////////
                av.txtcalle.setText(av.tblalumnos.getValueAt(fila, 19).toString());
                av.txtnum.setText(av.tblalumnos.getValueAt(fila, 20).toString());
                av.txtcol.setText(av.tblalumnos.getValueAt(fila, 21).toString());
                av.txtcp.setText(av.tblalumnos.getValueAt(fila, 22).toString());
                av.txtmun.setText(av.tblalumnos.getValueAt(fila, 23).toString());
                av.txtest.setText(av.tblalumnos.getValueAt(fila, 24).toString());
                //////////////////////Documentación/////////////////////////////
                boolean var;
                var = Boolean.parseBoolean(av.tblalumnos.getValueAt(fila, 25).toString());
                av.jCheckBox1.setSelected(var);
                var = Boolean.parseBoolean(av.tblalumnos.getValueAt(fila, 26).toString());
                av.jCheckBox2.setSelected(var);
                var = Boolean.parseBoolean(av.tblalumnos.getValueAt(fila, 27).toString());
                av.jCheckBox3.setSelected(var);
                var = Boolean.parseBoolean(av.tblalumnos.getValueAt(fila, 28).toString());
                av.jCheckBox4.setSelected(var);
                var = Boolean.parseBoolean(av.tblalumnos.getValueAt(fila, 29).toString());
                av.jCheckBox5.setSelected(var);
                var = Boolean.parseBoolean(av.tblalumnos.getValueAt(fila, 30).toString());
                av.jCheckBox6.setSelected(var);
                var = Boolean.parseBoolean(av.tblalumnos.getValueAt(fila, 31).toString());
                av.jCheckBox7.setSelected(var);
                var = Boolean.parseBoolean(av.tblalumnos.getValueAt(fila, 32).toString());
                av.jCheckBox8.setSelected(var);
                av.txtobs.setText(av.tblalumnos.getValueAt(fila, 33).toString());
                
                //////////////////////Estudios//////////////////////////////////
                av.txtnomuni.setText(av.tblalumnos.getValueAt(fila, 35).toString());
                av.txtuniciu.setText(av.tblalumnos.getValueAt(fila, 36).toString());
                av.txtuniest.setText(av.tblalumnos.getValueAt(fila, 37).toString());
                av.txtlic.setText(av.tblalumnos.getValueAt(fila, 38).toString());
                //av.txtnomuni.setText(av.tblalumnos.getValueAt(fila, 39).toString());
                String tit = null;
                tit = av.tblalumnos.getValueAt(fila, 40).toString();
                if(tit.equals("true")){
                    av.rblicsi.setSelected(true);
                    //av.rblicno.setSelected(false);
                }else{
                    //av.rblicsi.setSelected(false);
                    av.rblicno.setSelected(true);
                }
                av.txtotro.setText(av.tblalumnos.getValueAt(fila, 41).toString());
                           
                
            }
            
        }else if(e.getSource() == av.btneliminar){
            
        }else if(e.getSource() == av.btncancelar){
            
        }else if(e.getSource() == av.btnsalir){
            //JOptionPane.showMessageDialog(av, av.cmbpro.getSelectedItem().toString());
        }else if(e.getSource() == av.btnagregar){
            if(av.cmbpos.getSelectedIndex() < 1){
            JOptionPane.showMessageDialog(av, "Selecciones un Posgrado");
        }else{
            if(av.txtpos.getText().isEmpty() || av.txtposuni.getText().isEmpty()){
                JOptionPane.showMessageDialog(av, "Complete todos los campos");
            }else{
                String a1, a2, a3, a4, a5;
                a1 = av.cmbpos.getSelectedItem().toString();
                a2 = av.txtpos.getText();
                a3 = av.txtposuni.getText();
                //////////// Conlcuyó
                av.rbpossi.setActionCommand("Si");
                av.rbposno.setActionCommand("No");
                a4 = av.conluyomae.getSelection().getActionCommand();
                //////////// Titulado
                av.rbtitsi.setActionCommand("Si");
                av.rbtitno.setActionCommand("No");
                a5 = av.tituladomae.getSelection().getActionCommand();

                av.modelt.addRow(new Object[]{"",a1,a2,a3,a4,a5});
                
                av.cmbpos.setSelectedIndex(0);
                av.txtpos.setText("");
                av.txtposuni.setText("");
                av.rbposno.setSelected(true);
                av.rbtitno.setSelected(true);
                av.cmbpos.requestFocus();
                
            }
            
        }
        }else if(e.getSource() == av.btned){
            altaalumnosescolaridad();
        }else if(e.getSource() == av.btnel){
            
        }else if(e.getSource() == av.btnfoto){
            int resultado;
            CargarFoto ventana = new CargarFoto();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG y PNG","jpg","png");
            ventana.jfchCargarfoto.setFileFilter(filtro);
            resultado= ventana.jfchCargarfoto.showOpenDialog(null);

            if (JFileChooser.APPROVE_OPTION == resultado){
                fichero = ventana.jfchCargarfoto.getSelectedFile();
                try{
                    ImageIcon icon = new ImageIcon(fichero.toString());
                    Icon icono = new ImageIcon(icon.getImage().getScaledInstance(av.lblfoto.getWidth(), av.lblfoto.getHeight(), Image.SCALE_DEFAULT));
                    av.lblfoto.setText(null);
                    av.lblfoto.setIcon( icono );
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Error abriendo la imagen "+ ex);         
                }
            }
        }
    }
    
    public void altaalumnos() throws FileNotFoundException{
        String mat_alu, nom_alu, ape_pat_alu, ape_mat_alu, curp_alu, tel_alu, cel_alu, email_alu, est_civ_alu, nom_pro, calle_dom,
        no_dom, col_dom, mun_dom, est_dom, obs_doc, nom_uni_esc, ciu_uni_esc, est_uni_esc, nom_est_esc, obs_esc;
        
        FileInputStream foto_al;
        
        if(fichero == null){
            foto_al = null;
        }else{
            foto_al = new FileInputStream(fichero);
        }
    
        
        
        int edad_alu, tip_pag_alu, sta_alu, cp_dom, niv_esc = 0, pag_alu_alu, pag_pro_alu;
        
        boolean sexo_alu, sol_doc, act_nac_doc, cred_ele_doc, curp_doc, cart_exp_mot_doc, foto_doc, tit_doc, ced_doc, fin_esc = true, tit_esc = false;
        
        String tipo = null;
        
        int a;
        
        mat_alu = av.txtmatricula.getText();
        nom_alu = av.txtnombre.getText();
        ape_pat_alu = av.txtappat.getText();
        ape_mat_alu = av.txtapmat.getText();
        est_civ_alu = av.txtestciv.getText();
        
        curp_alu = av.txtcurp.getText();
        
        SimpleDateFormat formato;
        formato = new SimpleDateFormat ("yyyy-MM-dd");
        java.sql.Date fec_nac_alu = null;
        try {
            fec_nac_alu = new java.sql.Date((formato.parse("19"+curp_alu.substring(4,6)+"-"+curp_alu.substring(6,8)+"-"+curp_alu.substring(8,10))).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(AlumnosC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int sx;
        sx = av.cmbsexo.getSelectedIndex();
        if(sx == 0){
            sexo_alu = false;
        }else{
            sexo_alu = true;
        }
        
        edad_alu = Integer.parseInt(av.txtedad.getText());
        
        tel_alu = av.txtfijo.getText();
        cel_alu = av.txtcel.getText();
        email_alu = av.txtemail.getText();
        nom_pro = av.cmbpro.getSelectedItem().toString();
        av.rbporpro.setActionCommand("Procedencia");
        av.rbporper.setActionCommand("Personal");
        av.rbpormix.setActionCommand("Mixto");
        tipo= av.Porcentaje.getSelection().getActionCommand();
        if(tipo == "Procedencia"){
            tip_pag_alu = 0;
            pag_pro_alu = 100;
            pag_alu_alu = 0;
        }else if(tipo == "Personal"){
            tip_pag_alu = 1;
            pag_pro_alu = 0;
            pag_alu_alu = 100;
        }else{
            tip_pag_alu = 2;
            pag_pro_alu = Integer.parseInt(av.txtporpro.getText());
            pag_alu_alu = Integer.parseInt(av.txtporper.getText());
        }
        
        sta_alu = 0;
        
        /*=== Domicilio ===*/
        calle_dom = av.txtcalle.getText();
        no_dom = av.txtnum.getText();
        col_dom = av.txtcol.getText();
        cp_dom = Integer.parseInt(av.txtcp.getText());
        mun_dom = av.txtmun.getText();
        est_dom = av.txtest.getText();
        
        /*=== Documentación ===*/
        if (av.jCheckBox1.isSelected()){
            sol_doc = true;
        }else{
            sol_doc = false;
        }
        if (av.jCheckBox2.isSelected()){
            act_nac_doc = true;
        }else{
            act_nac_doc = false;
        }
        if (av.jCheckBox3.isSelected()){
            cred_ele_doc = true;
        }else{
            cred_ele_doc = false;
        }
        if (av.jCheckBox4.isSelected()){
            curp_doc = true;
        }else{
            curp_doc = false;
        }
        if (av.jCheckBox5.isSelected()){
            cart_exp_mot_doc = true;
        }else{
            cart_exp_mot_doc = false;
        }
        if (av.jCheckBox6.isSelected()){
            foto_doc = true;
        }else{
            foto_doc = false;
        }
        if (av.jCheckBox7.isSelected()){
            tit_doc = true;
        }else{
            tit_doc = false;
        }
        if (av.jCheckBox8.isSelected()){
            ced_doc = true;
        }else{
            ced_doc = false;
        }
        obs_doc = av.txtobs.getText();
        
        /*=== Licenciatura ===*/
        nom_uni_esc = av.txtnomuni.getText();
        ciu_uni_esc = av.txtuniciu.getText();
        est_uni_esc = av.txtuniest.getText();
        nom_est_esc = av.txtlic.getText();
        av.rblicsi.setActionCommand("Si");
        av.rblicno.setActionCommand("No");
        tipo = null;
        tipo= av.titlic.getSelection().getActionCommand();
        if(tipo == "Si"){
            tit_esc = true;
        }else{
            tit_esc = false;
        }
        obs_esc = av.txtotro.getText();
        
        /*=== ALUMNO ===*/
        am.setMat_alu(mat_alu);
        am.setNom_alu(nom_alu);
        am.setApe_pat_alu(ape_pat_alu);
        am.setApe_mat_alu(ape_mat_alu);
        am.setFec_nac_alu(fec_nac_alu);
        am.setCurp_alu(curp_alu);
        am.setEdad_alu(edad_alu);
        am.setSexo_alu(sexo_alu);
        am.setTel_alu(tel_alu);
        am.setCel_alu(cel_alu);
        am.setEmail_alu(email_alu);
        am.setEst_civ_alu(est_civ_alu);
        
        am.setFoto_al(foto_al);
        am.setTip_pag_alu(tip_pag_alu);
        am.setPag_alu_alu(pag_alu_alu);
        am.setPag_pro_alu(pag_pro_alu);
        am.setSta_alu(sta_alu);
        am.setNom_pro(nom_pro);

        /*=== Domicilio ===*/
        am.setCalle_dom(calle_dom);
        am.setNo_dom(no_dom);
        am.setCol_dom(col_dom);
        am.setCp_dom(cp_dom);
        am.setMun_dom(mun_dom);
        am.setEst_dom(est_dom);

        /*=== Documentación ===*/
        am.setSol_doc(sol_doc);
        am.setAct_nac_doc(act_nac_doc);
        am.setCred_ele_doc(cred_ele_doc);
        am.setCurp_doc(curp_doc);
        am.setCart_exp_mot_doc(cart_exp_mot_doc);
        am.setFoto_doc(foto_doc);
        am.setTit_doc(tit_doc);
        am.setCed_doc(ced_doc);
        am.setObs_doc(obs_doc);

        /*=== Escolaridad ===*/
        am.setNiv_esc(niv_esc);
        am.setNom_uni_esc(nom_uni_esc);
        am.setCiu_uni_esc(ciu_uni_esc);
        am.setEst_uni_esc(est_uni_esc);
        am.setNom_est_esc(nom_est_esc);
        am.setFin_esc(fin_esc);
        am.setTit_esc(tit_esc);
        am.setObs_esc(obs_esc);

        if(am.AltaAlumnos(mat_alu, nom_alu, ape_pat_alu, ape_mat_alu, fec_nac_alu, curp_alu, edad_alu, sexo_alu, tel_alu, cel_alu, email_alu, est_civ_alu, foto_al, tip_pag_alu, 
                pag_alu_alu, pag_pro_alu, cel_alu, nom_pro, calle_dom, no_dom, col_dom, cp_dom, mun_dom, est_dom, sol_doc, act_nac_doc, cred_ele_doc, curp_doc, cart_exp_mot_doc, 
                foto_doc, tit_doc, ced_doc, obs_doc, niv_esc, nom_uni_esc, ciu_uni_esc, est_uni_esc, nom_est_esc, fin_esc, tit_esc, obs_esc)){
            altaalumnosescolaridad();
            //JOptionPane.showMessageDialog(null, "Registro Guardado");
            //llenartabla(av.tblalumnos);
            //limpiar();
            //bloquear();
            //av.btnguardar.setEnabled(false);
            //av.btncancelar.setEnabled(false);
            //av.btnnuevo.setEnabled(true);
            //av.btneditar.setEnabled(true);
            //av.btnsalir.setEnabled(true);
            //av.btneliminar.setEnabled(true);
        }else{
            JOptionPane.showMessageDialog(null, "Error al Guardar");
            //limpiar();
        }
    }
    
    public void llenartabla(JTable tblalumnos){
        DefaultTableModel modelt = new DefaultTableModel();
        tblalumnos.setModel(modelt);
        
        modelt.addColumn("NOMBRE");
        modelt.addColumn("SIGLAS");
        modelt.addColumn("ID");
        modelt.addColumn("MATRICULA");
        modelt.addColumn("NOMBRE");
        modelt.addColumn("AP PATERNO");
        modelt.addColumn("AP MATERNO");
        modelt.addColumn("FECHA NAC");
        modelt.addColumn("CURP");
        modelt.addColumn("EDAD");
        modelt.addColumn("SEXO");
        modelt.addColumn("TELEFONO");
        modelt.addColumn("CELULAR");
        modelt.addColumn("EMAIL");
        modelt.addColumn("EST. CIVIL");
        modelt.addColumn("TIPO PAGO");
        modelt.addColumn("ALUMNO");
        modelt.addColumn("PROCEDENCIA");
        modelt.addColumn("INSTITUCION");
        modelt.addColumn("CALLE");
        modelt.addColumn("NÚMERO");
        modelt.addColumn("COLONIA");
        modelt.addColumn("C.P.");
        modelt.addColumn("MUNICIPIO");
        modelt.addColumn("ESTADO");
        modelt.addColumn("SOLICITUD");
        modelt.addColumn("ACTA DE NAC");
        modelt.addColumn("CEREDENCIAL");
        modelt.addColumn("CURP");
        modelt.addColumn("CARTA");
        modelt.addColumn("FOTO");
        modelt.addColumn("TITULO");
        modelt.addColumn("CEDULA");
        modelt.addColumn("OBSERVACIONES");
        modelt.addColumn("NIVEL");
        modelt.addColumn("NOM INSTITUCION");
        modelt.addColumn("CIUDAD INST");
        modelt.addColumn("ESTADO INST");
        modelt.addColumn("ESTUDIOS");
        modelt.addColumn("FINALIZADOS");
        modelt.addColumn("TITULADO");
        modelt.addColumn("OBSERVACIONES");
        
        Object[] columna = new Object[42];
        int numreg = am.listAlumnos().size();
        
        for (int i =0; i < numreg ; i++){
            String nombre = null;
            nombre = am.listAlumnos().get(i).getNom_alu() + " " + 
                    am.listAlumnos().get(i).getApe_pat_alu() + " " + 
                    am.listAlumnos().get(i).getApe_mat_alu();
            columna[0]= nombre;
            columna[1]= am.listAlumnos().get(i).getSig_pro();
            columna[2]= am.listAlumnos().get(i).getId_alu();
            columna[3]= am.listAlumnos().get(i).getMat_alu();
            columna[4]= am.listAlumnos().get(i).getNom_alu();
            columna[5]= am.listAlumnos().get(i).getApe_pat_alu();
            columna[6]= am.listAlumnos().get(i).getApe_mat_alu();
            columna[7]= am.listAlumnos().get(i).getFec_nac_alu();
            columna[8]= am.listAlumnos().get(i).getCurp_alu();
            columna[9]= am.listAlumnos().get(i).getEdad_alu();
            columna[10]= am.listAlumnos().get(i).isSexo_alu();
            columna[11]= am.listAlumnos().get(i).getTel_alu();
            columna[12]= am.listAlumnos().get(i).getCel_alu();
            columna[13]= am.listAlumnos().get(i).getEmail_alu();
            columna[14]= am.listAlumnos().get(i).getEst_civ_alu();
            columna[15]= am.listAlumnos().get(i).getTip_pag_alu();
            columna[16]= am.listAlumnos().get(i).getPag_alu_alu();
            columna[17]= am.listAlumnos().get(i).getPag_pro_alu();
            columna[18]= am.listAlumnos().get(i).getIns_pro();
            //////////////////////////Domicilio/////////////////////////////////
            columna[19]= am.listAlumnos().get(i).getCalle_dom();
            columna[20]= am.listAlumnos().get(i).getNo_dom();
            columna[21]= am.listAlumnos().get(i).getCol_dom();
            columna[22]= am.listAlumnos().get(i).getCp_dom();
            columna[23]= am.listAlumnos().get(i).getMun_dom();
            columna[24]= am.listAlumnos().get(i).getEst_dom();
            //////////////////////////Documentación/////////////////////////////
            columna[25]= am.listAlumnos().get(i).isSol_doc();
            columna[26]= am.listAlumnos().get(i).isAct_nac_doc();
            columna[27]= am.listAlumnos().get(i).isCred_ele_doc();
            columna[28]= am.listAlumnos().get(i).isCurp_doc();
            columna[29]= am.listAlumnos().get(i).isCart_exp_mot_doc();
            columna[30]= am.listAlumnos().get(i).isFoto_doc();
            columna[31]= am.listAlumnos().get(i).isTit_doc();
            columna[32]= am.listAlumnos().get(i).isCed_doc();
            columna[33]= am.listAlumnos().get(i).getObs_doc();
            //////////////////////////Escolaridad///////////////////////////////
            columna[34]= am.listAlumnos().get(i).getNiv_esc();
            columna[35]= am.listAlumnos().get(i).getNom_uni_esc();
            columna[36]= am.listAlumnos().get(i).getCiu_uni_esc();
            columna[37]= am.listAlumnos().get(i).getEst_uni_esc();
            columna[38]= am.listAlumnos().get(i).getNom_est_esc();
            columna[39]= am.listAlumnos().get(i).isFin_esc();
            columna[40]= am.listAlumnos().get(i).isTit_esc();
            columna[41]= am.listAlumnos().get(i).getObs_esc();
            
            modelt.addRow(columna);
        }
        
        //av.tblalumnos.getColumnModel().getColumn(11).setMaxWidth(50);
        //av.tblalumnos.getColumnModel().getColumn(11).setMinWidth(50);
        //av.tblalumnos.getColumnModel().getColumn(11).setPreferredWidth(50);
        
        /*av.tblalumnos.getColumnModel().getColumn(0).setMaxWidth(300);
        av.tblalumnos.getColumnModel().getColumn(0).setMinWidth(300);
        av.tblalumnos.getColumnModel().getColumn(0).setPreferredWidth(300);
        
        av.tblalumnos.getColumnModel().getColumn(1).setMaxWidth(90);
        av.tblalumnos.getColumnModel().getColumn(1).setMinWidth(90);
        av.tblalumnos.getColumnModel().getColumn(1).setPreferredWidth(90);
        
        for(int j = 2; j < 19 ; j++){
            av.tblalumnos.getColumnModel().getColumn(j).setMaxWidth(0);
            av.tblalumnos.getColumnModel().getColumn(j).setMinWidth(0);
            av.tblalumnos.getColumnModel().getColumn(j).setPreferredWidth(0);
        }*/
        
        
    }
    
    public void BuscarFotoAlumno() throws IOException{
    Conexion cc = new Conexion();
    Connection cn = cc.getConexion();
    
    Image rpta=null;
    
    int idalu;
    idalu = Integer.parseInt(av.tblalumnos.getValueAt(fila, 2).toString());
    
    av.lblfoto.setIcon(null);
    
    av.lblfoto.setText("FOTO");
        
        try{
            Statement ps = cn.createStatement();
            ResultSet rs = ps.executeQuery("SELECT foto_alu FROM alumnos WHERE id_alu = " + idalu + "");
            if (rs.next()){  
                
                ByteArrayOutputStream ouput = new ByteArrayOutputStream();
                InputStream isdatos = rs.getBinaryStream("foto_alu");
                if(isdatos != null){
                    int temp=isdatos.read();
                    while(temp>=0)
                    {
                       ouput.write((char)temp);
                       temp=isdatos.read();

                    }
                    Image imagen=Toolkit.getDefaultToolkit().createImage(ouput.toByteArray());
                    //imagen = imagen;
                    av.lblfoto.setText(null);
                    av.lblfoto.setIcon(new ImageIcon(imagen.getScaledInstance(av.lblfoto.getWidth(), av.lblfoto.getHeight(), Image.SCALE_DEFAULT)));
                }
                
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
    }
    
    public void BuscarEscolaridadAlumnos(){
    Conexion cc = new Conexion();
    Connection cn = cc.getConexion();
    
    int idalu;
    idalu = Integer.parseInt(av.tblalumnos.getValueAt(fila, 2).toString());
        
        try{
            Statement ps = cn.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM escolaridad WHERE id_alu = " + idalu + "");
            if (rs.next()){  
                                
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
    }
    
    public void altaalumnosescolaridad(){
        String curp = null;
        curp = av.txtcurp.getText();
        int fil = av.tblposgrado.getRowCount();
        int con = 0;
        for(int i = 0 ; i < fil ; i++){
            int niv_esc = 0;
            String nom_uni_esc = null;
            String nom_est_esc = null;
            boolean fin_esc = false;
            boolean tit_esc = false;
            
            if(av.tblposgrado.getValueAt(i, 1).toString().equals("Licenciatura")){
                niv_esc = 0;
            }else if(av.tblposgrado.getValueAt(i, 1).toString().equals("Diplomado")){
                niv_esc = 1;
            }else if(av.tblposgrado.getValueAt(i, 1).toString().equals("Maestria")){
                niv_esc = 2;
            }else if(av.tblposgrado.getValueAt(i, 1).toString().equals("Doctorado")){
                niv_esc = 3;
            }
            
            nom_uni_esc = av.tblposgrado.getValueAt(i, 3).toString();
            nom_est_esc = av.tblposgrado.getValueAt(i, 2).toString();
            
            if(av.tblposgrado.getValueAt(i, 4).toString() == "Si"){
                fin_esc = true;
            }else{
                fin_esc = false;
            }
            
            if(av.tblposgrado.getValueAt(i, 5).toString() == "Si"){
                tit_esc = true;
            }else{
                tit_esc = false;
            }
            
            /*=== Escolaridad ===*/
            am.setNiv_esc(niv_esc);
            am.setNom_uni_esc(nom_uni_esc);
            am.setNom_est_esc(nom_est_esc);
            am.setFin_esc(fin_esc);
            am.setTit_esc(tit_esc);
            am.setCurp_alu(curp);
            
            if(am.AltaAlumnosEscolaridad(niv_esc, nom_uni_esc, nom_est_esc, fin_esc, tit_esc, curp)){
                //JOptionPane.showMessageDialog(null, "Registro Guardado");
                //llenartabla(av.tblalumnos);
                //limpiar();
                //bloquear();
                //av.btnguardar.setEnabled(false);
                con = con +1;
            }else{
                JOptionPane.showMessageDialog(null, "Error al Guardar");
                //limpiar();
            }            
        }
        
        if(con == fil){
            JOptionPane.showMessageDialog(null, "Registro Guardado");
            llenartabla(av.tblalumnos);
            //limpiar();
            bloquear();
            av.btnguardar.setEnabled(false);
            av.btncancelar.setEnabled(false);
            av.btnnuevo.setEnabled(true);
            av.btneditar.setEnabled(true);
            av.btnsalir.setEnabled(true);
            av.btneliminar.setEnabled(true);
        }
        
    }
    
    public void actualizaralumnos(){
        /*String institucion, siglas, nombre, apepaterno, apematerno, cargo, telefono;
        int id, tipop;
        String tipo = null;

        id = Integer.parseInt(pv.txtid.getText());
        institucion = pv.txtinstitucion.getText();
        siglas = pv.txtsiglas.getText();
        nombre = pv.txtnombre.getText();
        apepaterno = pv.txtapepat.getText();
        apematerno = pv.txtapemat.getText();
        cargo = pv.txtcargo.getText();
        telefono = pv.txttelefono.getText();

        pv.jRadioButton1.setActionCommand("Alumno");
        pv.jRadioButton2.setActionCommand("Docente");
        pv.jRadioButton4.setActionCommand("Ambos");
        tipo= pv.tipoprocedencia.getSelection().getActionCommand();
        if(tipo == "Alumno"){
            tipop = 0;
        }else if(tipo == "Docente"){
            tipop = 1;
        }else{
            tipop = 2;
        }

        pm.setId_pro(id);
        pm.setIns_pro(institucion);
        pm.setSig_pro(siglas);
        pm.setNom_pro(nombre);
        pm.setApe_pat_pro(apepaterno);
        pm.setApe_mat_pro(apematerno);
        pm.setCar_pro(cargo);
        pm.setTel_pro(telefono);
        pm.setTipo_pro(tipop);

        if(pm.ModificarProcedencia(id, institucion, siglas, nombre, apepaterno, apematerno, cargo, telefono, tipop)){
            JOptionPane.showMessageDialog(null, "Registro Actualizado");
            llenartabla(pv.tblprocedencia);
            limpiar();
            pv.btnguardar.setText("Guardar");
            ImageIcon img = new ImageIcon("src/img/guardar.png");
            pv.btnguardar.setIcon(img);
            bloquear();
            pv.btnguardar.setEnabled(false);
            pv.btncancelar.setEnabled(false);
            pv.btnnuevo.setEnabled(true);
            pv.btneditar.setEnabled(true);
            pv.btnsalir.setEnabled(true);
            pv.btneliminar.setEnabled(true);
        }else{
            JOptionPane.showMessageDialog(null, "Error al Actualizar");
            limpiar();
            pv.btnguardar.setText("Guardar");
            ImageIcon img = new ImageIcon("src/img/guardar.png");
            pv.btnguardar.setIcon(img);
        }*/
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

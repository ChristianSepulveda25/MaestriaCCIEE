/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.ProcedenciaM;
import Vista.ProcedenciaV;
import com.mysql.jdbc.Connection;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author INFORMATICA-05
 */
public class ProcedenciaC implements ActionListener, KeyListener {
    
    private final ProcedenciaM pm;
    private final ProcedenciaV pv;
    
    public ProcedenciaC(ProcedenciaM pm, ProcedenciaV pv){
        
        this.pm = pm;
        this.pv = pv;
        this.pv.btnnuevo.addActionListener(this);
        this.pv.btnguardar.addActionListener(this);
        this.pv.btneditar.addActionListener(this);
        this.pv.btneliminar.addActionListener(this);
        this.pv.btncancelar.addActionListener(this);
        this.pv.btnsalir.addActionListener(this);
        this.pv.txtbuscar.addKeyListener((KeyListener) this);
    }  
    
    public void iniciar(){
        pv.setTitle("Alta Procedencias");
        pv.setSize(820, 415);
        llenartabla(pv.tblprocedencia);
        bloquear();
        pv.btnguardar.setEnabled(false);
        pv.btncancelar.setEnabled(false);
        pv.jRadioButton1.setSelected(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == pv.btnguardar){
            String a = pv.txtid.getText();
            if(a.equals("")){
                String institucion, siglas, nombre, apepaterno, apematerno, cargo, telefono;
                int tipop;
                String tipo = null;
            
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
                
                pm.setIns_pro(institucion);
                pm.setSig_pro(siglas);
                pm.setNom_pro(nombre);
                pm.setApe_pat_pro(apepaterno);
                pm.setApe_mat_pro(apematerno);
                pm.setCar_pro(cargo);
                pm.setTel_pro(telefono);
                pm.setTipo_pro(tipop);

                if(pm.AltaProcedencia(institucion, siglas, nombre, apepaterno, apematerno, cargo, telefono, tipop)){
                    JOptionPane.showMessageDialog(null, "Registro Guardado");
                    llenartabla(pv.tblprocedencia);
                    limpiar();
                    bloquear();
                    pv.btnguardar.setEnabled(false);
                    pv.btncancelar.setEnabled(false);
                    pv.btnnuevo.setEnabled(true);
                    pv.btneditar.setEnabled(true);
                    pv.btnsalir.setEnabled(true);
                    pv.btneliminar.setEnabled(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                    limpiar();
                }
            }else{
                String institucion, siglas, nombre, apepaterno, apematerno, cargo, telefono;
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
                }
            }
            
        }else if(e.getSource() == pv.btneditar){
            int fila = pv.tblprocedencia.getSelectedRow();
            if(fila < 0){
                JOptionPane.showMessageDialog(pv, "Seleccione una fila");
            }else{
                pv.btnguardar.setText("Actualizar");
                ImageIcon img = new ImageIcon("src/img/actualizar.png");
                pv.btnguardar.setIcon(img);
                pv.txtid.setText(pv.tblprocedencia.getValueAt(fila, 0).toString());
                pv.txtinstitucion.setText(pv.tblprocedencia.getValueAt(fila, 1).toString());
                pv.txtsiglas.setText(pv.tblprocedencia.getValueAt(fila, 2).toString());
                pv.txtnombre.setText(pv.tblprocedencia.getValueAt(fila, 3).toString());
                pv.txtapepat.setText(pv.tblprocedencia.getValueAt(fila, 4).toString());
                pv.txtapemat.setText(pv.tblprocedencia.getValueAt(fila, 5).toString());
                pv.txtcargo.setText(pv.tblprocedencia.getValueAt(fila, 6).toString());
                pv.txttelefono.setText(pv.tblprocedencia.getValueAt(fila, 7).toString());
                
                String tipofila;
                int tipovalor;
                tipofila = pv.tblprocedencia.getValueAt(fila, 8).toString();
                tipovalor = Integer.parseInt(tipofila);
                if(tipovalor == 0){
                    pv.jRadioButton1.setSelected(true);
                }else if(tipovalor == 1){
                    pv.jRadioButton2.setSelected(true);
                }else{
                    pv.jRadioButton4.setSelected(true);
                }
                desbloquear();
                pv.btnnuevo.setEnabled(false);
                pv.btneditar.setEnabled(false);
                pv.btnsalir.setEnabled(false);
                pv.btneliminar.setEnabled(false);
                pv.btnguardar.setEnabled(true);
                pv.btncancelar.setEnabled(true);
            }
        }else if(e.getSource() == pv.btneliminar){
            int fila = pv.tblprocedencia.getSelectedRow();
            if(fila < 0){
                JOptionPane.showMessageDialog(pv, "Seleccione una fila");
            }else{ 
                int id;
                
                id = Integer.parseInt(pv.tblprocedencia.getValueAt(fila, 0).toString());
                pm.setId_pro(id);
                
                int resp;
                resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar el registro?", "Alerta!", JOptionPane.YES_NO_OPTION);
                
                if(resp == 0){
                    if(pm.EliminarProcedencia(id)){
                        JOptionPane.showMessageDialog(null, "Registro Eliminado");
                        llenartabla(pv.tblprocedencia);
                        limpiar();
                    }else{
                        JOptionPane.showMessageDialog(null, "Error al Eliminar");
                        limpiar();
                    }
                }
            }
        }else if(e.getSource() == pv.btnsalir){
            pv.dispose();
        }else if(e.getSource() == pv.btnnuevo){
            limpiar();
            pv.btnguardar.setText("Guardar");
            ImageIcon img = new ImageIcon("src/img/guardar.png");
            pv.btnguardar.setIcon(img);
            desbloquear();
            pv.btnnuevo.setEnabled(false);
            pv.btneditar.setEnabled(false);
            pv.btnsalir.setEnabled(false);
            pv.btneliminar.setEnabled(false);
            pv.btnguardar.setEnabled(true);
            pv.btncancelar.setEnabled(true);
            pv.txtinstitucion.requestFocus();
        }else if(e.getSource() == pv.btncancelar){
            limpiar();
            pv.btnguardar.setText("Guardar");
            ImageIcon img = new ImageIcon("src/img/guardar.png");
            pv.btnguardar.setIcon(img);
            limpiar();
            bloquear();
            pv.btnguardar.setEnabled(false);
            pv.btncancelar.setEnabled(false);
            pv.btnnuevo.setEnabled(true);
            pv.btneditar.setEnabled(true);
            pv.btnsalir.setEnabled(true);
            pv.btneliminar.setEnabled(true);
        }
    }
    
    public void limpiar(){
        pv.txtid.setText(null);
        pv.txtinstitucion.setText(null);
        pv.txtsiglas.setText(null);
        pv.txtnombre.setText(null);
        pv.txtapepat.setText(null);
        pv.txtapemat.setText(null);
        pv.txtcargo.setText(null);
        pv.txttelefono.setText(null);
        pv.jRadioButton1.setSelected(true);
        pv.jRadioButton2.setSelected(false);
        pv.jRadioButton4.setSelected(false);
    }
    
    public void bloquear(){
        pv.txtid.setEnabled(false);
        pv.txtinstitucion.setEnabled(false);
        pv.txtsiglas.setEnabled(false);
        pv.txtnombre.setEnabled(false);
        pv.txtapepat.setEnabled(false);
        pv.txtapemat.setEnabled(false);
        pv.txtcargo.setEnabled(false);
        pv.txttelefono.setEnabled(false);
        pv.jRadioButton1.setEnabled(false);
        pv.jRadioButton2.setEnabled(false);
        pv.jRadioButton4.setEnabled(false);
    }
    
    public void desbloquear(){
        pv.txtid.setEnabled(true);
        pv.txtinstitucion.setEnabled(true);
        pv.txtsiglas.setEnabled(true);
        pv.txtnombre.setEnabled(true);
        pv.txtapepat.setEnabled(true);
        pv.txtapemat.setEnabled(true);
        pv.txtcargo.setEnabled(true);
        pv.txttelefono.setEnabled(true); 
        pv.jRadioButton1.setEnabled(true);
        pv.jRadioButton2.setEnabled(true);
        pv.jRadioButton4.setEnabled(true);       
    }
    
    public void llenartabla(JTable tblprocedencia){
        DefaultTableModel modelt = new DefaultTableModel();
        tblprocedencia.setModel(modelt);
        
        modelt.addColumn("ID");
        modelt.addColumn("INSTITUCIÓN");
        modelt.addColumn("SIGLAS");
        modelt.addColumn("NOMBRE REPRESENTANTE");
        modelt.addColumn("APELLIDO PATERNO");
        modelt.addColumn("APELLIDO MATERNO");
        modelt.addColumn("CARGO");
        modelt.addColumn("TELÉFONO");
        modelt.addColumn("TIPO");
        
        Object[] columna = new Object[9];
        int numreg = pm.listPersona().size();
        
        for (int i =0; i < numreg ; i++){
            columna[0]= pm.listPersona().get(i).getId_pro();
            columna[1]= pm.listPersona().get(i).getIns_pro();
            columna[2]= pm.listPersona().get(i).getSig_pro();
            columna[3]= pm.listPersona().get(i).getNom_pro();
            columna[4]= pm.listPersona().get(i).getApe_pat_pro();
            columna[5]= pm.listPersona().get(i).getApe_mat_pro();
            columna[6]= pm.listPersona().get(i).getCar_pro();
            columna[7]= pm.listPersona().get(i).getTel_pro();
            columna[8]= pm.listPersona().get(i).getTipo_pro();
            modelt.addRow(columna);
        }
        pv.tblprocedencia.getColumnModel().getColumn(0).setMaxWidth(0);
        pv.tblprocedencia.getColumnModel().getColumn(0).setMinWidth(0);
        pv.tblprocedencia.getColumnModel().getColumn(0).setPreferredWidth(0);
        pv.tblprocedencia.getColumnModel().getColumn(1).setMaxWidth(300);
        pv.tblprocedencia.getColumnModel().getColumn(1).setMinWidth(300);
        pv.tblprocedencia.getColumnModel().getColumn(1).setPreferredWidth(300);
        pv.tblprocedencia.getColumnModel().getColumn(2).setMaxWidth(80);
        pv.tblprocedencia.getColumnModel().getColumn(2).setMinWidth(80);
        pv.tblprocedencia.getColumnModel().getColumn(2).setPreferredWidth(80);
        
        for(int j = 3; j < 9; j++){
            pv.tblprocedencia.getColumnModel().getColumn(j).setMaxWidth(0);
            pv.tblprocedencia.getColumnModel().getColumn(j).setMinWidth(0);
            pv.tblprocedencia.getColumnModel().getColumn(j).setPreferredWidth(0);
        }
        
        
    }
    
    public void llenartablabusc(JTable tblprocedencia){
        DefaultTableModel modelt = new DefaultTableModel();
        tblprocedencia.setModel(modelt);
        
        modelt.addColumn("ID");
        modelt.addColumn("INSTITUCIÓN");
        modelt.addColumn("SIGLAS");
        modelt.addColumn("NOMBRE REPRESENTANTE");
        modelt.addColumn("APELLIDO PATERNO");
        modelt.addColumn("APELLIDO MATERNO");
        modelt.addColumn("CARGO");
        modelt.addColumn("TELÉFONO");
        
        Object[] columna = new Object[8];
        int numreg = pm.BuscPersona().size();
        
        for (int i =0; i < numreg ; i++){
            columna[0]= pm.BuscPersona().get(i).getId_pro();
            columna[1]= pm.BuscPersona().get(i).getIns_pro();
            columna[2]= pm.BuscPersona().get(i).getSig_pro();
            columna[3]= pm.BuscPersona().get(i).getNom_pro();
            columna[4]= pm.BuscPersona().get(i).getApe_pat_pro();
            columna[5]= pm.BuscPersona().get(i).getApe_mat_pro();
            columna[6]= pm.BuscPersona().get(i).getCar_pro();
            columna[7]= pm.BuscPersona().get(i).getTel_pro();
            modelt.addRow(columna);
        }   
        pv.tblprocedencia.getColumnModel().getColumn(0).setMaxWidth(0);
        pv.tblprocedencia.getColumnModel().getColumn(0).setMinWidth(0);
        pv.tblprocedencia.getColumnModel().getColumn(0).setPreferredWidth(0);
        pv.tblprocedencia.getColumnModel().getColumn(1).setMaxWidth(300);
        pv.tblprocedencia.getColumnModel().getColumn(1).setMinWidth(300);
        pv.tblprocedencia.getColumnModel().getColumn(1).setPreferredWidth(300);
        pv.tblprocedencia.getColumnModel().getColumn(2).setMaxWidth(80);
        pv.tblprocedencia.getColumnModel().getColumn(2).setMinWidth(80);
        pv.tblprocedencia.getColumnModel().getColumn(2).setPreferredWidth(80);
        
        for(int j = 3; j < 8; j++){
            pv.tblprocedencia.getColumnModel().getColumn(j).setMaxWidth(0);
            pv.tblprocedencia.getColumnModel().getColumn(j).setMinWidth(0);
            pv.tblprocedencia.getColumnModel().getColumn(j).setPreferredWidth(0);
        }         
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /*if (e.getSource() == pv.txtinstitucion){
            if (e.VK_ENTER == e.getKeyCode()){
                pv.txtsiglas.requestFocus();
            }
        }*/
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getKeyText(e.getKeyCode()) != pv.txtbuscar.toString()){
            pm.setBusqueda(pv.txtbuscar.getText());
            pm.BuscPersona();
            llenartablabusc(pv.tblprocedencia);
        }
        
    }
    
    
    
}

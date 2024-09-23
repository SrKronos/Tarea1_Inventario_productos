/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Productos;

import java.awt.Dimension;
import Productos.Producto;
import Productos.ControladorProducto;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Util.Respuesta;
import java.awt.Color;
import java.text.DecimalFormatSymbols;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
/**
 *
 * @author Asus.com
 */
public class rowMovimiento extends javax.swing.JPanel {

    public Movimiento p1;
    ControladorProducto cp;

    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    char decimalSeparator = symbols.getDecimalSeparator(); // Obtiene el separador decimal (puede ser ',' o '.')
    ControladorMovimientoProducto controladormovimiento = new ControladorMovimientoProducto();
// Crea una expresión regular que permita tanto el separador de decimales como los números
String regex = "^\\d+[" + decimalSeparator + "]?\\d{0.2}$";

    /**
     * Creates new form rowProductos
     * 
     */
    JFrame padre = new JFrame();
    
    public rowMovimiento(Movimiento p){
       initComponents();            
        p1 = p;
        cp = new ControladorProducto();
        getValores();
    }
    public rowMovimiento(Movimiento p,JFrame pf){
       initComponents();    
        padre = pf;
        p1 = p;
        cp = new ControladorProducto();
        getValores();
    }
    public rowMovimiento() {
        initComponents();
        p1 = new Movimiento();
        cp = new ControladorProducto();
        getValores();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        campo_codigo = new javax.swing.JTextField();
        campo_producto = new javax.swing.JTextField();
        campo_fecha = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        campo_cantidad = new javax.swing.JSpinner();
        ico_ingreso = new javax.swing.JLabel();
        ico_egreso = new javax.swing.JLabel();

        setBackground(new java.awt.Color(231, 231, 231));
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setMaximumSize(new java.awt.Dimension(910, 60));
        setMinimumSize(new java.awt.Dimension(910, 60));
        setPreferredSize(new java.awt.Dimension(910, 60));

        campo_codigo.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        campo_codigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campo_codigo.setText("0000");
        campo_codigo.setToolTipText("Código");
        campo_codigo.setEnabled(false);
        campo_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_codigoActionPerformed(evt);
            }
        });

        campo_producto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campo_producto.setText("Nombre del Producto");
        campo_producto.setToolTipText("Nombre");
        campo_producto.setEnabled(false);
        campo_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_productoActionPerformed(evt);
            }
        });
        campo_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campo_productoKeyReleased(evt);
            }
        });

        campo_fecha.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campo_fecha.setText("Fecha de Transacción");
        campo_fecha.setToolTipText("Fecha de Transacción");
        campo_fecha.setEnabled(false);

        btnEliminar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pack_eliminar_32.png"))); // NOI18N
        btnEliminar.setToolTipText("Seleccionar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        campo_cantidad.setEnabled(false);
        campo_cantidad.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                campo_cantidadInputMethodTextChanged(evt);
            }
        });
        campo_cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campo_cantidadKeyReleased(evt);
            }
        });

        ico_ingreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pack_ingreso_32.png"))); // NOI18N
        ico_ingreso.setToolTipText("Ingreso");

        ico_egreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pack_egreso_32.png"))); // NOI18N
        ico_egreso.setToolTipText("Egreso");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(campo_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campo_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campo_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ico_ingreso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ico_egreso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campo_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campo_cantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(ico_ingreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campo_codigo)
                    .addComponent(campo_producto)
                    .addComponent(ico_egreso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(campo_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(7, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void campo_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_codigoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    String mensaje = "Desea eliminar el "+p1.getTipo()+" de #"+p1.getCantidad()+" cantidades.";
        int r = JOptionPane.showConfirmDialog(this,mensaje,"titulo",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(r==0){            
            Respuesta respuesta = new Respuesta();
            respuesta = controladormovimiento.EliminarMovimiento(p1.getId());
            JOptionPane.showMessageDialog(this, respuesta.getMensaje());            
        }
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void campo_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_productoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_productoActionPerformed

    private void campo_productoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_productoKeyReleased
        
    }//GEN-LAST:event_campo_productoKeyReleased

    private void campo_cantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_cantidadKeyReleased
                String texto = String.copyValueOf((char[]) campo_cantidad.getValue());
                if (!texto.matches("^\\d+$")) {
                    campo_cantidad.setValue(Integer.parseInt(texto.substring(0, texto.length() - 1)));
                }
    }//GEN-LAST:event_campo_cantidadKeyReleased

    private void campo_cantidadInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_campo_cantidadInputMethodTextChanged
        
    }//GEN-LAST:event_campo_cantidadInputMethodTextChanged

    public void getValores(){
        if(p1.getId()>0){
        campo_codigo.setBackground(new Color(176, 255, 193));
        }
        campo_codigo.setText(String.valueOf(p1.getId()));
        campo_producto.setText(p1.getNombre());
        campo_cantidad.setValue(p1.getCantidad());
        campo_fecha.setText(p1.getFecha());        
        if(p1.getTipo().trim().equals("I")){
            ico_egreso.setVisible(false);
        }
        if(p1.getTipo().trim().equals("E")){
            ico_ingreso.setVisible(false);
        }        

    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JSpinner campo_cantidad;
    private javax.swing.JTextField campo_codigo;
    private javax.swing.JTextField campo_fecha;
    private javax.swing.JTextField campo_producto;
    private javax.swing.JLabel ico_egreso;
    private javax.swing.JLabel ico_ingreso;
    // End of variables declaration//GEN-END:variables
}

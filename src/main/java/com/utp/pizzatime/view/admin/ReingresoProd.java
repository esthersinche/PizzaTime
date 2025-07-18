package com.utp.pizzatime.view.admin;

import com.utp.pizzatime.model.dao.MovimientoCocinaDAO;
import com.utp.pizzatime.model.dao.PrioridadDAO;
import com.utp.pizzatime.model.dao.impl.I_MovimientoCocinaDAO;
import com.utp.pizzatime.model.dao.impl.I_PrioridadDAO;
import com.utp.pizzatime.model.dao.impl.I_ProductoDAO;
import com.utp.pizzatime.model.entity.Prioridad;
import com.utp.pizzatime.model.entity.Producto_modificar;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author BeeIsMega
 */
public class ReingresoProd extends javax.swing.JPanel {

    /**
     * Creates new form ReingresoProd
     */
    private List<Producto_modificar> listaIngredientes;
    private List<Prioridad> listaReingresos;
    private PrioridadDAO prioDao = new I_PrioridadDAO();
    private MovimientoCocinaDAO movCoc = new I_MovimientoCocinaDAO();

    public ReingresoProd() {
        initComponents();
        initIngredientesCombo();
        initReingresoFecha();
        cargarReingresosEnTabla();
    }

    private void initIngredientesCombo() {
        try {
            I_ProductoDAO prodDao = new I_ProductoDAO();
            listaIngredientes = prodDao.listarTodos();  // o el método que tengas
            DefaultComboBoxModel<String> md = new DefaultComboBoxModel<>();
            for (Producto_modificar p : listaIngredientes) {
                md.addElement(p.getNOMBRE_PRO());
            }
            cboIngrediente.setModel(md);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar ingredientes:\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initReingresoFecha() {
        Date hoy = new java.util.Date();
        txtFechaReingreso.setText(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        dateCaducidad.setMinSelectableDate(hoy);
    }

    private void cargarReingresosEnTabla() {
        try {
            listaReingresos = prioDao.findAllPrioridades();
            DefaultTableModel md = (DefaultTableModel) tblReingresos.getModel();
            md.setRowCount(0);
            for (Prioridad p : listaReingresos) {
                md.addRow(new Object[]{
                    p.getIdPrio(),
                    p.getIdMov(),
                    p.getCantidadUnit(),
                    p.getFechaPrio(),
                    p.getVencimiento(),
                    p.getLote()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar reingresos:\n" + ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Texto_RegistroIngreso1 = new javax.swing.JLabel();
        Text_Ingrediente1 = new javax.swing.JLabel();
        cboIngrediente = new javax.swing.JComboBox<>();
        Text_CantidadCajas1 = new javax.swing.JLabel();
        txtCantidadUnidades = new javax.swing.JTextField();
        Text_CantidadCajas2 = new javax.swing.JLabel();
        txtLote = new javax.swing.JTextField();
        Text_CantidadCajas3 = new javax.swing.JLabel();
        Text_CantidadCajas4 = new javax.swing.JLabel();
        dateCaducidad = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReingresos = new javax.swing.JTable();
        btnRegistrar = new javax.swing.JButton();
        txtFechaReingreso = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        Texto_RegistroIngreso1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Texto_RegistroIngreso1.setForeground(new java.awt.Color(0, 109, 86));
        Texto_RegistroIngreso1.setText("Reingreso de Ingredientes");

        Text_Ingrediente1.setBackground(new java.awt.Color(0, 0, 0));
        Text_Ingrediente1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Text_Ingrediente1.setText("Ingrediente");

        cboIngrediente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboIngredienteActionPerformed(evt);
            }
        });

        Text_CantidadCajas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Text_CantidadCajas1.setText("Cantidad Unidades");

        Text_CantidadCajas2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Text_CantidadCajas2.setText("Nro. Lote:");

        Text_CantidadCajas3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Text_CantidadCajas3.setText("Fecha Reingreso:");

        Text_CantidadCajas4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Text_CantidadCajas4.setText("Fecha Caducidad:");

        tblReingresos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "ID_MOV", "Cant", "Fecha_Reingreso", "Fecha Vencimiento", "Lote"
            }
        ));
        jScrollPane1.setViewportView(tblReingresos);

        btnRegistrar.setBackground(new java.awt.Color(0, 109, 86));
        btnRegistrar.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("Registrar Reingreso");
        btnRegistrar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), null, null));
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        txtFechaReingreso.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(33, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Texto_RegistroIngreso1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(Text_CantidadCajas4)
                                            .addGap(27, 27, 27))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(Text_CantidadCajas1)
                                            .addGap(18, 18, 18))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(Text_CantidadCajas2)
                                                .addComponent(Text_Ingrediente1))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Text_CantidadCajas3)
                                        .addGap(31, 31, 31)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboIngrediente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtLote)
                                    .addComponent(txtCantidadUnidades)
                                    .addComponent(dateCaducidad, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                    .addComponent(txtFechaReingreso))))
                        .addGap(32, 32, 32))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(Texto_RegistroIngreso1)
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Text_Ingrediente1)
                            .addComponent(cboIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Text_CantidadCajas1)
                            .addComponent(txtCantidadUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Text_CantidadCajas2)
                            .addComponent(txtLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Text_CantidadCajas3)
                            .addComponent(txtFechaReingreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Text_CantidadCajas4))
                        .addGap(49, 49, 49)
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboIngredienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboIngredienteActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        String nombre = (String) cboIngrediente.getSelectedItem();
        String lote = txtLote.getText().trim();
        int unidades;
        try {
            unidades = Integer.parseInt(txtCantidadUnidades.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad inválida");
            return;
        }

        String idPro = listaIngredientes.stream()
                .filter(p -> p.getNOMBRE_PRO().equals(nombre))
                .map(Producto_modificar::getID_PRO)
                .findFirst().orElse(null);
        if (idPro == null) {
            return;
        }

        String idMov;
        try {
            I_MovimientoCocinaDAO movDao = new I_MovimientoCocinaDAO();
            idMov = movDao.findLastIdMovByLote(lote);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al buscar movimiento para lote “" + lote + "”: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (idMov == null) {
            JOptionPane.showMessageDialog(this,
                    "No se encontró movimiento de cocina para el lote “" + lote + "”",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        java.util.Date utilDate = dateCaducidad.getDate();
        if (utilDate == null) {
            JOptionPane.showMessageDialog(this,
                    "Debes seleccionar una fecha de caducidad",
                    "Fecha inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        LocalDate fechaSeleccionada = utilDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate hoy = LocalDate.now();
        if (fechaSeleccionada.isBefore(hoy)) {
            JOptionPane.showMessageDialog(this,
                    "La fecha de caducidad no puede ser anterior a hoy ("
                    + hoy.format(DateTimeFormatter.ISO_DATE) + ")",
                    "Fecha inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        java.sql.Date sqlVenc = java.sql.Date.valueOf(fechaSeleccionada);

        java.sql.Date sqlReing = new java.sql.Date((new java.util.Date()).getTime());

        Prioridad pr = new Prioridad();
        pr.setIdMov(idMov);
        pr.setCantidadUnit(unidades);
        pr.setFechaPrio(sqlReing);
        pr.setVencimiento(sqlVenc);
        pr.setLote(lote);

        try {
            prioDao.insertPrioridad(pr);
            JOptionPane.showMessageDialog(this, "Reingreso registrado");
            cargarReingresosEnTabla();  // refrescas la tabla
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error:\n" + ex.getMessage());
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Text_CantidadCajas1;
    private javax.swing.JLabel Text_CantidadCajas2;
    private javax.swing.JLabel Text_CantidadCajas3;
    private javax.swing.JLabel Text_CantidadCajas4;
    private javax.swing.JLabel Text_Ingrediente1;
    private javax.swing.JLabel Texto_RegistroIngreso1;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cboIngrediente;
    private com.toedter.calendar.JDateChooser dateCaducidad;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReingresos;
    private javax.swing.JTextField txtCantidadUnidades;
    private javax.swing.JTextField txtFechaReingreso;
    private javax.swing.JTextField txtLote;
    // End of variables declaration//GEN-END:variables
}

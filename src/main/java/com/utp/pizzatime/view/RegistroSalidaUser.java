package com.utp.pizzatime.view;

import com.utp.pizzatime.model.entity.MovimientoCocina;
import com.utp.pizzatime.util.SesionActiva;
import com.utp.pizzatime.util.SQLConexion;
import com.utp.pizzatime.model.dao.impl.I_MovimientoCocinaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andrecito
 */
public class RegistroSalidaUser extends javax.swing.JPanel {

    public RegistroSalidaUser() {
        initComponents();
        cargarIngredientesDesdeBD();
        cargarTablaSalida();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_RegistroSalida = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbprodsalida = new javax.swing.JTable();
        btncancelarsalida = new javax.swing.JButton();
        btnguardarsalida = new javax.swing.JButton();
        Text_Ingrediente1 = new javax.swing.JLabel();
        Texto_RegistroIngreso1 = new javax.swing.JLabel();
        cboingsalida = new javax.swing.JComboBox<>();
        Text_CantidadCajas1 = new javax.swing.JLabel();
        txtcantcajasalida = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(770, 520));

        Panel_RegistroSalida.setBackground(new java.awt.Color(255, 255, 255));

        tbprodsalida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Producto", "Cantidad", "Fecha de ingreso", "Fecha de caducidad"
            }
        ));
        jScrollPane3.setViewportView(tbprodsalida);

        btncancelarsalida.setBackground(new java.awt.Color(0, 109, 86));
        btncancelarsalida.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btncancelarsalida.setText("Cancelar");
        btncancelarsalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarsalidaActionPerformed(evt);
            }
        });

        btnguardarsalida.setBackground(new java.awt.Color(0, 109, 86));
        btnguardarsalida.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnguardarsalida.setText("Guardar");
        btnguardarsalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarsalidaActionPerformed(evt);
            }
        });

        Text_Ingrediente1.setBackground(new java.awt.Color(0, 0, 0));
        Text_Ingrediente1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Text_Ingrediente1.setText("Ingrediente");

        Texto_RegistroIngreso1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Texto_RegistroIngreso1.setForeground(new java.awt.Color(0, 109, 86));
        Texto_RegistroIngreso1.setText("Registro de Salida");

        cboingsalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboingsalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboingsalidaActionPerformed(evt);
            }
        });

        Text_CantidadCajas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Text_CantidadCajas1.setText("Cantidad Cajas");

        javax.swing.GroupLayout Panel_RegistroSalidaLayout = new javax.swing.GroupLayout(Panel_RegistroSalida);
        Panel_RegistroSalida.setLayout(Panel_RegistroSalidaLayout);
        Panel_RegistroSalidaLayout.setHorizontalGroup(
            Panel_RegistroSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_RegistroSalidaLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(Panel_RegistroSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_RegistroSalidaLayout.createSequentialGroup()
                        .addGroup(Panel_RegistroSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Text_Ingrediente1)
                            .addComponent(Text_CantidadCajas1))
                        .addGap(14, 14, 14)
                        .addGroup(Panel_RegistroSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcantcajasalida, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Panel_RegistroSalidaLayout.createSequentialGroup()
                                .addComponent(cboingsalida, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnguardarsalida, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(Panel_RegistroSalidaLayout.createSequentialGroup()
                        .addGroup(Panel_RegistroSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Texto_RegistroIngreso1)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 26, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_RegistroSalidaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btncancelarsalida, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        Panel_RegistroSalidaLayout.setVerticalGroup(
            Panel_RegistroSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_RegistroSalidaLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(Texto_RegistroIngreso1)
                .addGap(12, 12, 12)
                .addGroup(Panel_RegistroSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Text_Ingrediente1)
                    .addComponent(cboingsalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnguardarsalida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_RegistroSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Text_CantidadCajas1)
                    .addComponent(txtcantcajasalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btncancelarsalida, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Panel_RegistroSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Panel_RegistroSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btncancelarsalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarsalidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelarsalidaActionPerformed

    // Método para cargar los datos en la tabla después de una operación.
    private void cargarTablaSalida() {
        try (Connection con = new SQLConexion().establecerConexion()) {
            // Modificada la consulta SQL para hacer un JOIN entre DISPONIBLE y PRODUCTO
            String sql = "SELECT p.NOMBRE_PRO, d.CANTIDAD_CAJAS, d.FECHA_DIS, d.VENCIMIENTO "
                       + "FROM DISPONIBLE d "
                       + "JOIN PRODUCTO p ON d.ID_PRO = p.ID_PRO "
                       + "WHERE d.CANTIDAD_CAJAS > 0"; 

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Limpiar tabla antes de cargar los datos
            DefaultTableModel model = (DefaultTableModel) tbprodsalida.getModel();
            model.setRowCount(0); // Limpiar filas existentes

            // Agregar los datos al modelo de la tabla
            while (rs.next()) {
                Object[] row = new Object[4];
                row[0] = rs.getString("NOMBRE_PRO");  // Producto
                row[1] = rs.getInt("CANTIDAD_CAJAS"); // Cantidad
                row[2] = rs.getDate("FECHA_DIS");     // Fecha de Ingreso
                row[3] = rs.getDate("VENCIMIENTO");   // Fecha de Caducidad
                model.addRow(row); // Añadir una nueva fila
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos de la tabla: " + e.getMessage());
        }
    }


    
    private void btnguardarsalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarsalidaActionPerformed
        try (Connection con = new SQLConexion().establecerConexion()) {

            String productoNombre = cboingsalida.getSelectedItem().toString();
            int cantidadRequerida = Integer.parseInt(txtcantcajasalida.getText());
            int dni = SesionActiva.empleadoLogueado.getDni_emp();

            PreparedStatement psIDPRO = con.prepareStatement("SELECT ID_PRO FROM PRODUCTO WHERE NOMBRE_PRO = ?");
            psIDPRO.setString(1, productoNombre);
            ResultSet rsIDPRO = psIDPRO.executeQuery();

            String idPro = null;
            if (rsIDPRO.next()) {
                idPro = rsIDPRO.getString("ID_PRO");
            }

            // Buscar lotes disponibles para ese producto (orden FIFO)
            PreparedStatement psLotes = con.prepareStatement(
                    "SELECT ID_DIS, CANTIDAD_CAJAS, LOTE FROM DISPONIBLE WHERE ID_PRO = ? AND CANTIDAD_CAJAS > 0 ORDER BY VENCIMIENTO ASC");
            psLotes.setString(1, idPro);
            ResultSet rsLotes = psLotes.executeQuery();

            List<MovimientoCocina> listaMov = new ArrayList<>();
            while (rsLotes.next() && cantidadRequerida > 0) {
                String idDis = rsLotes.getString("ID_DIS");
                String lote = rsLotes.getString("LOTE");
                int disponibles = rsLotes.getInt("CANTIDAD_CAJAS");
                int cantidadUsada = Math.min(disponibles, cantidadRequerida);

                MovimientoCocina m = new MovimientoCocina();
                m.setId_dis(idDis);
                m.setDni_emp(dni);
                m.setCantidad_unit(0);
                m.setCantidad_cajas(cantidadUsada);
                m.setLote(lote);
                listaMov.add(m);

                // Actualizar stock
                PreparedStatement psUpdate = con.prepareStatement(
                        "UPDATE DISPONIBLE SET CANTIDAD_CAJAS = CANTIDAD_CAJAS - ? WHERE ID_DIS = ?");
                psUpdate.setInt(1, cantidadUsada);
                psUpdate.setString(2, idDis);
                psUpdate.executeUpdate();

                cantidadRequerida -= cantidadUsada;
            }

            if (listaMov.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No hay stock suficiente para registrar esta salida.",
                        "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 3) Invocas el DAO **por cada movimiento**:
            I_MovimientoCocinaDAO dao = new I_MovimientoCocinaDAO();
            for (MovimientoCocina m : listaMov) {
                dao.registrarMovimientoCocina(m);
            }

            JOptionPane.showMessageDialog(this,
                    "Salida registrada correctamente.",
                    "OK", JOptionPane.INFORMATION_MESSAGE);

            // TODO: recargar tabla, limpiar campos...
                    cargarTablaSalida();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al guardar salida: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);

    }    }//GEN-LAST:event_btnguardarsalidaActionPerformed

    private void cargarIngredientesDesdeBD() {
        try (Connection con = new SQLConexion().establecerConexion()) {
            String sql = "SELECT NOMBRE_PRO FROM PRODUCTO";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            cboingsalida.removeAllItems();  // Limpiar antes de cargar

            while (rs.next()) {
                cboingsalida.addItem(rs.getString("NOMBRE_PRO"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar ingredientes: " + e.getMessage());
        }
    }

    private void cboingsalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboingsalidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboingsalidaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_RegistroSalida;
    private javax.swing.JLabel Text_CantidadCajas1;
    private javax.swing.JLabel Text_Ingrediente1;
    private javax.swing.JLabel Texto_RegistroIngreso1;
    private javax.swing.JButton btncancelarsalida;
    private javax.swing.JButton btnguardarsalida;
    private javax.swing.JComboBox<String> cboingsalida;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbprodsalida;
    private javax.swing.JTextField txtcantcajasalida;
    // End of variables declaration//GEN-END:variables
}

package com.utp.pizzatime.view.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.utp.pizzatime.util.SQLConexion;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Reportes extends javax.swing.JPanel {

    public Reportes() {
        initComponents();
        
        // 1) Poblar el combo Ingreso/Salida
        ComboBoxTipos.removeAllItems();
        ComboBoxTipos.addItem("Ingreso");
        ComboBoxTipos.addItem("Salida");

        // 2) Asociar evento al combo
        ComboBoxTipos.addActionListener(this::ComboBoxTiposActionPerformed);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Btn_excelimpo = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableRepor = new javax.swing.JTable();
        Button_Repo = new javax.swing.JButton();
        ComboBoxTipos = new javax.swing.JComboBox<>();
        DateInicial = new com.toedter.calendar.JDateChooser();
        DateFinal = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setBackground(new java.awt.Color(255, 255, 255));

        Btn_excelimpo.setBackground(new java.awt.Color(0, 109, 86));
        Btn_excelimpo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn_excelimpo.setForeground(new java.awt.Color(255, 255, 255));
        Btn_excelimpo.setText("Exportar Excel");
        Btn_excelimpo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_excelimpoActionPerformed(evt);
            }
        });

        TableRepor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_Producto", "Producto", "Medida", "Stock_Actual", "Stock_Cajas", "Stock_Min", "Stock_Max", "ID_Proveedor", "Precio", "Descripcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TableRepor);

        Button_Repo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Button_Repo.setForeground(new java.awt.Color(0, 109, 86));
        Button_Repo.setText("Generar Reporte");
        Button_Repo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_RepoActionPerformed(evt);
            }
        });

        ComboBoxTipos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboBoxTipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxTiposActionPerformed(evt);
            }
        });

        jLabel1.setText("Desde:");

        jLabel2.setText("Hasta:");

        jLabel3.setText("Tipo de movimiento");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(ComboBoxTipos, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DateInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DateFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                                .addComponent(Button_Repo))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Btn_excelimpo)
                        .addGap(292, 292, 292)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxTipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DateInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(DateFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Btn_excelimpo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Button_Repo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

        //--------------------------------------------------//
    
    private void Btn_excelimpoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_excelimpoActionPerformed

        // Asumimos que la tabla ya está cargada por Button_RepoActionPerformed
    TableModel model = TableRepor.getModel();
    if (model.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "No hay datos para exportar.");
        return;
    }

    // Preparar diálogo de guardar
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Guardar como");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    String timestamp = LocalDateTime.now().format(formatter);
    fileChooser.setSelectedFile(new java.io.File("Reporte_Pizzeria_" + timestamp + ".xlsx"));

    int userSelection = fileChooser.showSaveDialog(this);
    if (userSelection != JFileChooser.APPROVE_OPTION) {
        return;
    }

    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
    if (!filePath.toLowerCase().endsWith(".xlsx")) {
        filePath += ".xlsx";
    }

    // Crear y escribir el archivo Excel
    try (Workbook workbook = new XSSFWorkbook();
         FileOutputStream out = new FileOutputStream(filePath)) {

        Sheet sheet = workbook.createSheet("Reporte");
        // Header
        Row header = sheet.createRow(0);
        for (int i = 0; i < model.getColumnCount(); i++) {
            header.createCell(i).setCellValue(model.getColumnName(i));
        }
        // Datos
        for (int r = 0; r < model.getRowCount(); r++) {
            Row row = sheet.createRow(r + 1);
            for (int c = 0; c < model.getColumnCount(); c++) {
                Object val = model.getValueAt(r, c);
                row.createCell(c).setCellValue(val != null ? val.toString() : "");
            }
        }

        workbook.write(out);
        JOptionPane.showMessageDialog(this, "Archivo guardado en:\n" + filePath);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al guardar el archivo:\n" + e.getMessage());
    }

    }//GEN-LAST:event_Btn_excelimpoActionPerformed
    //------------------------------------------------------------------------------//
    //-------------------------------------------------------------------//
    
    private void Button_RepoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_RepoActionPerformed
        
             
    // 1) Lee fechas (java.util.Date)
    java.util.Date inicio = DateInicial.getDate();
    java.util.Date fin    = DateFinal.getDate();
    if (inicio == null || fin == null) {
        JOptionPane.showMessageDialog(this, "Seleccione ambas fechas.");
        return;
    }

    // 2) Prepara el modelo de la tabla
    DefaultTableModel model = (DefaultTableModel) TableRepor.getModel();
    model.setRowCount(0);

    // 3) Detecta Ingreso vs Salida
    String tipo = ComboBoxTipos.getSelectedItem().toString();
    String sql;
    if ("Ingreso".equalsIgnoreCase(tipo)) {
        model.setColumnIdentifiers(new String[]{
            "ID_DIS", "Producto", "Cant. Cajas", "Lote", "Fecha Ingreso", "Vencimiento"
        });
        sql =
            "SELECT d.ID_DIS, p.NOMBRE_PRO, d.CANTIDAD_CAJAS, d.LOTE, d.FECHA_DIS, d.VENCIMIENTO " +
            "FROM DISPONIBLE d " +
            " JOIN PRODUCTO p ON d.ID_PRO = p.ID_PRO " +
            "WHERE d.FECHA_DIS BETWEEN ? AND ? " +
            "ORDER BY d.FECHA_DIS";
    } else {
        model.setColumnIdentifiers(new String[]{
            "ID_MOV", "Producto", "Cant. Cajas", "Cant. Unid.", "Fecha Salida", "Motivo"
        });
        sql =
            "SELECT m.ID_MOV, p.NOMBRE_PRO, m.CANTIDAD_CAJAS, m.CANTIDAD_UNIT, m.FECHA_MOV, m.MOTIVO " +
            "FROM MOVIMIENTO_COCINA m " +
            " JOIN DISPONIBLE d ON m.ID_DIS = d.ID_DIS " +
            " JOIN PRODUCTO p ON d.ID_PRO = p.ID_PRO " +
            "WHERE m.FECHA_MOV BETWEEN ? AND ? " +
            "ORDER BY m.FECHA_MOV";
    }

    // 4) Ejecuta la consulta y llena la tabla
    try (Connection conn = new SQLConexion().establecerConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setDate(1, new java.sql.Date(inicio.getTime()));
        ps.setDate(2, new java.sql.Date(fin   .getTime()));
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Object[] fila = new Object[model.getColumnCount()];
                for (int i = 0; i < fila.length; i++) {
                    fila[i] = rs.getObject(i+1);
                }
                model.addRow(fila);
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al generar reporte: " + ex.getMessage());
    }
        
    }//GEN-LAST:event_Button_RepoActionPerformed

    //------------------------------------------------------------------------------//
    
    private void ComboBoxTiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxTiposActionPerformed
        
        if (DateInicial.getDate() == null || DateFinal.getDate() == null) {
        return;
        }
        Button_RepoActionPerformed(null);
        
    }//GEN-LAST:event_ComboBoxTiposActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton Btn_excelimpo;
    private javax.swing.JButton Button_Repo;
    private javax.swing.JComboBox<String> ComboBoxTipos;
    private com.toedter.calendar.JDateChooser DateFinal;
    private com.toedter.calendar.JDateChooser DateInicial;
    private javax.swing.JTable TableRepor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

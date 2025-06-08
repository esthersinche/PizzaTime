package com.utp.pizzatime.view;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Reportes extends javax.swing.JPanel {

    public Reportes() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Btn_excelimpo = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableRepor = new javax.swing.JTable();

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

        Btn_excelimpo.setText("Exportar Excel");
        Btn_excelimpo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_excelimpoActionPerformed(evt);
            }
        });

        TableRepor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"flour", "2", "2123SA"},
                {"tomate souce", "3", "23DS34"},
                {"chesse", "4", "213DSF456"},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Producto", "Cantidad", "Lote"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TableRepor);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(Btn_excelimpo)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(Btn_excelimpo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_excelimpoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_excelimpoActionPerformed

        // Crear un JFileChooser para que el usuario elija dónde guardar el archivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar como");

        // Nombre por defecto del archivo
        fileChooser.setSelectedFile(new java.io.File("reporte.xlsx"));

        // Mostrar el cuadro de diálogo de guardar y capturar la opción del usuario
        int userSelection = fileChooser.showSaveDialog(this);

        // Si el usuario eligió una ruta y presionó "Guardar"
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado por el usuario
            java.io.File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            // Asegurarse de que el archivo tenga extensión .xlsx
            if (!filePath.toLowerCase().endsWith(".xlsx")) {
                filePath += ".xlsx";
            }

            // Crear un nuevo libro de Excel (.xlsx)
            try (Workbook workbook = new XSSFWorkbook()) {
                // Crear una hoja dentro del libro
                Sheet sheet = workbook.createSheet("Reporte");

                // Obtener el modelo de datos de la JTable
                TableModel model = TableRepor.getModel();

                // Crear la primera fila del archivo Excel para los nombres de las columnas
                Row header = sheet.createRow(0);
                for (int i = 0; i < model.getColumnCount(); i++) {
                    header.createCell(i).setCellValue(model.getColumnName(i)); // Nombre de la columna
                }

                // Escribir los datos de cada fila de la JTable en el archivo Excel
                for (int i = 0; i < model.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1); // +1 para dejar la primera fila para los encabezados
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Object value = model.getValueAt(i, j); // Obtener valor de la celda
                        row.createCell(j).setCellValue(value != null ? value.toString() : ""); // Escribir en Excel
                    }
                }

                // Guardar el archivo en la ruta seleccionada por el usuario
                try (FileOutputStream out = new FileOutputStream(filePath)) {
                    workbook.write(out); // Escribir el contenido del libro al archivo
                    // Mostrar mensaje de éxito
                    JOptionPane.showMessageDialog(this, "Archivo guardado en:\n" + filePath);
                }

            } catch (IOException e) {
                // Si ocurre un error al guardar, mostrar mensaje de error
                JOptionPane.showMessageDialog(this, "Error al guardar el archivo:\n" + e.getMessage());
            }
        }

    }//GEN-LAST:event_Btn_excelimpoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton Btn_excelimpo;
    private javax.swing.JTable TableRepor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

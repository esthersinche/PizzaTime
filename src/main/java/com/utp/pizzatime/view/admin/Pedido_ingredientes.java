package com.utp.pizzatime.view.admin;

import com.utp.pizzatime.service.PedidoService;
import com.utp.pizzatime.service.SessionService;
import com.utp.pizzatime.model.dao.PedidoDAO;
import com.utp.pizzatime.model.dao.ProductoDAO;
import com.utp.pizzatime.model.dao.impl.I_PedidoDAO;
import com.utp.pizzatime.model.dao.impl.I_ProductoDAO;
import com.utp.pizzatime.model.entity.DetallePedido;
import com.utp.pizzatime.model.entity.Pedido;
import com.utp.pizzatime.model.entity.Producto_modificar;
import com.utp.pizzatime.model.entity.Producto_pedido;
import com.utp.pizzatime.service.FecVenService;
import com.utp.pizzatime.service.P_ReportGeneratorService;
import com.utp.pizzatime.service.ProductoService;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
//damn son demasiadas librerias, bombardeen java

/**
 *
 * @author Laura&EstherSinche
 */
public class Pedido_ingredientes extends javax.swing.JPanel {

    /**
     * Creates new form Pedido_ingredientes
     */
    JPopupMenu popmenuanticlick; //para q al hacer anticlick en un row se pueda eliminar
    JMenuItem elimitem;
    private List<Producto_pedido> listaProductos;      // para combo y carrito
    private List<DetallePedido> listaDetalles = new ArrayList<>(); // carrito “persistible”
    private FecVenService fecvenserv = new FecVenService();
    private final ProductoService productoService = new ProductoService();
    private final PedidoService pedidoService = new PedidoService();

    public Pedido_ingredientes() {
        initComponents();

        table_head_color(tbped_ing, tbstockactualpeding);
        popmenuanticlick = new JPopupMenu();
        elimitem = new JMenuItem("Eliminar");
        elimitem.addActionListener(e -> elim_row_peding());
        popmenuanticlick.add(elimitem);

        MouseListener popuplistens = new PopupListener();//es un obj PopupListener, solo funciona pq no tiene constructor, solo se ve interfaz generica
        tbped_ing.addMouseListener(popuplistens);

        initProductos();

        fecvenserv.VeryNotifIng();

        //JTableHeader tableheader= tbped_ing.getTableHeader();
    }

    private void initProductos() {
        try {
            List<Producto_modificar> productos = productoService.listarTodos();

            // 1) Llenar el combo
            listaProductos = new ArrayList<>();
            DefaultComboBoxModel<String> modeloCombo = new DefaultComboBoxModel<>();
            for (Producto_modificar p : productos) {
                modeloCombo.addElement(p.getNOMBRE_PRO());
                // creamos un wrapper Producto_pedido para el carrito
                listaProductos.add(new Producto_pedido(
                        p.getID_PRO(), p.getNOMBRE_PRO(), p.getPRECIO()
                ));
            }
            cbonomingre.setModel(modeloCombo);

            // 2) Llenar la tabla de stock
            DefaultTableModel stockModel = (DefaultTableModel) tbstockactualpeding.getModel();
            stockModel.setRowCount(0);
            for (Producto_modificar p : productos) {
                stockModel.addRow(new Object[]{
                    p.getID_PRO(),
                    p.getNOMBRE_PRO(),
                    p.getSTOCK_CAJAS(),
                    p.getSTOCK_ACTUAL()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar productos:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refreshStock() {
        initProductos();   // recarga combo y tabla de stock
    }

    class PopupListener extends MouseAdapter {//clase q reescribe los eventos para q se ejecute el metodo chiquito ese

        @Override
        public void mousePressed(MouseEvent e) {

            plswork(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {

            plswork(e);
        }

        public void plswork(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popmenuanticlick.show(e.getComponent(), e.getX(), e.getY());//enseña el menu en donde esta el mouse

            }

        }

    }

    private void table_head_color(JTable tbped_ing, JTable tbstockactualpeding) {
        DefaultTableCellRenderer header_ren = new DefaultTableCellRenderer();//deja personalizar, pinta celdas en una Jtable

        header_ren.setBackground(new Color(0, 109, 86));//color del bg
        header_ren.setForeground(Color.LIGHT_GRAY);//color del texto
        header_ren.setHorizontalAlignment(SwingConstants.CENTER);//lo pone en el centro

        tbped_ing.getTableHeader().setDefaultRenderer(header_ren);//agarra el header y le pone la cosa personalizada
        tbstockactualpeding.getTableHeader().setDefaultRenderer(header_ren);
    }

    private void elim_row_peding() {
        DefaultTableModel tb_model_peding = (DefaultTableModel) tbped_ing.getModel();

        try {
            int row = tbped_ing.getSelectedRow();
            //namas para ver index
            System.out.println(row);

            tb_model_peding.removeRow(row);
            listaDetalles.remove(row);
            //for para ver la lista de listadetalles
            for (DetallePedido dp : listaDetalles) {
                System.out.println(dp.getNOMBRE_PRO());
            }

            JOptionPane.showMessageDialog(this,
                    "Eliminado con éxito.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar una fila.",
                    "Warning", JOptionPane.WARNING_MESSAGE);

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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbonomingre = new javax.swing.JComboBox<>();
        txtcantcajas = new javax.swing.JTextField();
        btnaddingadmin = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbped_ing = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txttotalpedido_ing = new javax.swing.JLabel();
        btnpediringadmin = new javax.swing.JButton();
        btncancelingadmin = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbstockactualpeding = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(770, 524));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 109, 86));
        jLabel1.setText("Pedido de Ingredientes");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(110, 104, 104));
        jLabel2.setText("Ingrediente :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(110, 104, 104));
        jLabel3.setText("Cantidad Cajas :");

        cbonomingre.setForeground(new java.awt.Color(110, 104, 104));
        cbonomingre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbonomingre.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        cbonomingre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbonomingre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbonomingreActionPerformed(evt);
            }
        });

        txtcantcajas.setForeground(new java.awt.Color(110, 104, 104));
        txtcantcajas.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));

        btnaddingadmin.setBackground(new java.awt.Color(204, 204, 204));
        btnaddingadmin.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnaddingadmin.setForeground(new java.awt.Color(0, 109, 86));
        btnaddingadmin.setText("Agregar");
        btnaddingadmin.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), null, null));
        btnaddingadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddingadminActionPerformed(evt);
            }
        });

        tbped_ing.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), null, null));
        tbped_ing.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbped_ing.setForeground(new java.awt.Color(0, 109, 86));
        tbped_ing.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Ingrediente", "Cantidad_Cajas", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbped_ing.setGridColor(new java.awt.Color(0, 109, 86));
        tbped_ing.setRowHeight(25);
        tbped_ing.setSelectionBackground(new java.awt.Color(0, 109, 86));
        tbped_ing.setSelectionForeground(new java.awt.Color(110, 104, 104));
        tbped_ing.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbped_ing.setShowGrid(false);
        jScrollPane1.setViewportView(tbped_ing);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(110, 104, 104));
        jLabel4.setText("Total : ");

        txttotalpedido_ing.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txttotalpedido_ing.setForeground(new java.awt.Color(110, 104, 104));
        txttotalpedido_ing.setText(".......");

        btnpediringadmin.setBackground(new java.awt.Color(0, 109, 86));
        btnpediringadmin.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        btnpediringadmin.setText("Pedir");
        btnpediringadmin.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), null, null));
        btnpediringadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpediringadminActionPerformed(evt);
            }
        });

        btncancelingadmin.setBackground(new java.awt.Color(0, 109, 86));
        btncancelingadmin.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        btncancelingadmin.setText("Cancelar");
        btncancelingadmin.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), null, null));
        btncancelingadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelingadminActionPerformed(evt);
            }
        });

        tbstockactualpeding.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbstockactualpeding.setForeground(new java.awt.Color(0, 109, 86));
        tbstockactualpeding.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ingrediente", "Stock Cajas", "Stock Un"
            }
        ));
        tbstockactualpeding.setGridColor(new java.awt.Color(0, 109, 86));
        tbstockactualpeding.setMinimumSize(new java.awt.Dimension(60, 100));
        tbstockactualpeding.setPreferredSize(new java.awt.Dimension(300, 100));
        jScrollPane2.setViewportView(tbstockactualpeding);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3))
                    .addGap(51, 51, 51)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbonomingre, 0, 405, Short.MAX_VALUE)
                        .addComponent(txtcantcajas))
                    .addGap(18, 18, 18)
                    .addComponent(btnaddingadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 31, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(46, 46, 46))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btncancelingadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(106, 106, 106)
                    .addComponent(btnpediringadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(174, 174, 174)
                    .addComponent(jLabel4)
                    .addGap(18, 18, 18)
                    .addComponent(txttotalpedido_ing)
                    .addGap(81, 81, 81)))
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbonomingre, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtcantcajas, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnaddingadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btncancelingadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnpediringadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txttotalpedido_ing)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnaddingadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddingadminActionPerformed
        String nombre = (String) cbonomingre.getSelectedItem();
        int cantidad;
        try {
            cantidad = Integer.parseInt(txtcantcajas.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Cantidad inválida",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Encuentra el producto en listaProductos
        Producto_pedido prod = listaProductos.stream()
                .filter(p -> p.getNOMBRE_PRO().equals(nombre))
                .findFirst().orElse(null);
        if (prod == null) {
            return;
        }

        //Añade fila al carrito (tbped_ing)
        DefaultTableModel cartModel = (DefaultTableModel) tbped_ing.getModel();
        cartModel.addRow(new Object[]{
            prod.getID_PRO(),
            prod.getNOMBRE_PRO(),
            cantidad,
            prod.getPRECIO()
        });

        //Crea el DetallePedido con idDet e idPed a null
        DetallePedido det = new DetallePedido(
                null, // idDet null para que el DAO lo genere
                null, // idPed null para que el DAO lo genere
                prod.getID_PRO(),
                prod.getNOMBRE_PRO(),
                prod.getPRECIO(),
                cantidad
        );
        listaDetalles.add(det);

        //Actualiza total en pantalla
        actualizarTotal();


    }//GEN-LAST:event_btnaddingadminActionPerformed

    private void btnpediringadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpediringadminActionPerformed
        if (listaDetalles.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No hay ingredientes en el pedido",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // 1) Crear objeto Pedido y persistirlo
            int dniEmp = SessionService.getCurrentDni();  // obtén el dni logueado
            Pedido pedido = new Pedido(null, dniEmp, new Date());
            pedido.setDetalles(new ArrayList<>(listaDetalles));

            pedidoService.crearPedido(pedido);  // aquí se asignan idPed e idDet

            // 2) Mostrar diálogo para guardar .docx
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Guardar Pedido como...");
            // Nombre por defecto con ID de pedido
            chooser.setSelectedFile(new File("pedido_" + pedido.getIdPed() + ".docx"));

            int userSel = chooser.showSaveDialog(this);
            if (userSel == JFileChooser.APPROVE_OPTION) {
                File fileToSave = chooser.getSelectedFile();
                String path = fileToSave.getAbsolutePath();
                // Asegurar extensión .docx
                if (!path.toLowerCase().endsWith(".docx")) {
                    path += ".docx";
                }

                // 3) Generar el documento Word
                try {
                    P_ReportGeneratorService.generatePedidoDocx(path, pedido);
                    JOptionPane.showMessageDialog(this,
                            "Pedido y documento guardados correctamente en:\n" + path,
                            "OK", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Error al generar el documento:\n" + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            // 4) Limpiar UI para un nuevo pedido
            listaDetalles.clear();
            ((DefaultTableModel) tbped_ing.getModel()).setRowCount(0);
            actualizarTotal();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al guardar pedido en BD:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnpediringadminActionPerformed

    private void cbonomingreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbonomingreActionPerformed

    }//GEN-LAST:event_cbonomingreActionPerformed

    private void btncancelingadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelingadminActionPerformed
        listaDetalles.clear();
        ((DefaultTableModel) tbped_ing.getModel()).setRowCount(0);
        actualizarTotal();
    }//GEN-LAST:event_btncancelingadminActionPerformed
    private void actualizarTotal() {
        double suma = listaDetalles.stream()
                .mapToDouble(d -> d.getPRECIO() * d.getCantidadCajas())
                .sum();
        txttotalpedido_ing.setText(String.format("%.2f", suma));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaddingadmin;
    private javax.swing.JButton btncancelingadmin;
    private javax.swing.JButton btnpediringadmin;
    private javax.swing.JComboBox<String> cbonomingre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbped_ing;
    private javax.swing.JTable tbstockactualpeding;
    private javax.swing.JTextField txtcantcajas;
    private javax.swing.JLabel txttotalpedido_ing;
    // End of variables declaration//GEN-END:variables
}

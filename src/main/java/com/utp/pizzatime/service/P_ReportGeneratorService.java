package com.utp.pizzatime.service;

import com.utp.pizzatime.model.entity.DetallePedido;
import com.utp.pizzatime.model.entity.Pedido;
import org.apache.poi.xwpf.usermodel.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author EstherSinche
 */

public class P_ReportGeneratorService {
    
    /**
     * Genera un .docx con los datos del pedido.
     *
     * @param outputPath  ruta donde se guardará el archivo
     * @param pedido      objeto Pedido con idPed, dniEmp, fecha, detalles
     * @throws IOException
     */
    public static void generatePedidoDocx(String outputPath, Pedido pedido) throws IOException {
        try (XWPFDocument doc = new XWPFDocument()) {
            // 1) Título / Cabecera
            XWPFParagraph p1 = doc.createParagraph();
            p1.setStyle("Heading1");
            p1.createRun().setText("Resumen de Pedido");

            // Datos del pedido
            XWPFParagraph p2 = doc.createParagraph();
            p2.createRun().setText("Código de Pedido: " + pedido.getIdPed());
            p2 = doc.createParagraph();
            p2.createRun().setText("DNI Empleado: " + pedido.getDniEmp());
            p2 = doc.createParagraph();
            p2.createRun().setText("Fecha: " + pedido.getFechaPedido());

            // 2) Tabla de detalles
            List<DetallePedido> detalles = pedido.getDetalles();
            XWPFTable table = doc.createTable(detalles.size() + 1, 4);

            // Encabezado
            table.getRow(0).getCell(0).setText("Código");
            table.getRow(0).getCell(1).setText("Ingrediente");
            table.getRow(0).getCell(2).setText("Cantidad Cajas");
            table.getRow(0).getCell(3).setText("Precio Unitario");

            // Filas de datos
            double total = 0;
            for (int i = 0; i < detalles.size(); i++) {
                DetallePedido d = detalles.get(i);
                XWPFTableRow row = table.getRow(i + 1);

                row.getCell(0).setText(d.getIdDet());
                row.getCell(1).setText(d.getNOMBRE_PRO());
                row.getCell(2).setText(String.valueOf(d.getCantidadCajas()));
                row.getCell(3).setText(String.format("%.2f", d.getPRECIO()));

                total += d.getCantidadCajas() * d.getPRECIO();
            }

            // 3) Pie con total
            XWPFParagraph pTotal = doc.createParagraph();
            pTotal.setAlignment(ParagraphAlignment.RIGHT);
            pTotal.createRun().setBold(true);
            pTotal.createRun().setText(String.format("Total: %.2f", total));

            // 4) Guardar archivo
            try (FileOutputStream out = new FileOutputStream(outputPath)) {
                doc.write(out);
            }
        }
    }
}

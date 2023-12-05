package tablas;

import controller.Controller;
import dtos.OrdenPagoDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TablaOP extends JFrame {

    private Controller controller;

    public TablaOP(Controller controller) {
        super("Tabla de Ordenes de Pago");
        this.controller = controller;
    }

    public void mostrarTablaCompleta() {
        List<OrdenPagoDTO> ordenesPago = controller.getAllOrdenespago();
        if (!ordenesPago.isEmpty()) {
            DefaultTableModel tableModel = new DefaultTableModel();

            tableModel.addColumn("ID");
            tableModel.addColumn("Importe Total");
            tableModel.addColumn("Metodo de Pago");
            tableModel.addColumn("Retenciones");
            tableModel.addColumn("Proveedor Asociado");

            for (OrdenPagoDTO op : ordenesPago) {
                Object[] rowData = {
                        op.getNroOrden(),
                        op.getTotalPagar(),
                        op.getFormaPago(),
                        op.getTotalRetenciones(),
                        op.getProveedor().getCuit(),
                };
                tableModel.addRow(rowData);
            }
            JTable tabla = new JTable(tableModel);

            JScrollPane scrollPane = new JScrollPane(tabla);
            getContentPane().add(scrollPane);

            setLayout(new GridLayout(1, 1));
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No hay Ordenes de Pago disponibles.", "Sin Ordenes de Compra", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
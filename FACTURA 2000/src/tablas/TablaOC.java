package tablas;

import controller.Controller;
import dtos.ItemProductoDTO;
import dtos.OrdenCompraDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TablaOC extends JFrame {

    private Controller controller;

    public TablaOC(Controller controller) {
        super("Tabla de Ordenes de Compra");
        this.controller = controller;
    }

    public void mostrarTabla(String cuit) {
        List<OrdenCompraDTO> ordenesCompra = controller.getAllOrdenesCompra();
        if (!ordenesCompra.isEmpty()) {
            DefaultTableModel tableModel = new DefaultTableModel();

            tableModel.addColumn("ID");
            tableModel.addColumn("Productos");
            tableModel.addColumn("Importe Total");
            tableModel.addColumn("Proveedor Asociado");

            for (OrdenCompraDTO oc : ordenesCompra) {
                List<String> productosNombres = new ArrayList<>();
                if (cuit.equals(oc.getProveedor().getCuit())) {
                    for (ItemProductoDTO items : oc.getItems()) {
                        productosNombres.add(items.getProducto().getNombre());
                    }
                    Object[] rowData = {
                            oc.getNroOrden(),
                            String.join(", ", productosNombres),
                            oc.getImporte(),
                            oc.getProveedor().getCuit()
                    };
                    tableModel.addRow(rowData);
                }
            }

            if (tableModel.getRowCount() > 0) {
                JTable tabla = new JTable(tableModel);

                JScrollPane scrollPane = new JScrollPane(tabla);
                getContentPane().add(scrollPane);

                setLayout(new GridLayout(1, 1));
                pack();
                setLocationRelativeTo(null);
                setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No hay Ordenes de Compra disponibles para el proveedor con CUIT: " + cuit, "Sin Ordenes de Compra", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No hay Ordenes de Compra disponibles.", "Sin Ordenes de Compra", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void mostrarTablaCompleta() {
        List<OrdenCompraDTO> ordenesCompra = controller.getAllOrdenesCompra();
        if (!ordenesCompra.isEmpty()) {
            DefaultTableModel tableModel = new DefaultTableModel();

            // Agregar las columnas al modelo;
            tableModel.addColumn("ID");
            tableModel.addColumn("Productos");
            tableModel.addColumn("Importe Total");
            tableModel.addColumn("Proovedor Asociado");

            for (OrdenCompraDTO oc : ordenesCompra) {
                List<String> productosNombres = new ArrayList<>();

                for (ItemProductoDTO items : oc.getItems()) {
                    productosNombres.add(items.getProducto().getNombre());
                }
                Object[] rowData = {
                        oc.getNroOrden(),
                        String.join(", ", productosNombres),
                        oc.getImporte(),
                        oc.getProveedor().getCuit()
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
            JOptionPane.showMessageDialog(this, "No hay Ordenes de Compra disponibles.", "Sin Ordenes de Compra", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}




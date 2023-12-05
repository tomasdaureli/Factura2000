package tablas;

import controller.Controller;
import dtos.ItemProductoDTO;
import dtos.OrdenCompraDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TablaLibroIVA extends JFrame {

    private Controller controller;

    public TablaLibroIVA(Controller controller) {
        super("Tabla de Libros IVA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.controller = controller;
    }

    public void mostrarTabla(String cuit) {
        List<OrdenCompraDTO> ordenesCompra = controller.getAllOrdenesCompra();

        if (!ordenesCompra.isEmpty()) {
            DefaultTableModel tableModel = new DefaultTableModel();

            tableModel.addColumn("Fecha");
            tableModel.addColumn("Cuit Proveedor");
            tableModel.addColumn("Nombre Proveedor");
            tableModel.addColumn("Descripcion");
            tableModel.addColumn("Precio Unitario");
            tableModel.addColumn("Cantidad");
            tableModel.addColumn("Iva");
            tableModel.addColumn("Precio Final");
            tableModel.addColumn("Documento");

            for (OrdenCompraDTO oc : ordenesCompra) {
                if (cuit.equals(oc.getProveedor().getCuit())) {
                    for (ItemProductoDTO item : oc.getItems()) {
                        Object[] rowData = {
                                oc.getFecha(),
                                item.getProducto().getProveedor().getCuit(),
                                item.getProducto().getProveedor().getNombreFantasia(),
                                item.getProducto().getNombre(),
                                item.getProducto().getPrecioUnitario(),
                                item.getCantidad(),
                                item.getProducto().getTipoIva(),
                                item.getCantidad() * item.getPrecio(),
                                "N/A",

                        };
                        tableModel.addRow(rowData);
                    }
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
                JOptionPane.showMessageDialog(this, "No hay Ordenes de Compra disponibles", "Sin Ordenes de Compra", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No existe tabla de IVA para el Proveedor " + cuit, "Sin Tabla IVA", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

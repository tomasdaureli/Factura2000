package tablas;

import controller.Controller;
import dtos.ProductoDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TablaProductos extends JFrame {

    public TablaProductos(Controller controller) {
        super("Tabla de Productos");
    }

    public void mostrarTabla(Controller controller) {
        List<ProductoDTO> productos = controller.getAllProductos();

        if (!productos.isEmpty()) {
            DefaultTableModel tableModel = new DefaultTableModel();

            tableModel.addColumn("Cuit Proovedor");
            tableModel.addColumn("ID");
            tableModel.addColumn("Nombre");
            tableModel.addColumn("Unidad");
            tableModel.addColumn("Precio Unitario");
            tableModel.addColumn("Tipo IVA");

            for (ProductoDTO prod : productos) {
                Object[] rowData = {
                        prod.getCuitProveedor(prod.getProveedor()),
                        prod.getId(),
                        prod.getNombre(),
                        prod.getTipoUnidad(),
                        prod.getPrecioUnitario(),
                        prod.getTipoIva()
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
            JOptionPane.showMessageDialog(this, "No hay Productos disponibles.", "Sin Productos", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}

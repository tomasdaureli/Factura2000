package tablas;

import controller.Controller;
import dtos.ProveedorDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TablaProveedores extends JFrame {

    private Controller controller;

    public TablaProveedores(Controller controller) {
        super("Tabla de Proovedores");
       // setDefaultCloseOperation();
        this.controller = controller;
    }

    public void mostrarTabla() {
        List<ProveedorDTO> proveedores = controller.getAllProveedores();
        DefaultTableModel tableModel = new DefaultTableModel();

        if (!proveedores.isEmpty()) {

            tableModel.addColumn("Cuit");
            tableModel.addColumn("Razón Social");
            tableModel.addColumn("Nombre Fantasía");
            tableModel.addColumn("Dirección");
            tableModel.addColumn("Teléfono");
            tableModel.addColumn("Correo Electrónico");
            tableModel.addColumn("Ingresos Brutos");
            tableModel.addColumn("Inicio Actividades");
            tableModel.addColumn("Condicion de IVA");
            tableModel.addColumn("Rubro");

            for (ProveedorDTO proveedor : proveedores) {
                Object[] rowData = {
                        proveedor.getCuit(),
                        proveedor.getRazonSocial(),
                        proveedor.getNombreFantasia(),
                        proveedor.getDireccion(),
                        proveedor.getTelefono(),
                        proveedor.getCorreoElectronico(),
                        proveedor.getIngresosBrutos(),
                        proveedor.getInicioActividades(),
                        proveedor.getIva(),
                        proveedor.getRubros(),
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
            JOptionPane.showMessageDialog(this, "No hay proveedores disponibles.", "Sin proveedores", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

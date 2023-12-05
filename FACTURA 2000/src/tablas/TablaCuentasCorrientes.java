package tablas;

import controller.Controller;
import model.CuentaCorriente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/*REVISAR FUNCIONA A UN 80%*/
public class TablaCuentasCorrientes extends JFrame {

    private Controller controller;

    public TablaCuentasCorrientes(Controller controller) {
        super("Tabla de Cuentas Corrientes");
        this.controller = controller;
    }

    public void mostrarTabla(String cuit) {
        CuentaCorriente cuentaCorriene = controller.getCuentaCorrienteByProveedor(controller.getProveedor(cuit));
        DefaultTableModel tableModel = new DefaultTableModel();

        tableModel.addColumn("Fecha");
        tableModel.addColumn("Motivo");
        tableModel.addColumn("Monto");
        tableModel.addColumn("Saldo");

        if (cuit.equals(cuentaCorriene.getProveedor().getCuit())) {
                Object[] rowData = {
                        cuentaCorriene.getProveedorCuit(controller.getProveedor(cuit)),
                        cuentaCorriene.getSaldo(), 
                };
                tableModel.addRow(rowData);
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
            JOptionPane.showMessageDialog(this, "No hay Cuentas Corrientes disponibles para el proveedor con CUIT: " + cuit, "Sin Ordenes de Compra", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}



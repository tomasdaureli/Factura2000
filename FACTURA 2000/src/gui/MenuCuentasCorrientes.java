package gui;


import controller.Controller;
import controller.GUIController;
import tablas.TablaCuentasCorrientes;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCuentasCorrientes extends JMenu {

    Controller controller = Controller.getInstance();
    GUIController GUIcontroller = GUIController.getGUIInstance();

    public MenuCuentasCorrientes() {
        super("Cuentas Corrientes");

        JMenuItem tableCC = new JMenuItem("Tabla de Cuentas Corrientes");
        add(tableCC);

        JMenuItem crearCC = new JMenuItem("Crear Cuenta Corriente");
        add(crearCC);
        tableCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cuit = null;
                JPanel panel = new JPanel();
                JTextField cuitDato = new JTextField(10);
                panel.add(cuitDato);
                TablaCuentasCorrientes tablaCC = new TablaCuentasCorrientes(controller);;
                int result = JOptionPane.showConfirmDialog(null, panel, "Ingrese CUIT del Proveedor",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                // System.out.println("ESTOY FUERA DEL IF");
                if (result == JOptionPane.OK_OPTION) {
                    cuit = cuitDato.getText();
                    GUIController.cargarCuentaCorrienteCompletaProovedor(controller,cuit);
                    tablaCC.mostrarTabla(cuit);
                }
            }
        });

        crearCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cuit = null;
                JPanel panel = new JPanel();
                JTextField cuitDato = new JTextField(10);
                panel.add(cuitDato);
                int result = JOptionPane.showConfirmDialog(null, panel, "Ingrese CUIT del Proveedor",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                // System.out.println("ESTOY FUERA DEL IF");
                if (result == JOptionPane.OK_OPTION) {
                    cuit = cuitDato.getText();
                    GUIController.cargarCuentaCorrienteCompletaProovedor(controller,cuit);
                }
            }
        });
    }


}
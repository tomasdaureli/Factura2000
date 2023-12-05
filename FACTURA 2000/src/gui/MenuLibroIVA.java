package gui;

import controller.Controller;
import tablas.TablaLibroIVA;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuLibroIVA extends JMenu {

    public MenuLibroIVA(Controller controller) {
        super("Libro de IVA");

        JMenuItem tableIVA = new JMenuItem("Tabla de IVA");
        add(tableIVA);
        tableIVA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cuit = null;
                JPanel panel = new JPanel();
                JTextField cuitDato = new JTextField(10);
                panel.add(cuitDato);
                TablaLibroIVA tablaLibroIVA = new TablaLibroIVA(controller);
                int result = JOptionPane.showConfirmDialog(null, panel, "Ingrese CUIT del Proveedor",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                // System.out.println("ESTOY FUERA DEL IF");
                if (result == JOptionPane.OK_OPTION) {
                    cuit = cuitDato.getText();
                    tablaLibroIVA.mostrarTabla(cuit);
                }

            }
        });
    }
}


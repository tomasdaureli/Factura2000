package gui;

import controller.Controller;
import tablas.TablaOC;
import tablas.TablaOP;
import tablas.TablaProductos;
import tablas.TablaProveedores;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerDatos extends JMenu {

    public VerDatos(Controller controller) {
        super("Ver Datos...");

        JMenuItem tableProv = new JMenuItem("Tabla de Proveedores");
        add(tableProv);

        JMenuItem tableOC = new JMenuItem("Tabla de Ordenes de Compra");
        add(tableOC);

        JMenuItem tableOP = new JMenuItem("Tabla de Ordenes de Pago");
        add(tableOP);


        JMenuItem tableProd = new JMenuItem("Tabla de Productos");
        add(tableProd);
        tableProv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TablaProveedores tablaProv = new TablaProveedores(controller);
                tablaProv.mostrarTabla();

            }
        });

        tableOC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TablaOC tablaOC = new TablaOC(controller);
                tablaOC.mostrarTablaCompleta();
            }
        });

        tableOP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TablaOP tablaOp = new TablaOP(controller);
                tablaOp.mostrarTablaCompleta();
            }
        });

        tableProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TablaProductos tablaProd = new TablaProductos(controller);
                tablaProd.mostrarTabla(controller);

            }
        });
    }
}

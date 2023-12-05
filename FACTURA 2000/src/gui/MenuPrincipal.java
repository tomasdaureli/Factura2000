package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.GUIController;
import controller.Controller;

class MenuPrincipal extends JMenu {

    Controller controller = Controller.getInstance();
    GUIController GUIcontroller = GUIController.getGUIInstance();


    public MenuPrincipal() {
        super("Menu");

        JMenuItem addProveedor = new JMenuItem("Añadir Proveedor");
        JMenuItem addProducto = new JMenuItem("Añadir Producto");
        JMenuItem generarCompra = new JMenuItem("Generar Orden de Compra");
        JMenuItem generarOrdenPago = new JMenuItem("Generar Orden de Pago");

        add(addProveedor);
        addProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIcontroller.cargarDatosProveedor(controller);

            }
        });

        add(addProducto);
        addProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIController.cargarProducto(controller);
            }
        });

        add(generarCompra);
        generarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIController.generarOrdenCompra();

            }
        });

        add(generarOrdenPago);
        generarOrdenPago.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIController.generarOrdenPago(controller);

            }
        });


    }
}

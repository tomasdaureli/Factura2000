package gui;

import controller.Controller;
import controller.GUIController;
import dtos.ItemProductoDTO;
import dtos.OrdenCompraDTO;
import dtos.ProductoDTO;
import mapper.MapperDTO;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class Seleccionar extends JFrame {

    private List<ItemProductoDTO> productosSeleccionados;

    private Controller controller = Controller.getInstance();
    private MapperDTO mapper = new MapperDTO();

    public List<ItemProductoDTO> AgregarProducto(String cuit, OrdenCompraDTO ordenCompra) { 

        List<ProductoDTO> productos = controller.getAllProductos();
        productosSeleccionados = new ArrayList<>();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        JComboBox<String> productosComboBox = new JComboBox<>(comboBoxModel);

        JButton agregarButton = new JButton("Agregar");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) productosComboBox.getSelectedItem();
                String[] parts = selectedItem.split(" - ");
                String productName = parts[0];
                double productPrice = Double.parseDouble(parts[1]);

                int cant = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad del Producto"));

                productosSeleccionados.add(mapper.toItemProductoDTO(controller.getProducto(productName), cant, productPrice));
            }
        });

        JButton continuarButton = new JButton("Continuar");
        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (ItemProductoDTO prod : productosSeleccionados) {
                    System.out.println("Producto: " + prod.getProducto().getNombre() + " | Cantidad :" + prod.getCantidad() + " | Precio : " + prod.getPrecio());
                }
                ordenCompra.setItems(productosSeleccionados);
                controller.generarOrdenCompra(cuit, ordenCompra.getItems());
                dispose();
            }
        });

        for (ProductoDTO prod : productos) {
            Proveedor proveedor = prod.getProveedor();
            String cuitProveedor = null;
            if (proveedor != null) {
                cuitProveedor = proveedor.getCuit();
            }

            assert cuitProveedor != null;
            if (cuitProveedor.equals(cuit)) {
                String infoProd = prod.getNombre() + " - " + prod.getPrecioUnitario();
                comboBoxModel.addElement(infoProd);
            }
        }

        JPanel panel = new JPanel();
        panel.add(new JLabel("Productos: "));
        panel.add(productosComboBox);
        panel.add(agregarButton);
        panel.add(continuarButton);

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        return productosSeleccionados;
    }

    private List<OrdenCompraDTO> todasLasOrdenes;
    private List<OrdenCompraDTO> ordenesProveedorSeleccionadas = new ArrayList<>();
    private JComboBox<String> ordenesCompraComboBox = new JComboBox<>();
    private OrdenCompraDTO ordencompraSeleccionadas;

    public OrdenCompraDTO seleccionarOrdenDeCompra(String cuit) {
        MapperDTO mapp = new MapperDTO();
        GUIController guiController = new GUIController();
        todasLasOrdenes = controller.getAllOrdenesCompra();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        ordenesCompraComboBox.setModel(comboBoxModel);

        for (OrdenCompraDTO orden : todasLasOrdenes) {
            if (orden.getProveedor().getCuit().equals(cuit) && !ordenesProveedorSeleccionadas.contains(orden)) {
                String infoProd = "ID" + " - " + orden.getNroOrden() + " - " + "$" + orden.getImporte() + " - " + orden.getFecha();
                comboBoxModel.addElement(infoProd);
            }
        }

        JButton seleccionarButton = new JButton("Seleccionar");
        JButton continuarButton = new JButton("Continuar");

        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ordenCompraSeleccionada = (String) ordenesCompraComboBox.getSelectedItem();
                String[] parts = ordenCompraSeleccionada.split(" - ");
                OrdenCompraDTO ordenCompraAux = controller.getOrdenCompraByNroOrden(Integer.parseInt(parts[1]));
                if (ordenCompraAux != null) {
                    ordencompraSeleccionadas = ordenCompraAux;
                    JOptionPane.showMessageDialog(null, "Orden de Compra seleccionada: ID --> " + ordencompraSeleccionadas.getNroOrden());
                }
            }
        });

        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ordencompraSeleccionadas != null) {
                    String pago = guiController.seleccionarFormaDePago(ordencompraSeleccionadas.getImporte(), mapp.toOrdenCompra(controller.getOrdenCompra(cuit)));
                    FormaPago formaPago = new FormaPago();
                    formaPago.setMetodoPago(pago);
                    System.out.println("-- Forma de Pago Seleccionada: " + formaPago.getMetodoPago());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna Orden de Compra", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Órdenes de Compra: "));
        panel.add(ordenesCompraComboBox);
        panel.add(seleccionarButton);
        panel.add(continuarButton);

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        return ordencompraSeleccionadas;
    }
}

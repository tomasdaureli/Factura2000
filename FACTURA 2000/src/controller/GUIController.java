package controller;

import dtos.*;
import gui.Seleccionar;
import mapper.MapperDTO;
import model.*;
import tablas.TablaProveedores;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GUIController {

    private static GUIController GUIinstance;

    public static GUIController getGUIInstance() {
        if (GUIinstance == null) {
            GUIinstance = new GUIController();
        }
        return GUIinstance;
    }

    Controller controller = Controller.getInstance();

    /*REVISAR PORQUE CC = NULL */
    public static void cargarCuentaCorrienteCompletaProovedor(Controller controller,String Cuit) {
        MapperDTO mapp = new MapperDTO();
        CuentaCorriente cc = new CuentaCorriente();
        List<OrdenCompraDTO> ordenCompraDTO = controller.getAllOrdenesCompraByProv(controller.getProveedor(Cuit));
        List<OrdenPagoDTO> ordenPagoDTO = controller.getAllOrdenesPagoByProv(controller.getProveedor(Cuit));

        List<OrdenCompra> ordenCompra = new ArrayList<>();
        List<OrdenPago> ordenPago = new ArrayList<>();

        for(OrdenCompraDTO ordendecompra : ordenCompraDTO) {
            ordenCompra.add(mapp.toOrdenCompra(ordendecompra));
        }

        for(OrdenPagoDTO ordendepago : ordenPagoDTO) {
            ordenPago.add(mapp.toOrdenPago(ordendepago));
        }

        controller.crearCuentaCorriente(controller.getProveedor(Cuit), ordenCompra, ordenPago, cc.getSaldo());

    }

    public void cargarDatosProveedor(Controller controller) {
        ProveedorDTO proveedorDTO = new ProveedorDTO();

        /*Si se toca la X o cnacelar cierra la ventana no hay verificacion :( */
        proveedorDTO.setCuit(JOptionPane.showInputDialog("Ingrese CUIT del Proveedor"));
        if (proveedorDTO.getCuit() == null) {return;}
        proveedorDTO.setIva(obtenerCondicionIVA());
        if (proveedorDTO.getIva() == null) {return;}
        proveedorDTO.setRazonSocial(JOptionPane.showInputDialog("Ingrese Razon Social"));
        if (proveedorDTO.getRazonSocial() == null) {return;}
        proveedorDTO.setNombreFantasia(JOptionPane.showInputDialog("Ingrese Nombre de Fantasia"));
        if (proveedorDTO.getNombreFantasia() == null) {return;}
        proveedorDTO.setTelefono(JOptionPane.showInputDialog("Ingrese numero de telefono"));
        if (proveedorDTO.getTelefono() == null) {return;}
        proveedorDTO.setDireccion(JOptionPane.showInputDialog("Ingrese el domiciolio/direccion"));
        if (proveedorDTO.getDireccion() == null) {return;}
        proveedorDTO.setCorreoElectronico(JOptionPane.showInputDialog("Ingrese el email"));
        if (proveedorDTO.getCorreoElectronico() == null) {return;}
        proveedorDTO.setIngresosBrutos(Double.parseDouble(JOptionPane.showInputDialog("Ingrese IIBB")));
        if (proveedorDTO.getIngresosBrutos() == 0) {return;}
        proveedorDTO.setRubros(JOptionPane.showInputDialog("Ingrese el rubro"));
        if (proveedorDTO.getRubros() == null) {return;}
        String inicioActividades = JOptionPane.showInputDialog("Ingrese la fecha de inicio de actividades (dd/MM/yyyy):");

        if (inicioActividades != null && !inicioActividades.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            try {
                LocalDate fecha = LocalDate.parse(inicioActividades, formatter);
                proveedorDTO.setInicioActividades(fecha);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Ingrese la fecha en formato dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            return;
        }

        controller.createProveedor(proveedorDTO.getCuit(), proveedorDTO.getIva(), proveedorDTO.getRazonSocial(), proveedorDTO.getNombreFantasia(), proveedorDTO.getDireccion(), proveedorDTO.getTelefono(), proveedorDTO.getCorreoElectronico(), proveedorDTO.getIngresosBrutos(), proveedorDTO.getInicioActividades(),proveedorDTO.getRubros());

        JOptionPane.showMessageDialog(null, "El proveedor fue cargado!", "Proveedor  : " + proveedorDTO.getNombreFantasia(), JOptionPane.INFORMATION_MESSAGE);

        System.out.println("Proveedor cargado: " + proveedorDTO.getCuit());
    }

    public static void generarOrdenPago(Controller controller) {
        Seleccionar seleccionar = new Seleccionar();
        TablaProveedores tablaProveedores = new TablaProveedores(controller);


        JPanel panel = new JPanel();
        JTextField cuitTextField = new JTextField(10);

        JButton btnVerProveedores = new JButton("Ver Proveedores");

        btnVerProveedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablaProveedores.mostrarTabla();
            }
        });


        panel.add(new JLabel("Ingrese el CUIT del Proveedor: "));
        panel.add(cuitTextField);
        panel.add(btnVerProveedores);

        int result = JOptionPane.showConfirmDialog(null, panel, "Ingrese CUIT del Proveedor",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
           String cuit = cuitTextField.getText();
           seleccionar.seleccionarOrdenDeCompra(cuit);
        }
    }

    public String seleccionarFormaDePago(double importe, OrdenCompra ordenCompra) {
        JPanel panel = new JPanel();
        String[] formasPago = {"Efectivo", "Cheque", "Otro"}; //Cambiar si es necesario

        JComboBox<String> formasPagoComboBox = new JComboBox<>(formasPago);
        panel.add(new JLabel("Seleccione la Forma de Pago:"));
        panel.add(formasPagoComboBox);


        int result = JOptionPane.showConfirmDialog(null, panel,
                "Seleccionar Forma de Pago - Debe Pagar: $" + importe, JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String formaPagoSeleccionada = (String) formasPagoComboBox.getSelectedItem();
            System.out.println("Forma de Pago Seleccionada: " + formaPagoSeleccionada);
            return formaPagoSeleccionada;
        } else {
            System.out.println("Selección Cancelada");
            return null;
        }
    }

    public static void generarOrdenCompra() {
        Seleccionar addProd = new Seleccionar();
        String cuit = null;
        OrdenCompraDTO ordenCompra = new OrdenCompraDTO();
        Controller controller = Controller.getInstance();
        TablaProveedores tablaProveedores = new TablaProveedores(controller);

        JPanel panel = new JPanel();  // Crear un panel para metere los componentes
        JTextField cuitDato = new JTextField(10);

        // Añadir el botón "Ver Proveedores"
        JButton btnVerProveedores = new JButton("Ver Proveedores");
        btnVerProveedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablaProveedores.mostrarTabla();

            }
        });

        panel.add(new JLabel("Ingrese el CUIT del Proveedor: "));
        panel.add(cuitDato);
        panel.add(btnVerProveedores);
        int result = -1;

        result = JOptionPane.showConfirmDialog(null, panel, "Ingrese CUIT del Proveedor",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        cuit = cuitDato.getText();

        while (controller.getProveedor(cuit) == null) {
            JOptionPane.showMessageDialog(null, "El proveedor no existe", "Error!", JOptionPane.INFORMATION_MESSAGE);
            result = JOptionPane.showConfirmDialog(null, panel, "Ingrese CUIT del Proveedor",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            cuit = cuitDato.getText();
        }

        // System.out.println("ESTOY FUERA DEL IF");
        if (result == JOptionPane.OK_OPTION) {
            //System.out.println("ESTOY EN EL IF");
            cuit = cuitDato.getText();
            addProd.AgregarProducto(cuit,ordenCompra);

        }

    }

    public static CondicionIVA obtenerCondicionIVA() {
        String[] condicionIVA = Arrays.stream(CondicionIVA.values()).map(Enum::name).toArray(String[]::new);

        List<String> opcionesIVA = Arrays.asList(condicionIVA);

        JComboBox<String> CondicionIVAComboBox = new JComboBox<>(opcionesIVA.toArray(new String[0]));
        int result = JOptionPane.showConfirmDialog(null, CondicionIVAComboBox, "Seleccione el Tipo de IVA",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        CondicionIVA condicionIVAElegidoenum = null;

        if (result == JOptionPane.OK_OPTION) {
            String condicionIVAElegido = (String) CondicionIVAComboBox.getSelectedItem();
            condicionIVAElegidoenum = CondicionIVA.valueOf(condicionIVAElegido);
        }

        return condicionIVAElegidoenum;
    }

    private static TipoIVA obtenerTipoIVA() {
        TipoIVA[] valoresIVA = TipoIVA.values();

        String[] opciones = new String[valoresIVA.length];
        for (int i = 0; i < valoresIVA.length; i++) {
            opciones[i] = valoresIVA[i].name();
        }

        JComboBox<String> comboBox = new JComboBox<>(opciones);

        int result = JOptionPane.showConfirmDialog(null, comboBox, "Seleccione el Tipo de IVA",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String tipoIVAElegido = (String) comboBox.getSelectedItem();

            return TipoIVA.valueOf(tipoIVAElegido);
        }

        return null;
    }

    private static TipoUnidad obtenerTipoUnidad() {
        TipoUnidad[] valoresUnidad = TipoUnidad.values();

        String[] opciones = new String[valoresUnidad.length];
        for (int i = 0; i < valoresUnidad.length; i++) {
            opciones[i] = valoresUnidad[i].name();
        }

        JComboBox<String> comboBox = new JComboBox<>(opciones);

        int result = JOptionPane.showConfirmDialog(null, comboBox, "Seleccione el Tipo de Unidad",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String tipoUnidadElegido = (String) comboBox.getSelectedItem();

            return TipoUnidad.valueOf(tipoUnidadElegido);
        }

        return null;
    }

    public static void cargarProducto(Controller controller) {

        ProductoDTO productoDTO = new ProductoDTO();
        MapperDTO mapperDTO = new MapperDTO();
        List<ProveedorDTO> listaProveedorDTO = new ArrayList<>();
        ProveedorDTO proveedorDTOSeleccionado = null;

        productoDTO.setNombre(JOptionPane.showInputDialog("Ingrese el Nombre del Producto"));
        if (productoDTO.getNombre() == null) {
            return;
        }

        listaProveedorDTO = controller.getAllProveedores();
        String[] nombresProveedores = listaProveedorDTO.stream().map(ProveedorDTO::getRazonSocial).toArray(String[]::new);
        JComboBox<String> proveedoresComboBox = new JComboBox<>(nombresProveedores);
        int result = JOptionPane.showConfirmDialog(
                null,
                proveedoresComboBox,
                "Seleccione un Proveedor",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String proveedorSeleccionado = (String) proveedoresComboBox.getSelectedItem();
            proveedorDTOSeleccionado = listaProveedorDTO.stream().filter(prov -> prov.getRazonSocial().equals(proveedorSeleccionado)).findFirst().orElse(null);

            productoDTO.setProveedor(mapperDTO.toProveedor(proveedorDTOSeleccionado));
            productoDTO.setPrecioUnitario(Double.parseDouble(JOptionPane.showInputDialog("Ingrese precio del Producto")));

            productoDTO.setTipoIva(obtenerTipoIVA());
            productoDTO.setTipoUnidad(obtenerTipoUnidad());

            controller.createProducto(productoDTO.getProveedor(), productoDTO.getNombre(), productoDTO.getTipoUnidad(), productoDTO.getPrecioUnitario(), productoDTO.getTipoIva());

            JOptionPane.showMessageDialog(null, "El producto fue cargado!", "Producto  : " + productoDTO.getNombre(), JOptionPane.INFORMATION_MESSAGE);

            System.out.println("Producto cargado: " + productoDTO.getNombre());
        }
    }
}
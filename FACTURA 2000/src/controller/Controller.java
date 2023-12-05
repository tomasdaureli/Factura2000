package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dtos.*;
import mapper.*;
import model.*;

public class Controller {

    // Listas donde persistira la informacion
    private List<Proveedor> proveedores;
    private List<Producto> productos ;
    private List<OrdenCompra> ordenesCompra;
    private List<OrdenPago> ordenesPago;
    private List<Factura> facturas;
    private List<NotaCredito> notasCredito;
    private List<NotaDebito> notasDebito;
    private List<CuentaCorriente> cuentaCorrientes;

    // dtoMapper para pasar de la entidad a dto
    private MapperDTO dtoMapper;

    private static Controller instance;

    public Controller() {
        this.proveedores = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.dtoMapper = new MapperDTO();
        this.ordenesCompra = new ArrayList<>();
        this.ordenesPago = new ArrayList<>();
        this.cuentaCorrientes = new ArrayList<>();
        this.facturas = new ArrayList<>();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    
    public ProveedorDTO createProveedor(String cuit, CondicionIVA iva, String razonSocial, String nombreFantasia,
            String direccion, String telefono, String correoElectronico, double iibb, LocalDate inicioActividades,String rubro) {

        Proveedor proveedor = new Proveedor(cuit, iva, razonSocial, nombreFantasia, direccion, telefono, correoElectronico, iibb, inicioActividades, rubro);

        proveedores.add(proveedor);

       return dtoMapper.toProveedorDTO(proveedor);
    }

    public Proveedor getProveedor(String cuit) {
        for (Proveedor p : proveedores) {
            if (p.getCuit().equals(cuit)) {
                return p;
            }
        }
        return null;
    }

    public List<ProveedorDTO> getAllProveedores() {
        List<ProveedorDTO> proveedoresDto = new ArrayList<>();
        for (Proveedor p : proveedores) {
            proveedoresDto.add(dtoMapper.toProveedorDTO(p));
        }
        return proveedoresDto;
    }

    public ProductoDTO createProducto(Proveedor proveedor, String name,TipoUnidad tipoUnidad,
            double precioXUnidad, TipoIVA iva) {

        
        Producto producto = new Producto(proveedor, name, tipoUnidad, precioXUnidad, iva);

        productos.add(producto);
        return dtoMapper.toProductoDTO(producto);
    }

    public ProductoDTO getProducto(String name) {
        for (Producto producto : productos) {
            if (producto.getNombre().equals(name)) {
                return dtoMapper.toProductoDTO(producto);
            }
        }
        return null;
    }

    public List<ProductoDTO> getAllProductos() {
        List<ProductoDTO> prodDto = new ArrayList<>();
        for (Producto producto : productos) {
            prodDto.add(dtoMapper.toProductoDTO(producto));
        }
        return prodDto;
    }

    public OrdenCompraDTO generarOrdenCompra(String cuit, List<ItemProductoDTO> productosSeleccionados) {
        OrdenCompra ordenCompra = new OrdenCompra();
        Proveedor proveedor = getProveedor(cuit);

        for (ItemProductoDTO producto : productosSeleccionados) {
            ordenCompra.addItem(dtoMapper.toProducto(producto.getProducto()), producto.getCantidad(), producto.getPrecio(), cuit);
        }

        double total = ordenCompra.calcularTotal(ordenCompra.getItems());
        System.out.println("TOTAL" + total);
        ordenCompra.setImporte(total);
        ordenCompra.setProveedor(proveedor);

        ordenesCompra.add(ordenCompra);

        CuentaCorriente cc = getCuentaCorrienteByProveedor(proveedor);
        if (cc != null) {
            cc.cargarCompra(ordenCompra);
        }

        for (OrdenCompra oc : ordenesCompra) {
            System.out.println("CONTENIDO LISTA OC");
            System.out.println(oc.getNroOrden());
            System.out.println(oc.getProveedor().getCuit());
            System.out.println(oc.getImporte());
            System.out.println(oc.getItems());
        }

        return dtoMapper.toOrdenCompraDTO(ordenCompra);
    }

    public OrdenCompraDTO getOrdenCompra(String Cuit) {
        for (OrdenCompra orden : ordenesCompra) {
            if (orden.getProveedor().getCuit().equals(Cuit)) {
                return dtoMapper.toOrdenCompraDTO(orden);
            }
        }
        return null;
    }

    public OrdenCompraDTO getOrdenCompraByNroOrden(int nroOrden) {
        for (OrdenCompra orden : ordenesCompra) {
            if (orden.getNroOrden() == nroOrden) {
                return dtoMapper.toOrdenCompraDTO(orden);
            }
        }
        return null;
    }

    public List<OrdenCompraDTO> getAllOrdenesCompra() {
        List<OrdenCompraDTO> ordenesDto = new ArrayList<>();
        if (ordenesCompra != null) {
            for (OrdenCompra orden : ordenesCompra) {
                ordenesDto.add(dtoMapper.toOrdenCompraDTO(orden));
            }
        }
        return ordenesDto;
    }

    public List<OrdenCompraDTO> getAllOrdenesCompraByProv(Proveedor proveedor) {
        List<OrdenCompraDTO> ordenesDto = new ArrayList<>();
        if (ordenesCompra != null) {
            for (OrdenCompra orden : ordenesCompra) {
                if(orden.getProveedor().getCuit().equals(proveedor.getCuit()))
                    ordenesDto.add(dtoMapper.toOrdenCompraDTO(orden));
            }
        }
        return ordenesDto;
    }
    public Factura generarFactura(OrdenCompra compra) {
        Factura factura = new Factura(compra, compra.getItems(), compra.getProveedor().getCuit());
        facturas.add(factura);
        return factura;
    }

    public List<Factura> getAllFacturas() {
        return facturas;
    }

    public Factura getFacturaByNro(Long nroFactura) {
        for (Factura f : facturas) {
            if (f.getNroFactura().equals(nroFactura)) {
                return f;
            }
        }
        return null;
    }

    public Factura getFacturaByCuit(String cuit) {
        for (Factura f : facturas) {
            if (f.getCuitProovedorAsociado().equals(cuit)) {
                return f;
            }
        }
        return null;
    }

    public NotaCredito generarNotaCredito(List<Producto> productos, LocalDate fechaEmision, double importe) {
        NotaCredito nota = new NotaCredito(productos, fechaEmision, importe);
        notasCredito.add(nota);
        return nota;
    }

    public List<NotaCredito> getAllNotasCredito() {
        return notasCredito;
    }

    public NotaCredito getNotaCreditoByNro(Long nroNota) {
        for (NotaCredito nc : notasCredito) {
            if (nc.getNroNotaCredito().equals(nroNota)) {
                return nc;
            }
        }
        return null;
    }

    public NotaDebito generarNotaDebito(List<Producto> productos, LocalDate fechaEmision, double importe) {
        NotaDebito nota = new NotaDebito(productos, fechaEmision, importe);
        notasDebito.add(nota);
        return nota;
    }

    public List<NotaDebito> getAllNotasDebito() {
        return notasDebito;
    }

    public NotaDebito getNotaDebitoByNro(Long nroNota) {
        for (NotaDebito nd : notasDebito) {
            if (nd.getNroNotaDebito().equals(nroNota)) {
                return nd;
            }
        }
        return null;
    }

    public CuentaCorrienteDTO crearCuentaCorriente(Proveedor proveedor, List<OrdenCompra> compras,
            List<OrdenPago> pagos, double saldo) {
        CuentaCorriente cuentaCorriente = new CuentaCorriente(proveedor, compras, pagos, saldo);

        cuentaCorrientes.add(cuentaCorriente);
        return dtoMapper.toCuentaCorrienteDTO(cuentaCorriente);
    }

    public CuentaCorriente getCuentaCorrienteByProveedor(Proveedor proveedor) {

        if (proveedor != null) {
            for (CuentaCorriente cc : cuentaCorrientes) {
                if (cc.getProveedorCuit(proveedor).equals(proveedor.getCuit())) {
                    return cc;
                }
            }

        }
        return null;
    }

    public List<CuentaCorrienteDTO> getAllCuentaCorriente() {
        List<CuentaCorrienteDTO> cuentaCorr = new ArrayList<>();
        if (cuentaCorrientes != null) {
            for (CuentaCorriente cc : cuentaCorrientes) {
                cuentaCorr.add(dtoMapper.toCuentaCorrienteDTO(cc));
                }
            }

        return cuentaCorr;
    }

    public OrdenPagoDTO crearOrdenPago(Proveedor proveedor, double importe, FormaPago formaPago,
            double totalRetenciones, Documento tipoDocumento) {
        OrdenPago ordenPago = new OrdenPago(importe, formaPago, totalRetenciones, tipoDocumento, proveedor);

        ordenesPago.add(ordenPago);

        CuentaCorriente cc = getCuentaCorrienteByProveedor(proveedor);

        if (cc != null) {
            cc.cargarPago(ordenPago);
        }

        return dtoMapper.toOrdenPagoDTO(ordenPago);
    }

    public List<OrdenPagoDTO> getAllOrdenespago() {
        List<OrdenPagoDTO> ordenesDto = new ArrayList<>();
        if (ordenesPago != null) {
            for (OrdenPago orden : ordenesPago) {
                ordenesDto.add(dtoMapper.toOrdenPagoDTO(orden));
            }
        }
        return ordenesDto;
    }

    public List<OrdenPagoDTO> getAllOrdenesPagoByProv(Proveedor proveedor) {
        List<OrdenPagoDTO> ordenesDto = new ArrayList<>();
        if (ordenesPago != null) {
            for (OrdenPago orden : ordenesPago) {
                if(orden.getProveedor().getCuit().equals(proveedor.getCuit()))
                    ordenesDto.add(dtoMapper.toOrdenPagoDTO(orden));
            }
        }
        return ordenesDto;
    }

}
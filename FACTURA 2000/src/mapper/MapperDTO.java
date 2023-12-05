package mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controller.Controller;
import dtos.*;
import model.*;


public class MapperDTO {


    public ItemProductoDTO toItemProductoDTO(ProductoDTO source, int cantidad, double precio) {
        ItemProductoDTO itemProducto = new ItemProductoDTO();
        Controller controller = Controller.getInstance();

        itemProducto.setProducto(controller.getProducto(source.getNombre()));
        itemProducto.setPrecio(source.getPrecioUnitario());
        itemProducto.setCantidad(cantidad);
        itemProducto.setPrecio(precio);

        return itemProducto;
    }

    public ProveedorDTO toProveedorDTO(Proveedor source) {

        if (source == null) {
            return null;
        }

        ProveedorDTO dto = new ProveedorDTO();

        dto.setId(source.getId());
        dto.setCuit(source.getCuit());
        dto.setIva(source.getIva());
        dto.setRazonSocial(source.getRazonSocial());
        dto.setNombreFantasia(source.getNombreFantasia());
        dto.setDireccion(source.getDireccion());
        dto.setTelefono(source.getTelefono());
        dto.setCorreoElectronico(source.getCorreoElectronico());
        dto.setIngresosBrutos(source.getIngresosBrutos());
        dto.setInicioActividades(source.getInicioActividades());
        dto.setRubros(source.getRubros());

        return dto;
    }

    public Proveedor toProveedor(ProveedorDTO source) {
        Proveedor dto = new Proveedor(source.getCuit(),source.getIva(),source.getRazonSocial(),source.getNombreFantasia(),source.getDireccion(),source.getTelefono(),source.getCorreoElectronico(),source.getIngresosBrutos(),source.getInicioActividades(),source.getRubros());

        dto.setCuit(source.getCuit());
        dto.setIva(source.getIva());
        dto.setRazonSocial(source.getRazonSocial());
        dto.setNombreFantasia(source.getNombreFantasia());
        dto.setDireccion(source.getDireccion());
        dto.setTelefono(source.getTelefono());
        dto.setCorreoElectronico(source.getCorreoElectronico());
        dto.setIngresosBrutos(source.getIngresosBrutos());
        dto.setInicioActividades(source.getInicioActividades());

        return dto;
    }

    public ProductoDTO toProductoDTO(Producto source) {
        if (source == null) {
            return null;
        }

        ProductoDTO dto = new ProductoDTO();

        dto.setId(source.getId());
        dto.setProveedor(source.getProveedor());
        dto.setNombre(source.getNombre());
        dto.setTipoUnidad(source.getTipoUnidad());
        dto.setPrecioUnitario(source.getPrecioUnitario());
        dto.setTipoIva(source.getTipoIva());

        return dto;
    }

    public Producto toProducto(ProductoDTO source) {
        if (source == null) {
            return null;
        }

        Producto dto = new Producto();

        dto.setNombre(source.getNombre());
        dto.setProveedor(source.getProveedor());
        dto.setNombre(source.getNombre());
        dto.setTipoUnidad(source.getTipoUnidad());
        dto.setPrecioUnitario(source.getPrecioUnitario());
        dto.setTipoIva(source.getTipoIva());

        return dto;
    }


    public OrdenCompraDTO toOrdenCompraDTO(OrdenCompra source) {
        OrdenCompraDTO dto = new OrdenCompraDTO();

        if (source.getItems() != null) {
            List<ItemProductoDTO> items = new ArrayList<>();
            for (ItemProducto item : source.getItems()) {
                items.add(toItemProductoDTO(item));
            }
            dto.setItems(items);
        }

        dto.setNroOrden(source.getNroOrden());
        dto.setImporte(source.getImporte());
        dto.setFecha(source.getFecha());
        dto.setProveedor(this.toProveedorDTO(source.getProveedor()));

        return dto;
    }

    public OrdenCompra toOrdenCompra(OrdenCompraDTO source) {

        if(source == null){
            return null;
        }

        OrdenCompra model = new OrdenCompra();

        if (source.getItems() != null) {
            List<ItemProducto> items = new ArrayList<>();
            for (ItemProductoDTO item : source.getItems()) {
                items.add(toItemProducto(item));
            }
            model.setItems(items);
        }
        model.setImporte(source.getImporte());
        model.setFecha(source.getFecha());
        model.setProveedor(toProveedor(source.getProveedor()));

        return model;
    }

    private ItemProducto toItemProducto(ItemProductoDTO source) {
        ItemProducto model = new ItemProducto();

        model.setProducto(toProducto(source.getProducto()));
        model.setPrecio(source.getPrecio());
        model.setCantidad(source.getCantidad());

        return model;
    }


    public ItemProductoDTO toItemProductoDTO(ItemProducto source) {
        ItemProductoDTO dto = new ItemProductoDTO();

        dto.setProducto(toProductoDTO(source.getProducto()));
        dto.setCantidad(source.getCantidad());
        dto.setPrecio(source.getPrecio());

        return dto;
    }

    public CuentaCorrienteDTO toCuentaCorrienteDTO(CuentaCorriente source) {
        CuentaCorrienteDTO dto = new CuentaCorrienteDTO();

        dto.setProveedor(toProveedorDTO(source.getProveedor()));
        List<OrdenCompraDTO> compras = new ArrayList<>();
        for (OrdenCompra compra : source.getCompras()) {
            compras.add(toOrdenCompraDTO(compra));
        }
        dto.setCompras(compras);
        List<OrdenPagoDTO> pagos = new ArrayList<>();
        for (OrdenPago pago : source.getPagos()) {
            pagos.add(toOrdenPagoDTO(pago));
        }
        dto.setPagos(pagos);
        dto.setSaldo(source.getSaldo());
        if (source.getDocumentos() != null) {
            List<DocumentoDTO> docs = new ArrayList<>();
            for (Documento doc : source.getDocumentos()) {
                docs.add(toDocumentoDTO(doc));
            }
            dto.setDocumentos(docs);
        }else {
            dto.setDocumentos(Collections.emptyList());
        }
        return dto;
    }

    public OrdenPagoDTO toOrdenPagoDTO(OrdenPago source) {
        OrdenPagoDTO dto = new OrdenPagoDTO();

        dto.setProveedor(toProveedorDTO(source.getProveedor()));
        dto.setNroOrden(source.getNroOrden());
        dto.setTotalPagar(source.getTotalPagar());
        dto.setFormaPago(toFormaPagoDTO(source.getFormaPago()));
        dto.setTotalRetenciones(source.getTotalRetenciones());
        dto.setTipoDocumento(toDocumentoDTO(source.getTipoDocumento()));

        return dto;
    }

    public OrdenPago toOrdenPago(OrdenPagoDTO source) {
        OrdenPago model = new OrdenPago();

        model.setNroOrden(source.getNroOrden());
        model.setProveedor(toProveedor(source.getProveedor()));
        model.setFormaPago(toFormaPago(source.getFormaPago()));
        model.setTotalPagar(source.getTotalPagar());
        model.setTotalRetenciones(source.getTotalRetenciones());

        return model;
    }

    public FormaPagoDTO toFormaPagoDTO(FormaPago source) {
        if (source == null) {
            return null;
        }

        FormaPagoDTO dto = new FormaPagoDTO();

        dto.setImporte(source.getImporte());
        if (Cheque.class.equals(source.getClass())) {
            dto.setNroCheque(((Cheque) source).getNroCheque());
            dto.setEmision(((Cheque) source).getEmision());
            dto.setVencimiento(((Cheque) source).getVencimiento());
            dto.setFirmante(((Cheque) source).getFirmante());
        }

        return dto;
    }

    public FormaPago toFormaPago(FormaPagoDTO source) {
        if (source == null) {
            return null;
        }

        FormaPago noDto = new FormaPago();

        noDto.setImporte(source.getImporte());
        noDto.setMetodoPago(source.getMetodo());


        return noDto;
    }


    public DocumentoDTO toDocumentoDTO(Documento source) {

        if (source == null) {
            return null; }

        DocumentoDTO dto = new DocumentoDTO();

        dto.setProveedor(toProveedorDTO(source.getProveedor()));
        if (Factura.class.equals(source.getClass())) {
            dto.setNroFactura(((Factura) source).getNroFactura());
            dto.setOrdenCompra(toOrdenCompraDTO(((Factura) source).getOrdenCompra()));
            List<ItemProductoDTO> items = new ArrayList<>();
            for (ItemProducto item : ((Factura) source).getItems()) {
                items.add(toItemProductoDTO(item));
            }
            dto.setItems(items);
        }
        if (NotaCredito.class.equals(source.getClass())) {
            dto.setNroNotaCredito(((NotaCredito) source).getNroNotaCredito());
            List<ProductoDTO> productos = new ArrayList<>();
            for (Producto producto : ((NotaCredito) source).getProductos()) {
                productos.add(toProductoDTO(producto));
            }
            dto.setProductos(productos);
            dto.setFechaEmision(((NotaCredito) source).getFechaEmision());
            dto.setImporte(((NotaCredito) source).getImporte());
        }
        if (NotaDebito.class.equals(source.getClass())) {
            dto.setNroNotaDebito(((NotaDebito) source).getNroNotaDebito());
            List<ProductoDTO> productos = new ArrayList<>();
            for (Producto producto : ((NotaDebito) source).getProductos()) {
                productos.add(toProductoDTO(producto));
            }
            dto.setProductos(productos);
            dto.setFechaEmision(((NotaDebito) source).getFechaEmision());
            dto.setImporte(((NotaDebito) source).getImporte());
        }

        return dto;
    }
}

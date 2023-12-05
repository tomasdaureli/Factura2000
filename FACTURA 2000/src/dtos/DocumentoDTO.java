package dtos;

import java.time.LocalDate;
import java.util.List;

public class DocumentoDTO {

    private ProveedorDTO proveedor;

    // Factura
    private Long nroFactura;
    private OrdenCompraDTO ordenCompra;
    private List<ItemProductoDTO> items;

    // Nota
    private Long nroNotaCredito;
    private Long nroNotaDebito;
    private List<ProductoDTO> productos;
    private LocalDate fechaEmision;
    private double importe;

    public DocumentoDTO() {
    }
    public ProveedorDTO getProveedor() {
        return proveedor;
    }
    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }
    public Long getNroFactura() {
        return nroFactura;
    }
    public void setNroFactura(Long nroFactura) {
        this.nroFactura = nroFactura;
    }
    public OrdenCompraDTO getOrdenCompra() {
        return ordenCompra;
    }
    public void setOrdenCompra(OrdenCompraDTO ordenCompra) {
        this.ordenCompra = ordenCompra;
    }
    public List<ItemProductoDTO> getItems() {
        return items;
    }
    public void setItems(List<ItemProductoDTO> items) {
        this.items = items;
    }
    public Long getNroNotaCredito() {
        return nroNotaCredito;
    }
    public void setNroNotaCredito(Long nroNotaCredito) {
        this.nroNotaCredito = nroNotaCredito;
    }
    public Long getNroNotaDebito() {
        return nroNotaDebito;
    }
    public void setNroNotaDebito(Long nroNotaDebito) {
        this.nroNotaDebito = nroNotaDebito;
    }
    public List<ProductoDTO> getProductos() {
        return productos;
    }
    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }
    public LocalDate getFechaEmision() {
        return fechaEmision;
    }
    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
    public double getImporte() {
        return importe;
    }
    public void setImporte(double importe) {
        this.importe = importe;
    }

}

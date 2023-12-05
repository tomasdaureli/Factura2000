package dtos;

public class OrdenPagoDTO {

    private Long nroOrden;
    private ProveedorDTO proveedor;
    private double totalPagar;
    private FormaPagoDTO formaPago;
    private double totalRetenciones;
    private DocumentoDTO tipoDocumento;

    public OrdenPagoDTO() {
    }
    public Long getNroOrden() {
        return nroOrden;
    }
    public ProveedorDTO getProveedor() {
        return proveedor;
    }
    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }
    public void setNroOrden(Long nroOrden) {
        this.nroOrden = nroOrden;
    }
    public double getTotalPagar() {
        return totalPagar;
    }
    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }
    public FormaPagoDTO getFormaPago() {
        return formaPago;
    }
    public void setFormaPago(FormaPagoDTO formaPago) {
        this.formaPago = formaPago;
    }
    public double getTotalRetenciones() {
        return totalRetenciones;
    }
    public void setTotalRetenciones(double totalRetenciones) {
        this.totalRetenciones = totalRetenciones;
    }
    public DocumentoDTO getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(DocumentoDTO tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

}

package dtos;

import java.util.List;

public class CuentaCorrienteDTO {
    
    private ProveedorDTO proveedor;
    private List<OrdenCompraDTO> compras;
    private List<OrdenPagoDTO> pagos;
    private double saldo;
    private List<DocumentoDTO> documentos;

    public double getDeuda() {
        return deuda;
    }

    public void setDeuda(double deuda) {
        this.deuda = deuda;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    private double deuda;

    private double pago;
    
    public CuentaCorrienteDTO() {
    }
    public ProveedorDTO getProveedor() {
        return proveedor;
    }
    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }
    public List<OrdenCompraDTO> getCompras() {
        return compras;
    }
    public void setCompras(List<OrdenCompraDTO> compras) {
        this.compras = compras;
    }
    public List<OrdenPagoDTO> getPagos() {
        return pagos;
    }
    public void setPagos(List<OrdenPagoDTO> pagos) {
        this.pagos = pagos;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public List<DocumentoDTO> getDocumentos() {
        return documentos;
    }
    public void setDocumentos(List<DocumentoDTO> documentos) {
        this.documentos = documentos;
    }
}

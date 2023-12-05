package model;

import java.util.List;

public class CuentaCorriente {

    private Proveedor proveedor;
    private List<OrdenCompra> compras;
    private List<OrdenPago> pagos;
    private double saldo;
    private List<Documento> documentos;


    public CuentaCorriente(Proveedor proveedor, List<OrdenCompra> compras, List<OrdenPago> pagos,
        double saldo) {
        this.proveedor = proveedor;
        this.compras = compras;
        this.pagos = pagos;
        this.saldo = saldo;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public CuentaCorriente() {
    }

    public Proveedor getProveedor() {
        return proveedor;
    }
    public String getProveedorCuit(Proveedor proveedor) {
        return proveedor.getCuit();
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<OrdenCompra> getCompras() {
        return compras;
    }

    public List<OrdenPago> getPagos() {
        return pagos;
    }

    public double getSaldo() {
        return saldo;
    }

    public double cargarCompra(OrdenCompra ordenCompra) {
        compras.add(ordenCompra);
        double Deuda = getSaldo() + ordenCompra.getImporte();
        return Deuda;
    }

    public double cargarPago(OrdenPago ordenPago) {
        pagos.add(ordenPago);
        double Pago = getSaldo() - ordenPago.getTotalPagar();
        return Pago;
    }

}

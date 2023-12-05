package model;

import java.util.List;

public class Factura extends Documento {

    private static Long contador = 0L;
    private Long nroFactura;
    private OrdenCompra ordenCompra;
    private List<ItemProducto> items;

    public String getCuitProovedorAsociado() {
        return cuitProovedorAsociado;
    }

    public void setCuitProovedorAsociado(String cuitProovedorAsociado) {
        this.cuitProovedorAsociado = cuitProovedorAsociado;
    }

    private String cuitProovedorAsociado;

    public Factura(OrdenCompra ordenCompra, List<ItemProducto> items,String cuitProovedorAsociado) {
        contador++;
        nroFactura = contador;
        this.ordenCompra = ordenCompra;
        this.items = items;
        this.cuitProovedorAsociado = cuitProovedorAsociado;
    }

    public Long getNroFactura() {
        return nroFactura;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public List<ItemProducto> getItems() {
        return items;
    }

    public void setItems(List<ItemProducto> items) {
        this.items = items;
    }
    
}

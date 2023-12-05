package dtos;

import model.TipoIVA;

public class ItemProductoDTO {

    private ProductoDTO producto;
    private int cantidad;
    private double precio;

    private String cuit;

    private TipoIVA iva;

    public TipoIVA getIva() {
        return iva;
    }

    public void setIva(TipoIVA iva) {
        this.iva = iva;
    }

    public ItemProductoDTO() {
    }
    public String getCuit() {return cuit;}

    public void setCuit(String cuit) {this.cuit = cuit;}
    public ProductoDTO getProducto() {
        return producto;
    }
    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

}

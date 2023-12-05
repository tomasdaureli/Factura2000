package dtos;

import model.Proveedor;
import model.TipoIVA;
import model.TipoUnidad;

public class ProductoDTO {

    private Long id;
    private Proveedor proveedor;
    private String nombre;
    private TipoUnidad tipoUnidad;
    private double precioUnitario;
    private TipoIVA tipoIva;

    public ProductoDTO() {
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Proveedor getProveedor() {
        return proveedor;
    }
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public TipoUnidad getTipoUnidad() {
        return tipoUnidad;
    }
    public void setTipoUnidad(TipoUnidad tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }
    public double getPrecioUnitario() {
        return precioUnitario;
    }
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    public TipoIVA getTipoIva() {
        return tipoIva;
    }
    public void setTipoIva(TipoIVA tipoIva) {
        this.tipoIva = tipoIva;
    }

    public String getCuitProveedor(Proveedor proveedor){
        if (proveedor != null) {
            return proveedor.getCuit();
        } else {
            return null;
        }
    }
    
}

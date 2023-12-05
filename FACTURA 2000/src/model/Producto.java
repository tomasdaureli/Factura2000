package model;

public class Producto {

    private static Long contador = 0L;
    private Long id = 0L;
    private Proveedor proveedor;
    private String nombre;
    private TipoUnidad tipoUnidad;
    private double precioUnitario;
    private TipoIVA tipoIva;

    public Producto(Proveedor proveedor, String nombre, TipoUnidad tipoUnidad, double precioUnitario,
            TipoIVA tipoIva) {
        contador++;
        id = contador;
        this.proveedor = proveedor;
        this.nombre = nombre;
        this.tipoUnidad = tipoUnidad;
        this.precioUnitario = precioUnitario;
        this.tipoIva = tipoIva;
    }

    public Producto() {

    }

    public Long getId() {
        return id;
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

}

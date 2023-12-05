package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdenCompra {

    private static int contador = 0;
    private int nroOrden;
    private LocalDate fecha;
    private Proveedor proveedor;
    private List<ItemProducto> items;
    private double importe;

    public OrdenCompra() {
        contador++;
        this.nroOrden = contador;
        this.fecha = LocalDate.now();
    }

    public int getNroOrden() {
        return nroOrden;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<ItemProducto> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }

    public void setItems(List<ItemProducto> items) {
        this.items = items;
    }

    public ItemProducto addItem(Producto producto, int cantidad, double precioUnitario,String Cuit) {
        ItemProducto item = new ItemProducto();

        item.setProducto(producto);
        item.setCantidad(cantidad);
        item.setPrecio(precioUnitario);

        getItems().add(item);

        return item;
    }

    public double calcularTotal(List<ItemProducto> items) {
        double total = 0;

        if (items != null) {
            for (ItemProducto i : items) {
                double cantidad = i.getCantidad();
                double precioUnitario = i.getPrecio();
                total = total + (cantidad * precioUnitario);
            }
        }
        return total;
    }

}

package dtos;

import java.time.LocalDate;
import java.util.List;

public class OrdenCompraDTO {

    private int nroOrden;
    public LocalDate fecha;
    private ProveedorDTO proveedor;
    private List<ItemProductoDTO> items;
    private double importe;

    public OrdenCompraDTO() {
    }

    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }


    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setNroOrden(int id){this.nroOrden = id;}
    public int getNroOrden() {
        return nroOrden;
    }

    public List<ItemProductoDTO> getItems() {
        return items;
    }
    public void setItems(List<ItemProductoDTO> items) {
        this.items = items;
    }
    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

}

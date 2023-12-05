package model;

import java.time.LocalDate;
import java.util.List;

public class NotaDebito extends Documento {

    private static Long contador = 0L;
    private Long nroNotaDebito;
    private List<Producto> productos;
    private LocalDate fechaEmision;
    private double importe;

    public NotaDebito(List<Producto> productos, LocalDate fechaEmision, double importe) {
        contador++;
        nroNotaDebito = contador;
        this.productos = productos;
        this.fechaEmision = fechaEmision;
        this.importe = importe;
    }
    
    public Long getNroNotaDebito() {
        return nroNotaDebito;
    }
    public void setNroNotaDebito(Long nroNotaDebito) {
        this.nroNotaDebito = nroNotaDebito;
    }
    public List<Producto> getProductos() {
        return productos;
    }
    public void setProductos(List<Producto> productos) {
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

package model;

import java.time.LocalDate;
import java.util.List;

public class NotaCredito extends Documento {

    private static Long contador = 0L;
    private Long nroNotaCredito;
    private List<Producto> productos;
    private LocalDate fechaEmision;
    private double importe;
    
    public NotaCredito(List<Producto> productos, LocalDate fechaEmision, double importe) {
        contador++;
        nroNotaCredito = contador;
        this.productos = productos;
        this.fechaEmision = fechaEmision;
        this.importe = importe;
    }
    
    public Long getNroNotaCredito() {
        return nroNotaCredito;
    }
    public void setNroNotaCredito(Long nroNotaCredito) {
        this.nroNotaCredito = nroNotaCredito;
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

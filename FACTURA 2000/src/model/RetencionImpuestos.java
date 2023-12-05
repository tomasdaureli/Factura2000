package model;

public class RetencionImpuestos {
    
    private String tipoImpuesto;
    private double porcentaje;
    private Proveedor proveedor;

    public String getTipoImpuesto() {
        return tipoImpuesto;
    }
    public void setTipoImpuesto(String tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }
    public double getPorcentaje() {
        return porcentaje;
    }
    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
    public Proveedor getProveedor() {
        return proveedor;
    }
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

}

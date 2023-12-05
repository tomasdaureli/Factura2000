package model;

import java.time.LocalDate;

public class CertificadoNoRetencion {
    
    private RetencionImpuestos retencionImpositiva;
    private LocalDate fecha;
    
    public CertificadoNoRetencion(RetencionImpuestos retencionImpositiva, LocalDate fecha) {
        this.retencionImpositiva = retencionImpositiva;
        this.fecha = fecha;
    }
    public RetencionImpuestos getRetencionImpositiva() {
        return retencionImpositiva;
    }
    public void setRetencionImpositiva(RetencionImpuestos retencionImpositiva) {
        this.retencionImpositiva = retencionImpositiva;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}

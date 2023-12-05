package dtos;

import java.time.LocalDate;

public class FormaPagoDTO {

    private double importe;

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    private String metodo;

    // Cheque
    private Long nroCheque;
    private LocalDate emision;
    private LocalDate vencimiento;
    private String firmante;

    public FormaPagoDTO() {
    }
    public double getImporte() {
        return importe;
    }
    public void setImporte(double importe) {
        this.importe = importe;
    }
    public Long getNroCheque() {
        return nroCheque;
    }
    public void setNroCheque(Long nroCheque) {
        this.nroCheque = nroCheque;
    }
    public LocalDate getEmision() {
        return emision;
    }
    public void setEmision(LocalDate emision) {
        this.emision = emision;
    }
    public LocalDate getVencimiento() {
        return vencimiento;
    }
    public void setVencimiento(LocalDate vencimiento) {
        this.vencimiento = vencimiento;
    }
    public String getFirmante() {
        return firmante;
    }
    public void setFirmante(String firmante) {
        this.firmante = firmante;
    }

}

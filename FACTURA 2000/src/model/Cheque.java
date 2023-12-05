package model;

import java.time.LocalDate;

public class Cheque extends FormaPago {

    private static Long contador = 0L;
    private Long nroCheque;
    private LocalDate emision;
    private LocalDate vencimiento;
    private String firmante;

    public Cheque() {
        contador++;
        nroCheque = contador;
    }

    public Long getNroCheque() {
        return nroCheque;
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

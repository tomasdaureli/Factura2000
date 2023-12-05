package model;

public enum TipoIVA {

    IVA_2_5(2.5),
    IVA_5(5),
    IVA_10_5(10.5),
    IVA_21(21),
    IVA_27(27);

    private double valor;

    TipoIVA(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

}

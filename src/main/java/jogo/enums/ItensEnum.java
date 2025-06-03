package jogo.enums;

public enum ItensEnum {
    ARMA(1),
    CONSUMIVEL(2),
    FERRAMENTA(3),
    MATERIAL(4);

    private final int indice;

    private ItensEnum(int indice) {
        this.indice = indice;
    }

    public int getIndice() {
        return indice;
    }
}

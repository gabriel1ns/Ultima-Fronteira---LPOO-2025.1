package jogo.enums.personagem;

public enum AtributosEnum {
    VIDA(0),
    FOME(1),
    SEDE(2),
    ENERGIA(3),
    SANIDADE(4);

    private int indice;

    private AtributosEnum(int indice) {
        this.indice = indice;
    }

    public int getIndice() {
        return indice;
    }
}

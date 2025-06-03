package jogo.enums.personagem;

public enum PersonagemAtributosEnum {
    VIDA(0),
    FOME(1),
    SEDE(2),
    ENERGIA(3),
    SANIDADE(4);

    private final int indice;

    private PersonagemAtributosEnum(int indice) {
        this.indice = indice;
    }

    public int getIndice() {
        return indice;
    }
}

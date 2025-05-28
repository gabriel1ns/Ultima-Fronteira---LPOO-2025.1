package jogo.enums.itens.consumiveis;

public enum AlimentosEnum {
    BANANA("Banana", 1, 10, 20),
    CARNE("Carne", 5, 10, 10);

    private final String NOME;
    private final int PESO;
    private final int VALOR_NUTRICIONAL;
    private final int PRAZO_DE_VALIDADE;

    private AlimentosEnum(String nome, int peso, int valorNutricional, int prazoDeValidade) {
        this.NOME = nome;
        this.PESO = peso;
        this.VALOR_NUTRICIONAL = valorNutricional;
        this.PRAZO_DE_VALIDADE = prazoDeValidade;
    }

    public String getNOME() {
        return NOME;
    }

    public int getPESO() {
        return PESO;
    }

    public int getVALOR_NUTRICIONAL() {
        return VALOR_NUTRICIONAL;
    }

    public int getPRAZO_DE_VALIDADE() {
        return PRAZO_DE_VALIDADE;
    }
}

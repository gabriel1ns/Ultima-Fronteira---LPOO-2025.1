package jogo.enums.itens.consumiveis;

public enum AlimentosEnum {
    FRUTAS_SILVESTRES("Frutas silvestres", 1, 10, 10),
    CARNE("Carne", 5, 20, 15),
    PEIXE("Peixe", 3, 15, 15),
    FEIJOES_ENLATADOS("Feijões enlatados", 5, 15, 30);

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

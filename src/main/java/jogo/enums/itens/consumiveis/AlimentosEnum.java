package jogo.enums.itens.consumiveis;

public enum AlimentosEnum {
    FRUTAS_SILVESTRES
    ("Frutas silvestres", 1, 10, 10),
    
    CARNE
    ("Carne", 5, 20, 15),
    
    PEIXE
    ("Peixe", 3, 15, 15),
    
    FEIJOES_ENLATADOS
    ("Feijões enlatados", 5, 15, 30);

    private final String nome;
    private final int peso;
    private final int valorNutricional;
    private final int prazoDeValidade;

    private AlimentosEnum(String nome, int peso, int valorNutricional, int prazoDeValidade) {
        this.nome = nome;
        this.peso = peso;
        this.valorNutricional = valorNutricional;
        this.prazoDeValidade = prazoDeValidade;
    }

    public String getNome() {
        return nome;
    }

    public int getPeso() {
        return peso;
    }

    public int getValorNutricional() {
        return valorNutricional;
    }

    public int getPrazoDeValidade() {
        return prazoDeValidade;
    }
}

package jogo.enums.itens;

public enum FerramentasEnum {
    PICARETA
    ("Picareta", 5, 5, 10),

    MACHADO
    ("Machado", 5, 5, 10),
    
    RECIPIENTE
    ("Recipiente", 5, 5, 20);

    private final String NOME;
    private final int EFICIENCIA;
    private final int PESO;
    private final int DURABILIDADE;

    private FerramentasEnum(String nome, int eficiencia, int peso, int durabilidade) {
        this.NOME = nome;
        this.EFICIENCIA = eficiencia;
        this.PESO = peso;
        this.DURABILIDADE = durabilidade;
    }

    public String getNOME() {
        return NOME;
    }

    public int getEFICIENCIA() {
        return EFICIENCIA;
    }

    public int getPESO() {
        return PESO;
    }

    public int getDURABILIDADE() {
        return DURABILIDADE;
    }

    
    
}

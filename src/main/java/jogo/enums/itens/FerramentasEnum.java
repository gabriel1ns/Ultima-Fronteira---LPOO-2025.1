package jogo.enums.itens;

public enum FerramentasEnum {
    PICARETA
    ("Picareta", 5, 10),

    MACHADO
    ("Machado", 5, 10),
    
    RECIPIENTE
    ("Recipiente", 5, 20),
    
    JANGADA
    ("Jangada", 0, 0);

    private final String nome;
    private final int peso;
    private final int durabilidade;

    private FerramentasEnum(String nome, int peso, int durabilidade) {
        this.nome = nome;
        this.peso = peso;
        this.durabilidade = durabilidade;
    }

    public String getNome() {
        return nome;
    }

    public int getPeso() {
        return peso;
    }

    public int getDurabilidade() {
        return durabilidade;
    }

    
    
}

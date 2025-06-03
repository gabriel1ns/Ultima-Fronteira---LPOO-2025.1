package jogo.enums.itens.consumiveis;

public enum RemediosEnum {    
    REMENDOS
    ("Remendos de tecido", 2, 5),
    
    CHA_DE_FOLHAS
    ("Chá de folhas", 5, 10),
    
    ANTIDOTO
    ("Antídoto", 2, 20);

    private final String nome;
    private final int peso;
    private final int efeito;

    private RemediosEnum(String nome, int peso, int efeito) {
        this.nome = nome;
        this.peso = peso;
        this.efeito = efeito;
    }

    public String getNome() {
        return nome;
    }

    public int getPeso() {
        return peso;
    }

    public int getEfeito() {
        return efeito;
    }
}

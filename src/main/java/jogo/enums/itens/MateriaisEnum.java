package jogo.enums.itens;

public enum MateriaisEnum {
    CORDA
    ("Corda", 2, 0),

    FERRO
    ("Ferro", 5, 1),

    MADEIRA
    ("Madeira", 2, 2),
    
    PEDRA
    ("Pedra", 3, 3),
    
    PLANTA
    ("Planta", 1, 4);

    private final String nome;
    private final int peso;
    private final int id;

    private MateriaisEnum(String nome, int peso, int id) {
        this.nome = nome;
        this.peso = peso;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public int getPeso() {
        return peso;
    }

    public int getId() {
        return id;
    }
}

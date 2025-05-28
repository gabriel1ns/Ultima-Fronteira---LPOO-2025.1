package jogo.enums.itens;

public enum ArmasEnum {
    PUNHOS("Punhos", 0, 10000, 5, 1),
    ESPADA("Espada", 5, 10, 10, 2),
    ARCOFLECHA("Arco e Flecha", 10, 20, 5, 20),
    LANCA("Lança", 3, 5, 5, 10);

    private final String NOME;
    private final int PESO;
    private final int DURABILIDADE;
    private final int DANO;
    private final int ALCANCE;

    private ArmasEnum(String nome, int peso, int durabilidade, int dano, int alcance) {
        this.NOME = nome;
        this.PESO = peso;
        this.DURABILIDADE = durabilidade;
        this.DANO = dano;
        this.ALCANCE = alcance;
    }

    public String getNome() {
        return NOME;
    }

    public int getPeso() {
        return PESO;
    }

    public int getDurabilidade() {
        return DURABILIDADE;
    }

    public int getDano() {
        return DANO;
    }

    public int getAlcance() {
        return ALCANCE;
    }

    

}

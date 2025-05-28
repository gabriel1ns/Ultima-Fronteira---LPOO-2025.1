package jogo.enums.eventos;

public enum EventosCriaturasEnum {
    LOBO("Lobo", "Um lobo está atacando",
        10, 5, 1),
    URSO("Urso", "Um urso está atacando",
        10, 10, 1);
    
    private final String nome;
    private final String descricao;
    private final int vida;
    private final int dano;
    private final int distancia;
    
    private EventosCriaturasEnum(String nome, String descricao, int vida, int dano, int distancia) {
        this.nome = nome;
        this.descricao = descricao;
        this.vida = vida;
        this.dano = dano;
        this.distancia = distancia;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getVida() {
        return vida;
    }

    public int getDano() {
        return dano;
    }

    public int getDistancia() {
        return distancia;
    }
}

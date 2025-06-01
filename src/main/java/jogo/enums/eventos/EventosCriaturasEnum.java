package jogo.enums.eventos;

import jogo.enums.EventosEnum;

public enum EventosCriaturasEnum {
    LOBO
    ("Lobo", "Um lobo hostil se aproxima",
    20, 5, 1),
    
    URSO
    ("Urso", "Um urso feroz está prestes a te perseguir",
    30, 10, 1),
    
    CORVO
    ("Corvo", "Um corvo te sobrevoa para atacar",
    10, 2, 4),
    
    COBRA
    ("Cobra", "Uma cobra venenosa se aproxima rapidamente",
    15, 2, 2);
    
    public static final EventosEnum TIPO = EventosEnum.CRIATURA; 

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

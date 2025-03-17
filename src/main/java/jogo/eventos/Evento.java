package jogo.eventos;

import jogo.personagem.Personagem;

public abstract class Evento {
    private String nome;
    private String descricao;

    public Evento(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public abstract void executar(Personagem personagem);
} // criar as subclasses dps (dificuldade falar com monitora)
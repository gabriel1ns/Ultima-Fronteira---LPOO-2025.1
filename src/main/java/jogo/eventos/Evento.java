package jogo.eventos;

import jogo.ambiente.Ambiente;
import jogo.personagem.Personagem;

public abstract class Evento {
    protected String nome;
    protected String descricao;

    protected String toString = "Evento: " + nome + "\n" +
                                "Descricao: " + descricao + "\n";

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

    public abstract void executar(Ambiente ambiente, Personagem personagem);
} // criar as subclasses dps (dificuldade falar com monitora)
    // lol
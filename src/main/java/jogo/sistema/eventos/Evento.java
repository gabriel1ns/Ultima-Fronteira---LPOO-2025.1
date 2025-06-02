package jogo.sistema.eventos;

import jogo.sistema.Personagem;

public abstract class Evento {
    private final String nome;
    private final String descricao;

    private int duracao;

    public Evento(String nome, String descricao, int duracao) {
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
    }

    public abstract void executar(Personagem personagem);

    public void decrementarDuracao() {
        setDuracao(getDuracao() - 1);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }


    @Override
    public String toString() {
        return "Evento: " + descricao; // Removed trailing "\n"
    }
}
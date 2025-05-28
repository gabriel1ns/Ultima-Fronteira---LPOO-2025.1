package jogo.ambientes;

import jogo.eventos.Evento;

public class Ambiente {
    private final String nome;
    private final String descricao;
    private final Evento[] eventosPossiveis;
    private final int[] probabilidadeDeEventos;
    private final int dificuldadeDeExploracao;

    public Ambiente(String nome, String descricao, Evento[] eventosPossiveis, int[] probabilidadeDeEventos, 
                    int dificuldadeDeExploracao) {
        this.nome = nome;
        this.descricao = descricao;
        this.eventosPossiveis = eventosPossiveis;
        this.probabilidadeDeEventos = probabilidadeDeEventos;
        this.dificuldadeDeExploracao = dificuldadeDeExploracao;

        assert(eventosPossiveis.length == probabilidadeDeEventos.length);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getDificuldadeDeExploracao() {
        return dificuldadeDeExploracao;
    }

    public Evento[] getEventosPossiveis() {
        return eventosPossiveis;
    }
    
    public int[] getProbabilidadeDeEventos() {
        return probabilidadeDeEventos;
    }

    @Override
    public String toString() {
        return this.nome + "n";
    }

}

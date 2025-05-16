package jogo.ambiente;

import jogo.eventos.Evento;
import jogo.itens.Item;

public abstract class Ambiente {
    private String nome;
    private String descricao;
    private int dificuldadeDeExploracao;

    private Item[] recursosDisponiveis;
    private Evento[] eventosPossiveis;
    private int[] probabilidadeDeEventos;

    public Ambiente(Evento[] eventosPossiveis, int[] probabilidadeDeEventos, int dificuldadeDeExploracao) {
        setEventosPossiveis(eventosPossiveis);
        setProbabilidadeDeEventos(probabilidadeDeEventos);
        setDificuldadeDeExploracao(dificuldadeDeExploracao);
    }

    final public void setNome(String nome) {
        this.nome = nome;
    }

    final public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    final public void setDificuldadeDeExploracao(int dificuldadeDeExploracao) {
        this.dificuldadeDeExploracao = dificuldadeDeExploracao;
    }

    final public void setRecursosDisponiveis(Item[] recursosDisponiveis) {
        this.recursosDisponiveis = recursosDisponiveis;
    }

    final public void setEventosPossiveis(Evento[] eventosPossiveis) {
        this.eventosPossiveis = eventosPossiveis;
    }

    final public void setProbabilidadeDeEventos(int[] probabilidadeDeEventos) {
        this.probabilidadeDeEventos = probabilidadeDeEventos;
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

    public Item[] getRecursosDisponiveis() {
        return recursosDisponiveis;
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

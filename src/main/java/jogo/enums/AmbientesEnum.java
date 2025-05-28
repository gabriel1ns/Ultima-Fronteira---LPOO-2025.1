package jogo.enums;

import jogo.eventos.Evento;

public enum AmbientesEnum {
    CAVERNA(),

    FLORESTA(),

    LAGORIO(),

    MONTANHA(),

    RUINAS();
    
    private final String nome;
    private final String descricao;
    private final Evento[] eventosPossiveis;
    private final int[] probabilidadeDeEventos; 
    private final int dificuldadeDeExploracao;
    
    private AmbientesEnum(String nome, String descricao, Evento[] eventosPossiveis, int[] probabilidadeDeEventos,
            int dificuldadeDeExploracao) {
        this.nome = nome;
        this.descricao = descricao;
        this.eventosPossiveis = eventosPossiveis;
        this.probabilidadeDeEventos = probabilidadeDeEventos;
        this.dificuldadeDeExploracao = dificuldadeDeExploracao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Evento[] getEventosPossiveis() {
        return eventosPossiveis;
    }

    public int[] getProbabilidadeDeEventos() {
        return probabilidadeDeEventos;
    }

    public int getDificuldadeDeExploracao() {
        return dificuldadeDeExploracao;
    }

    
}

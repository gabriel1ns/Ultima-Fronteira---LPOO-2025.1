package jogo.ambiente;

import jogo.eventos.Evento;

public class AmbienteRuinas extends Ambiente {

    private static final String NOME = "Ruínas Abandonadas";
    private static final String DESCRICAO = "Restos de antigas construções que podem conter suprimentos valiosos ou armadilhas.";
    private static final Evento[] EVENTOS_POSSIVEIS = {};
    private static final int[] PROBABILIDADE_DE_EVENTOS = {};

    public AmbienteRuinas() {
        super(NOME, DESCRICAO, EVENTOS_POSSIVEIS, PROBABILIDADE_DE_EVENTOS, 5);
    }
}

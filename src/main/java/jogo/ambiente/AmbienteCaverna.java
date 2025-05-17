package jogo.ambiente;

import jogo.eventos.Evento;
import jogo.eventos.criatura.EventoUrso;

public class AmbienteCaverna extends Ambiente {

    private static final String NOME = "Caverna";
    private static final String DESCRICAO = "Um ambiente subterrâneo que pode oferecer abrigo contra o clima, mas esconde perigos desconhecidos.";
    private static final Evento[] EVENTOS_POSSIVEIS = {new EventoUrso()};
    private static final int[] PROBABILIDADE_DE_EVENTOS = {1};

    public AmbienteCaverna() {
        super(NOME, DESCRICAO, EVENTOS_POSSIVEIS, PROBABILIDADE_DE_EVENTOS, 5);
    }
}

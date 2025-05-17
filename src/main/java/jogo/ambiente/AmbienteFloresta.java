package jogo.ambiente;

import jogo.eventos.Evento;
import jogo.eventos.climatico.EventoTempestade;
import jogo.eventos.criatura.EventoLobo;
import jogo.eventos.criatura.EventoUrso;

public class AmbienteFloresta extends Ambiente {

    private static final String NOME = "Floresta";
    private static final String DESCRICAO = "Uma área rica em recursos naturais, mas também habitada por predadores.";
    private static final Evento[] EVENTOS_POSSIVEIS = {new EventoLobo(),
        new EventoUrso(), new EventoTempestade(2)};
    private static final int[] PROBABILIDADE_DE_EVENTOS = {5,5,6};

    public AmbienteFloresta() {
        super(NOME, DESCRICAO, EVENTOS_POSSIVEIS, PROBABILIDADE_DE_EVENTOS, 5);
    }
}

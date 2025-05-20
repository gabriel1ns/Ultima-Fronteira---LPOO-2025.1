package jogo.ambiente;

import jogo.eventos.Evento;
import jogo.eventos.climatico.EventoClimaticoTempestade;

public class AmbienteLagoRio extends Ambiente {

    private static final String NOME = "Lago e Rio";
    private static final String DESCRICAO = "Regiões ricas em água, mas que podem esconder riscos como afogamento ou criaturas aquáticas.";
    private static final Evento[] EVENTOS_POSSIVEIS = {new EventoClimaticoTempestade(2)};
    private static final int[] PROBABILIDADE_DE_EVENTOS = {7};

    public AmbienteLagoRio() {
        super(NOME, DESCRICAO, EVENTOS_POSSIVEIS, PROBABILIDADE_DE_EVENTOS, 5);
    }
}

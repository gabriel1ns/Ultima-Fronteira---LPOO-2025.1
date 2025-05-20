package jogo.ambiente;

import jogo.eventos.Evento;
import jogo.eventos.descoberta.EventoDescobertaPedra;

public class AmbienteMontanha extends Ambiente {

    private static final String NOME = "Montanha";
    private static final String DESCRICAO = "Uma região de difícil acesso, mas rica em minérios e pedras preciosa";
    private static final Evento[] EVENTOS_POSSIVEIS = {new EventoDescobertaPedra()};
    private static final int[] PROBABILIDADE_DE_EVENTOS = {2};

    public AmbienteMontanha() {
        super(NOME, DESCRICAO, EVENTOS_POSSIVEIS, PROBABILIDADE_DE_EVENTOS, 5);
    }
}

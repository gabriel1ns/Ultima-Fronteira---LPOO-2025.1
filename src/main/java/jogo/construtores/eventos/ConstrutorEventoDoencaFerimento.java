package jogo.construtores.eventos;

import jogo.enums.eventos.EventosDoencaFerimentosEnum;
import jogo.sistema.eventos.EventoDoencaFerimento;

public class ConstrutorEventoDoencaFerimento {
    public static EventoDoencaFerimento construir(EventosDoencaFerimentosEnum doencaFerimento) {
        return new EventoDoencaFerimento(
            doencaFerimento.getNome(), 
            doencaFerimento.getDescricao(),
            doencaFerimento.getDuracao(),
            doencaFerimento.getEfeitoNegativo(), 
            doencaFerimento.getRemedioParaCura()
        );

    }
}

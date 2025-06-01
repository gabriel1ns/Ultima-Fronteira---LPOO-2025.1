package jogo.construtores.eventos;

import jogo.enums.eventos.EventosClimaticosEnum;
import jogo.sistema.eventos.EventoClimatico;

public class ConstrutorEventoClimatico {
    public static EventoClimatico construir(EventosClimaticosEnum evento) {
        return new EventoClimatico(
            evento.getNome(), 
            evento.getDescricao(), 
            evento.getDuracao(), 
            evento.getAtributosAfetados(), 
            evento.getEfeitos()
        );
    }
}

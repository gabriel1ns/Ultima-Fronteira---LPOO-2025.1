package jogo.construtores.eventos;

import jogo.enums.eventos.EventosDescobertasEnum;
import jogo.sistema.eventos.EventoDescoberta;

public class ConstrutorEventoDescoberta {
    public static EventoDescoberta construir(EventosDescobertasEnum descoberta) {
        return new EventoDescoberta(
            descoberta.getNome(), 
            descoberta.getDescricao(), 
            descoberta.getItensDescobertos(), 
            descoberta.getQuantidades(),
            descoberta.getItemNecessario()
        );
    }
}

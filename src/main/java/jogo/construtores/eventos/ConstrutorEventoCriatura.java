package jogo.construtores.eventos;

import jogo.enums.eventos.EventosCriaturasEnum;
import jogo.eventos.EventoCriatura;

public class ConstrutorEventoCriatura {
    public static EventoCriatura construir(EventosCriaturasEnum criatura) {
        return new EventoCriatura(
            criatura.getNome(), 
            criatura.getDescricao(), 
            criatura.getVida(),
            criatura.getDano(), 
            criatura.getDistancia()
        );
    }
}

package jogo.construtores;

import jogo.construtores.eventos.ConstrutorEventoClimatico;
import jogo.construtores.eventos.ConstrutorEventoCriatura;
import jogo.construtores.eventos.ConstrutorEventoDescoberta;
import jogo.enums.EventosEnum;
import jogo.enums.eventos.EventosClimaticosEnum;
import jogo.enums.eventos.EventosCriaturasEnum;
import jogo.enums.eventos.EventosDescobertasEnum;
import jogo.sistema.eventos.Evento;

public class ConstrutorEvento {
    public static Evento construir(Enum<?> eventoEnum) throws IllegalArgumentException {
        String tipo = eventoEnum.getClass().getSimpleName();

        if(tipo.equals(EventosEnum.CLIMATICO.getTipo())) 
            return ConstrutorEventoClimatico.construir((EventosClimaticosEnum) eventoEnum);

        if(tipo.equals(EventosEnum.CRIATURA.getTipo()))
            return ConstrutorEventoCriatura.construir((EventosCriaturasEnum) eventoEnum);
            
        if(tipo.equals(EventosEnum.DESCOBERTA.getTipo()))
            return ConstrutorEventoDescoberta.construir((EventosDescobertasEnum) eventoEnum);
        
        throw new IllegalArgumentException("Tipo de evento não existente: " + tipo);
    }
}

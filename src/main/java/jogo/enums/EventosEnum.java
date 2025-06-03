package jogo.enums;

import jogo.enums.eventos.EventosClimaticosEnum;
import jogo.enums.eventos.EventosCriaturasEnum;
import jogo.enums.eventos.EventosDescobertasEnum;
import jogo.enums.eventos.EventosDoencaFerimentosEnum;

public enum EventosEnum {
    CLIMATICO(EventosClimaticosEnum.class),
    CRIATURA(EventosCriaturasEnum.class),
    DESCOBERTA(EventosDescobertasEnum.class),
    DOENCA_FERIMENTO(EventosDoencaFerimentosEnum.class);

    private final String tipo;

    private EventosEnum(Class<?> tipoEnum) {
        this.tipo = tipoEnum.getSimpleName();
    }

    public String getTipo() {
        return tipo;
    }

    
}

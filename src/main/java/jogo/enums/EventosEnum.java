package jogo.enums;

import jogo.enums.eventos.EventosClimaticosEnum;
import jogo.enums.eventos.EventosCriaturasEnum;
import jogo.enums.eventos.EventosDescobertasEnum;

public enum EventosEnum {
    CLIMATICO(EventosClimaticosEnum.class),
    CRIATURA(EventosCriaturasEnum.class),
    DESCOBERTA(EventosDescobertasEnum.class);

    private final String tipo;

    private EventosEnum(Class<?> tipoEnum) {
        this.tipo = tipoEnum.getSimpleName();
    }

    public String getTipo() {
        return tipo;
    }

    
}

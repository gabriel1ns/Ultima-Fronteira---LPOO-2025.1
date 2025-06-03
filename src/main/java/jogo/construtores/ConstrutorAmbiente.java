package jogo.construtores;

import jogo.enums.AmbientesEnum;
import jogo.sistema.Ambiente;

public class ConstrutorAmbiente {
    public static Ambiente construir(AmbientesEnum ambiente) throws IllegalArgumentException {
        
        if(ambiente.getEventosPossiveis().length != ambiente.getProbabilidadeDeEventos().length)
            throw new IllegalArgumentException("Tamanho dos arrays de eventos e probabilidades de " 
                      + ambiente.getNome() + " diferem");
        
        return new Ambiente(
            ambiente.getNome(), 
            ambiente.getDescricao(), 
            ambiente.getEventosPossiveis(), 
            ambiente.getProbabilidadeDeEventos(), 
            ambiente.getDificuldadeDeExploracao()
        );
    }
}

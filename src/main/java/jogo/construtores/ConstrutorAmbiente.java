package jogo.construtores;

import jogo.ambientes.Ambiente;
import jogo.enums.AmbientesEnum;

public class ConstrutorAmbiente {
    public static Ambiente construir(AmbientesEnum ambiente) {
        return new Ambiente(
            ambiente.getNome(), 
            ambiente.getDescricao(), 
            ambiente.getEventosPossiveis(), 
            ambiente.getProbabilidadeDeEventos(), 
            ambiente.getDificuldadeDeExploracao()
        );
    }
}

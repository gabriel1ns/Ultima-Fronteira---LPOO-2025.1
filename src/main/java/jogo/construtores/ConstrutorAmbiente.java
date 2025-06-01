package jogo.construtores;

import jogo.enums.AmbientesEnum;
import jogo.sistema.Ambiente;

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

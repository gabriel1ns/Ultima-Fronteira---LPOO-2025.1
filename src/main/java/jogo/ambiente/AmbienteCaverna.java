package jogo.ambiente;

import jogo.eventos.Evento;
import jogo.itens.Item;

public class AmbienteCaverna extends Ambiente {

    private final String nome = "Caverna";
    private final String descricao = "Um ambiente subterrâneo que pode oferecer abrigo contra o clima, mas esconde perigos desconhecidos.";
    private final Evento[] eventosPossiveis = {};
    private final int[] probabilidadeDeEventos = {};

    public AmbienteCaverna(Item[] recursosDisponiveis) {
        super(recursosDisponiveis);
        
        super.setNome(this.nome);
        super.setDescricao(descricao);
        super.setEventosPossiveis(eventosPossiveis);
        super.setProbabilidadeDeEventos(probabilidadeDeEventos);
        
        assert(eventosPossiveis.length == probabilidadeDeEventos.length);

        // Atributos adicionais
        

    }
}

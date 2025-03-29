package jogo.ambiente;

import jogo.eventos.Evento;
import jogo.itens.Item;

public class AmbienteFloresta extends Ambiente {

    private final String nome = "";
    private final String descricao = "";
    private final Evento[] eventosPossiveis = {};
    private final int[] probabilidadeDeEventos = {};

    public AmbienteFloresta(Item[] recursosDisponiveis) {
        super(recursosDisponiveis);
        
        super.setNome(this.nome);
        super.setDescricao(descricao);
        super.setEventosPossiveis(eventosPossiveis);
        super.setProbabilidadeDeEventos(probabilidadeDeEventos);
        
        assert(eventosPossiveis.length == probabilidadeDeEventos.length);

        // Atributos adicionais
        

    }
}

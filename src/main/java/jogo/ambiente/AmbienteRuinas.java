package jogo.ambiente;

import jogo.eventos.Evento;
import jogo.itens.Item;

public class AmbienteRuinas extends Ambiente {

    private final String nome = "";
    private final String descricao = "";
    private final Evento[] eventosPossiveis = {};
    private final int[] probabilidadeDeEventos = {};

    public AmbienteRuinas(Item[] recursosDisponiveis) {
        super(recursosDisponiveis);
        
        super.nome = this.nome;
        super.descricao = this.descricao;
        super.eventosPossiveis = this.eventosPossiveis;
        super.probabilidadeDeEventos = this.probabilidadeDeEventos;
        assert(eventosPossiveis.length == probabilidadeDeEventos.length);

        // Atributos adicionais
        

    }
}

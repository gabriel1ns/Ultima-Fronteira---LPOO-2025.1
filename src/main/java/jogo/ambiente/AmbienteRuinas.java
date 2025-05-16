package jogo.ambiente;

import jogo.eventos.Evento;
import jogo.itens.Item;
import jogo.itens.materiais.Madeira;

public class AmbienteRuinas extends Ambiente {

    private final String nome = "Ruínas Abandonadas";
    private final String descricao = "Restos de antigas construções que podem conter suprimentos valiosos ou armadilhas.";
    private final Evento[] eventosPossiveis = {};
    private final int[] probabilidadeDeEventos = {};

    public AmbienteRuinas(Item[] recursosDisponiveis) {
        super(new Item[] {new Madeira()}, new int[] {5}, 4);


        super.setNome(this.nome);
        super.setDescricao(this.descricao);
        super.setEventosPossiveis(this.eventosPossiveis);
        super.setProbabilidadeDeEventos(this.probabilidadeDeEventos);

        
        
        assert(eventosPossiveis.length == probabilidadeDeEventos.length);

        // Atributos adicionais - adicionar dps
        

    }
}

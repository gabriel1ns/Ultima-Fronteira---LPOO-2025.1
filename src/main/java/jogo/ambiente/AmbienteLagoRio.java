package jogo.ambiente;

import jogo.eventos.Evento;
import jogo.itens.Item;
import jogo.itens.consumiveis.Agua;
import jogo.itens.consumiveis.alimentos.Proteina;

public class AmbienteLagoRio extends Ambiente {

    private final String nome = "Lago e Rio";
    private final String descricao = "Regiões ricas em água, mas que podem esconder riscos como afogamento ou criaturas aquáticas.";
    private final Evento[] eventosPossiveis = {};
    private final int[] probabilidadeDeEventos = {};

    public AmbienteLagoRio(Item[] recursosDisponiveis) {
        super(new Item[] {new Agua(), new Proteina()}, new int[] {10}, 3); //resolver agua
        
        super.setNome(this.nome);
        super.setDescricao(descricao);
        super.setEventosPossiveis(eventosPossiveis);
        super.setProbabilidadeDeEventos(probabilidadeDeEventos);
        
        assert(eventosPossiveis.length == probabilidadeDeEventos.length);

        // Atributos adicionais
        

    }
}

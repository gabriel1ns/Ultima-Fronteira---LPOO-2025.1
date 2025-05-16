package jogo.ambiente;

import jogo.eventos.Evento;
import jogo.eventos.climatico.EventoTempestade;
import jogo.itens.Item;
import jogo.itens.consumiveis.Agua;
import jogo.itens.consumiveis.alimentos.Proteina;

public class AmbienteLagoRio extends Ambiente {

    private final String nome = "Lago e Rio";
    private final String descricao = "Regiões ricas em água, mas que podem esconder riscos como afogamento ou criaturas aquáticas.";
    private final Evento[] eventosPossiveis = {new EventoTempestade(2)};
    private final int[] probabilidadeDeEventos = {7};

    public AmbienteLagoRio(Item[] recursosDisponiveis) {
        super(new Evento[]{ new EventoTempestade(2)}, new int[] {7}, 1); //resolver agua
        
        super.setNome(this.nome);
        super.setDescricao(descricao);
        super.setEventosPossiveis(eventosPossiveis);
        super.setProbabilidadeDeEventos(probabilidadeDeEventos);
        
        assert(eventosPossiveis.length == probabilidadeDeEventos.length);

        // Atributos adicionais
        

    }
}

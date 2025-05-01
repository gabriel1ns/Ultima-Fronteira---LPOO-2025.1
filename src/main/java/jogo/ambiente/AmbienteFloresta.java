package jogo.ambiente;

import jogo.eventos.Evento;
import jogo.itens.Item;
import jogo.itens.consumiveis.alimentos.Fruta;
import jogo.itens.consumiveis.alimentos.Proteina;
import jogo.itens.materiais.Ferro;
import jogo.itens.materiais.Madeira;
import jogo.itens.materiais.Pedra;

public class AmbienteFloresta extends Ambiente {

    private final String nome = "Floresta";
    private final String descricao = "Uma área rica em recursos naturais, mas também habitada por predadores.";
    private final Evento[] eventosPossiveis = {};
    private final int[] probabilidadeDeEventos = {};

    public AmbienteFloresta(Item[] recursosDisponiveis) {
        super(new Item[] { new Madeira(), new Pedra(), new Fruta(), new Proteina()}, new int[] {5}, 3);
        // mexer na probabilidade de eventos após criar subclasses dos eventos
        
        super.setNome(this.nome);
        super.setDescricao(descricao);
        super.setEventosPossiveis(eventosPossiveis);
        super.setProbabilidadeDeEventos(probabilidadeDeEventos);
        
        assert(eventosPossiveis.length == probabilidadeDeEventos.length);


        

    }
}

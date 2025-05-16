package jogo.ambiente;

import jogo.eventos.Evento;
import jogo.eventos.climatico.EventoTempestade;
import jogo.eventos.criatura.EventoLobo;
import jogo.eventos.criatura.EventoUrso;
import jogo.itens.Item;
import jogo.itens.consumiveis.alimentos.Fruta;
import jogo.itens.consumiveis.alimentos.Proteina;
import jogo.itens.materiais.Madeira;
import jogo.itens.materiais.Pedra;

public class AmbienteFloresta extends Ambiente {

    private final String nome = "Floresta";
    private final String descricao = "Uma área rica em recursos naturais, mas também habitada por predadores.";
    private final Evento[] eventosPossiveis = {new EventoLobo(),
    new EventoUrso(), new EventoTempestade(2)};
    private final int[] probabilidadeDeEventos = {5,5,6};

    public AmbienteFloresta(Evento[] eventosPossiveis) {
        super(new Evento[]{new EventoLobo(),
                new EventoUrso(), new EventoTempestade(2)},
                new int[] {5,5,6}, 3);
        // mexer na probabilidade de eventos após criar subclasses dos eventos
        
        super.setNome(this.nome);
        super.setDescricao(descricao);
        super.setEventosPossiveis(eventosPossiveis);
        super.setProbabilidadeDeEventos(probabilidadeDeEventos);
        
        assert(eventosPossiveis.length == probabilidadeDeEventos.length);


        

    }
}

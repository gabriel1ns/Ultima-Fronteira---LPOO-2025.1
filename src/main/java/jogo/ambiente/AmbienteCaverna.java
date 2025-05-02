package jogo.ambiente;

import jogo.eventos.Evento;
import jogo.eventos.criatura.EventoUrso;
import jogo.itens.Item;

public class AmbienteCaverna extends Ambiente {

    private final String nome = "Caverna";
    private final String descricao = "Um ambiente subterrâneo que pode oferecer abrigo contra o clima, mas esconde perigos desconhecidos.";
    private final Evento[] eventosPossiveis = {new EventoUrso()};
    private final int[] probabilidadeDeEventos = {1};

    public AmbienteCaverna(Item[] items) {
        super(new Evento[]{new EventoUrso()}, new int[] {1}, 5);
        
        super.setNome(this.nome);
        super.setDescricao(descricao);
        super.setEventosPossiveis(eventosPossiveis);
        super.setProbabilidadeDeEventos(probabilidadeDeEventos);

        //adicionar materiais, depende defazer os itens
        
        assert(eventosPossiveis.length == probabilidadeDeEventos.length);

        

    }
}

package jogo.ambiente;

import jogo.eventos.Evento;
import jogo.itens.Item;

public class AmbienteMontanha extends Ambiente {

    private final String nome = "Montanha";
    private final String descricao = "Uma região de difícil acesso, mas rica em minérios e pedras preciosa";
    private final Evento[] eventosPossiveis = {};
    private final int[] probabilidadeDeEventos = {};

    public AmbienteMontanha(Item[] recursosDisponiveis) {
        super(recursosDisponiveis);
        
        super.setNome(this.nome);
        super.setDescricao(this.descricao);
        super.setEventosPossiveis(this.eventosPossiveis);
        super.setProbabilidadeDeEventos(this.probabilidadeDeEventos);
        assert(eventosPossiveis.length == probabilidadeDeEventos.length);

        // Atributos adicionais
       // super.dificuldadeDeExploracao *= 4;

    }
}

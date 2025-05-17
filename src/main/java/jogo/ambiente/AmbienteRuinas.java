package jogo.ambiente;

import jogo.eventos.Evento;
import jogo.eventos.climatico.EventoTempestade;

public class AmbienteRuinas extends Ambiente {

    private final String nome = "Ruínas Abandonadas";
    private final String descricao = "Restos de antigas construções que podem conter suprimentos valiosos ou armadilhas.";
    private final Evento[] eventosPossiveis = {};
    private final int[] probabilidadeDeEventos = {};

    public AmbienteRuinas(Evento[] eventosPossiveis) {
        super(new Evento[]{ new EventoTempestade(2)}, new int[] {7}, 4);


        super.setNome(this.nome);
        super.setDescricao(this.descricao);
        super.setEventosPossiveis(this.eventosPossiveis);
        super.setProbabilidadeDeEventos(this.probabilidadeDeEventos);

        
        
        assert(eventosPossiveis.length == probabilidadeDeEventos.length);

        // Atributos adicionais - adicionar dps
        

    }
}

package jogo.eventos.climatico;

import jogo.ambiente.Ambiente;
import jogo.personagem.Personagem;

public class EventoClimaticoNevasca extends EventoClimatico{
    private static final String NOME                = "Nevasca";
    private static final String DESCRICAO           = "Uma nevasca está ocorrendo";
    private static final String[] STATUS_AFETADOS   = {"Energia"};
    private static final int[] EFEITOS              = {-10};

    public EventoClimaticoNevasca(int duracao) {
        super(NOME, DESCRICAO, duracao, STATUS_AFETADOS, EFEITOS);
    }
}

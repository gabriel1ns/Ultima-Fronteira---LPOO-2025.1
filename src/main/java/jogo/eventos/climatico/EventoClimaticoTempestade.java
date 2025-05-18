package jogo.eventos.climatico;

public class EventoClimaticoTempestade extends EventoClimatico{
    private static final String NOME                = "Tempestade";
    private static final String DESCRICAO           = "";
    private static final String[] STATUS_AFETADOS   = {"Energia"};
    private static final int[] EFEITOS              = {-10};

    public EventoClimaticoTempestade(int duracao) {
        super(NOME, DESCRICAO, duracao, STATUS_AFETADOS, EFEITOS);
    }
}

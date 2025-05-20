package jogo.eventos.climatico;

public class EventoClimaticoCalorExtremo extends EventoClimatico{

    private static final String NOME                = "Calor extremo";
    private static final String DESCRICAO           = "Uma onda de calor está ocorrendo";
    private static final String[] STATUS_AFETADOS   = {"Energia"};
    private static final int[] EFEITOS              = {-10};

    public EventoClimaticoCalorExtremo(int duracao) {
        super(NOME, DESCRICAO, duracao, STATUS_AFETADOS, EFEITOS);
    }
}

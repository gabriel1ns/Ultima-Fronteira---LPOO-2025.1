package jogo.eventos.criatura;

public class EventoCriaturaUrso extends EventoCriatura {
    private static final String NOME        = "Urso";
    private static final String DESCRICAO   = "";
    private static final int VIDA           = 10;
    private static final int DANO           = 5;
    private static final int DISTANCIA      = 1;

    public EventoCriaturaUrso() {
        super(NOME, DESCRICAO, VIDA, DANO, DISTANCIA);
    }
}
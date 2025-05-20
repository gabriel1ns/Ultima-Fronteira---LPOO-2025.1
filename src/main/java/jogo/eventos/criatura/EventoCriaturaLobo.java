package jogo.eventos.criatura;


public class EventoCriaturaLobo extends EventoCriatura {
    private static final String NOME        = "Lobo";
    private static final String DESCRICAO   = "Um lobo está atacando";
    private static final int VIDA           = 10;
    private static final int DANO           = 10;
    private static final int DISTANCIA      = 1;

    public EventoCriaturaLobo() {
        super(NOME, DESCRICAO, VIDA, DANO, DISTANCIA);
    }
}
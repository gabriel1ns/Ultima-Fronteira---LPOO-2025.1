package jogo.eventos.criatura;

import jogo.ambiente.Ambiente;
import jogo.personagem.Personagem;


public class EventoLobo extends EventoCriatura {

    public EventoLobo() {
        super("Lobo", "", 60, 10, 2, 4);

    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {
        atacarPersonagem(personagem);
    }

    @Override
    protected int getQuantProteina() {
        return 2;
    }
}
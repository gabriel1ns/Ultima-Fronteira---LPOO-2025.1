package jogo.eventos.criatura;

import jogo.ambiente.Ambiente;
import jogo.personagem.Personagem;

public class EventoUrso extends EventoCriatura {

    public EventoUrso() {
        super("Urso", "", 100, 15, 1, 3);

    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {
    atacarPersonagem(personagem);

}

    @Override
    protected int getQuantProteina() {
        return 4;
    }
}
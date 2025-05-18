package jogo.eventos.criatura;

import jogo.ambiente.Ambiente;
import jogo.personagem.Personagem;

public class EventoCriaturaCobra extends EventoCriatura {

    public EventoCobra(String descricao, int vida, int dano) {
        super("Cobra", "", 30, 8, 1, 3);

    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {
    atacarPersonagem(personagem);
}

    @Override
    protected int getQuantProteina() {
        return 1;
    }
}

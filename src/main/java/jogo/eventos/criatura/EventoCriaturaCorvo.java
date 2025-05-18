package jogo.eventos.criatura;

import jogo.ambiente.Ambiente;
import jogo.personagem.Personagem;

public class EventoCriaturaCorvo extends EventoCriatura {

    public EventoCorvo(){
        super("Corvo", "", 10, 0, 20, 1);
    }

    @Override
    protected int getQuantProteina() {
        return 0;
    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {
        System.out.println("CUÁÁÁÁÁÁAÁÁ! CUÁÁÁ");
    }
}



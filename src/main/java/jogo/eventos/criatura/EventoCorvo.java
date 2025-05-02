package jogo.eventos.criatura;

import jogo.ambiente.Ambiente;
import jogo.personagem.Personagem;

public class EventoCorvo extends EventoCriatura {


    public EventoCorvo(String tipo, String descricao, int vida, int dano, int distancia) {
        super(tipo, descricao, vida, dano, distancia);
    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {
        System.out.println("CUÁÁÁÁÁÁAÁÁ! CUÁÁÁ");
    }
}



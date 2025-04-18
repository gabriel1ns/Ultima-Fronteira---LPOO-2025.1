package jogo.eventos;

import jogo.ambiente.Ambiente;
import jogo.personagem.Personagem;

public interface EventoInterface {
    public void executar(Ambiente ambiente, Personagem personagem);
}

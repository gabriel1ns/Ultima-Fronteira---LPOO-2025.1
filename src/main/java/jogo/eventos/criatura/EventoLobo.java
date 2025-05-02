package jogo.eventos.criatura;

import jogo.ambiente.Ambiente;
import jogo.itens.consumiveis.alimentos.Proteina;
import jogo.personagem.Personagem;
import java.util.Scanner;
import jogo.utils.InputOutput;


public class EventoLobo extends EventoCriatura {

    public EventoLobo() {
        super("Lobo", "",60, 10, 2, 4);

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
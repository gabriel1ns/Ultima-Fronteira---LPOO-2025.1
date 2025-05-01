package jogo.eventos.doencaFerimento;

import jogo.ambiente.Ambiente;
import jogo.personagem.Personagem;

public class EventoDesitratacao extends EventoDoencaFerimento{
    private static final int DANO_FIXO = 15;

    public EventoDesitratacao() {
        super("Desidratação", " ", 10, " ");
    }

    @Override
    public void aplicarFerimento(Personagem personagem){

        personagem.setVida(personagem.getVida() - DANO_FIXO);
        personagem.setEnergia(personagem.getEnergia() - 8);
        personagem.setSanidade(personagem.getSanidade() - 5);

    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {

    }
}

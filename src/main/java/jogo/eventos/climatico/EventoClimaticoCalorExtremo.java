package jogo.eventos.climatico;

import jogo.ambiente.Ambiente;
import jogo.personagem.Personagem;

public class EventoClimaticoCalorExtremo extends EventoClimatico{

    private final int aumentoSede = 10;
    private final int reducaoEnergia = 7;

    public EventoCalorExtremo(int duracao) {
        super("Calor Extremo", "", duracao);
    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {
        personagem.setSede(personagem.getSede() + aumentoSede);
        personagem.setEnergia(personagem.getEnergia() - reducaoEnergia);
    }
}

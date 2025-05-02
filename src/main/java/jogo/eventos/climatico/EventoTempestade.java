package jogo.eventos.climatico;

import jogo.ambiente.Ambiente;
import jogo.personagem.Personagem;

public class EventoTempestade extends EventoClimatico{
    private final int reducaoSanidade = 10;
    private final int reducaoEnergia = 6;

    public EventoTempestade(int duracao) {
        super("Nevasca","", duracao);
    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {
        personagem.setSanidade(personagem.getSanidade() - reducaoSanidade);
        personagem.setEnergia(personagem.getEnergia() - reducaoEnergia);
    }
}

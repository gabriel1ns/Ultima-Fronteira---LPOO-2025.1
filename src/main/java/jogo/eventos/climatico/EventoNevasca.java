package jogo.eventos.climatico;

import jogo.ambiente.Ambiente;
import jogo.personagem.Personagem;

public class EventoNevasca extends EventoClimatico{
    private final int reducaoSanidade = 8;
    private final int reducaoEnergia = 5;

    public EventoNevasca(int duracao) {
        super("Nevasca", "", duracao);
    }

    @Override
    public void aplicarEventoClimatico(Personagem personagem) {
        personagem.setSanidade(personagem.getSanidade() - reducaoSanidade);
        personagem.setEnergia(personagem.getEnergia() - reducaoEnergia);
    }



    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {

    }


}

package jogo.eventos.climatico;

import jogo.ambiente.Ambiente;
import jogo.eventos.Evento;
import jogo.personagem.Personagem;

public abstract class EventoClimatico extends Evento {
    private final String[] statusAfetados;
    private final int[] efeitos;

    public EventoClimatico(String nome, String descricao, int duracao, String[] statusAfetados, int[] efeitos) {
        super(nome, descricao, duracao);
        this.statusAfetados = statusAfetados;
        this.efeitos = efeitos;

        assert(statusAfetados.length == efeitos.length);
    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {
        for(int i = 0; i < statusAfetados.length; i++)
            personagem.mudarAtributo(statusAfetados[i], efeitos[i]);
    }

    // @Override
    // public String toString() {
    //     return  super.toString() +
    //             "Duracao: " + this.duracao + "\n";
    // }


}

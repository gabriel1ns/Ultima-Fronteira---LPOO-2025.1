package jogo.eventos;

import jogo.Ambiente;
import jogo.enums.personagem.AtributosEnum;
import jogo.personagem.Personagem;

public class EventoClimatico extends Evento {
    private final AtributosEnum[] atributosAfetados;
    private final int[] efeitos;

    public EventoClimatico(String nome, String descricao, int duracao, 
    AtributosEnum[] atributosAfetados, int[] efeitos) {

        super(nome, descricao, duracao);
        this.atributosAfetados = atributosAfetados;
        this.efeitos = efeitos;

        assert(atributosAfetados.length == efeitos.length);
    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {
        for(int i = 0; i < atributosAfetados.length; i++)
            personagem.mudarAtributo(atributosAfetados[i], efeitos[i]);
    }

}

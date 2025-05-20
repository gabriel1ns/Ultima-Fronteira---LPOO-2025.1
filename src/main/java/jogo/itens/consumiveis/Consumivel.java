package jogo.itens.consumiveis;

import jogo.itens.Item;
import jogo.personagem.Personagem;

public abstract class Consumivel extends Item {
    private static final String TIPO = "Consumivel";

    public Consumivel(String nome, int peso, int durabilidade, int quantidade) {
        super(nome, TIPO, peso, durabilidade, quantidade);
    }

    public abstract void consumir(Personagem personagem);
}

package jogo.itens.consumiveis;

import jogo.enums.personagem.AtributosEnum;
import jogo.itens.Item;
import jogo.personagem.Personagem;

public abstract class Consumivel extends Item {
    private static final String TIPO = "Consumivel";

    private final AtributosEnum atributoAfetado;
    private final int efeito;

    public Consumivel(String nome, int peso, int quantidade, 
    AtributosEnum atributoAfetado, int efeito) {
        super(nome, TIPO, peso, quantidade);

        this.atributoAfetado = atributoAfetado;
        this.efeito = efeito;
    }

    public AtributosEnum getAtributoAfetado() {
        return atributoAfetado;
    }

    public int getEfeito() {
        return efeito;
    }

    public void consumir(Personagem personagem) {
        personagem.mudarAtributo(atributoAfetado, efeito);
    }
}

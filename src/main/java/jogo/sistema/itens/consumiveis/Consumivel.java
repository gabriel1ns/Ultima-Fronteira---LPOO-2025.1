package jogo.sistema.itens.consumiveis;

import jogo.enums.personagem.PersonagemAtributosEnum;
import jogo.sistema.Personagem;
import jogo.sistema.itens.Item;

public abstract class Consumivel extends Item {
    private static final String TIPO = "Consumivel";

    private final PersonagemAtributosEnum atributoAfetado;
    private final int efeito;

    public Consumivel(String nome, int peso, int quantidade, 
    PersonagemAtributosEnum atributoAfetado, int efeito) {
        super(nome, TIPO, peso, quantidade);

        this.atributoAfetado = atributoAfetado;
        this.efeito = efeito;
    }

    public PersonagemAtributosEnum getAtributoAfetado() {
        return atributoAfetado;
    }

    public int getEfeito() {
        return efeito;
    }

    public void consumir(Personagem personagem) {
        personagem.mudarAtributo(atributoAfetado, efeito);
    }
}

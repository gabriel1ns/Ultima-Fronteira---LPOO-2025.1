package jogo.itens.consumiveis;

import jogo.enums.personagem.AtributosEnum;

public class ConsumivelRemedio extends Consumivel {

    public ConsumivelRemedio(String nome, int peso, int efeito) {
        super(nome, peso, 1, AtributosEnum.VIDA, efeito);
    }

    @Override
    public String toString() {
        return  super.toString() + 
                "Efeito: " + super.getEfeito() + "\n";
    }
}

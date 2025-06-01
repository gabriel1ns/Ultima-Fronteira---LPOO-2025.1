package jogo.sistema.itens.consumiveis;

import jogo.enums.personagem.PersonagemAtributosEnum;

public class ConsumivelRemedio extends Consumivel {

    public ConsumivelRemedio(String nome, int peso, int efeito, int quantidade) {
        super(nome, peso, quantidade, PersonagemAtributosEnum.VIDA, efeito);
    }

    @Override
    public String toString() {
        return  super.toString() + 
                "Efeito: " + super.getEfeito() + "\n";
    }
}

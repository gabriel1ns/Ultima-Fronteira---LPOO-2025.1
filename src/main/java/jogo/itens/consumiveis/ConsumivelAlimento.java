package jogo.itens.consumiveis;

import jogo.enums.personagem.AtributosEnum;
import jogo.itens.IItemPerecivel;
import jogo.personagem.Personagem;

public class ConsumivelAlimento extends Consumivel implements IItemPerecivel {
    private final int valorNutricional;
    private int prazoDeValidade;

    public ConsumivelAlimento(String nome, int peso, int valorNutricional, int prazoDeValidade) {
        super(nome, peso, 1, AtributosEnum.FOME, valorNutricional);

        this.valorNutricional = valorNutricional;
        this.prazoDeValidade = prazoDeValidade;
    }
        
    @Override
    public void consumir(Personagem personagem) {
        super.consumir(personagem);

        if(estaPerecido())
            personagem.mudarAtributo(AtributosEnum.VIDA, valorNutricional);
    }

    @Override
    public boolean estaPerecido() {
        return prazoDeValidade <= 0;
    } 

    @Override
    public void decrementarDurabilidade() {
        prazoDeValidade--;
    }

    public int getValorNutricional() {
        return valorNutricional;
    }

    public int getPrazoDeValidade() {
        return prazoDeValidade;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "Valor nutricional: " + valorNutricional + "\n" +
                "Prazo de validade: " + (estaPerecido()? "vencida" : prazoDeValidade + " turnos") + "\n";            
    }


}

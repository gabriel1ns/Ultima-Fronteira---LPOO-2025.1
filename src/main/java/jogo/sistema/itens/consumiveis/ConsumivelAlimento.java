package jogo.sistema.itens.consumiveis;

import jogo.enums.personagem.PersonagemAtributosEnum;
import jogo.sistema.Personagem;
import jogo.sistema.itens.IItemPerecivel;

public class ConsumivelAlimento extends Consumivel implements IItemPerecivel {
    private final int valorNutricional;
    private int prazoDeValidade;

    public ConsumivelAlimento(String nome, int peso, int valorNutricional, int prazoDeValidade, int quantidade) {
        super(nome, peso, quantidade, PersonagemAtributosEnum.FOME, valorNutricional);

        this.valorNutricional = valorNutricional;
        this.prazoDeValidade = prazoDeValidade;
    }
        
    @Override
    public void consumir(Personagem personagem) {
        super.consumir(personagem);

        if(estaPerecido())
            personagem.mudarAtributo(PersonagemAtributosEnum.VIDA, -1*valorNutricional);
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

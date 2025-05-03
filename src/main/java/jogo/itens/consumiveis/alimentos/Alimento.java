package jogo.itens.consumiveis.alimentos;

import jogo.itens.Item;
import jogo.itens.consumiveis.IConsumivel;
import jogo.personagem.Personagem;

public abstract class Alimento extends Item implements IConsumivel {
    private int valorNutricional;

    public Alimento(String nome, int peso, int valorNutricional, int prazoDeValidade) {
        super(nome, peso, prazoDeValidade, 1);

        setValorNutricional(valorNutricional);
    }
        
    @Override
    public void consumir(Personagem personagem) {
        personagem.setFome(personagem.getFome() + this.valorNutricional);

        if(super.getDurabilidade() <= 0)
            personagem.setVida(personagem.getVida() - this.valorNutricional);
    }

    final public void setValorNutricional(int valorNutricional) {
        this.valorNutricional = valorNutricional;
    }

    public int getValorNutricional() {
        return valorNutricional;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "Valor nutricional: " + this.valorNutricional + "\n";            
    }


}

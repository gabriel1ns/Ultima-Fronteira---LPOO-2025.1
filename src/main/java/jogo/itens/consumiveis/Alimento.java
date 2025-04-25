package jogo.itens.consumiveis;

import jogo.itens.Item;
import jogo.personagem.Personagem;

public class Alimento extends Item implements Consumivel {
    private int valorNutricional;

    public Alimento(String tipo, int peso, int valorNutricional, int prazoDeValidade) {
        super(tipo, peso, prazoDeValidade);

        setValorNutricional(valorNutricional);
    }
        
    @Override
    public void consumir(Personagem personagem) {
        int fomeAtual = personagem.getFome();
        personagem.setFome(fomeAtual + this.valorNutricional);

        if(super.getDurabilidade() <= 0) {
            int vidaAtual = personagem.getVida();
            personagem.setVida(vidaAtual - this.valorNutricional);
        }

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
                "Tipo: Alimento\n" +
                "Valor nutricional: " + this.valorNutricional + "\n";            
    }


}

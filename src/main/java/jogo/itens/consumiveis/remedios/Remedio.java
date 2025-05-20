package jogo.itens.consumiveis.remedios;

import jogo.itens.consumiveis.Consumivel;
import jogo.personagem.Personagem;

public abstract class Remedio extends Consumivel {
    private int efeito;

    public Remedio(String nome, int peso, int durabilidade, int efeito) {
        super(nome, peso, durabilidade, 1);

        setEfeito(efeito);
    }

    @Override
    public void consumir(Personagem personagem) {
        if(super.getDurabilidade() > 0)
            personagem.setVida(personagem.getVida() + efeito);
    }

    final public void setEfeito(int efeito) {
        this.efeito = efeito;
    }

    public int getEfeito() {
        return efeito;
    }

    @Override
    public String toString() {
        return  super.toString() + 
                "Efeito: " + efeito + "\n";
    }


}

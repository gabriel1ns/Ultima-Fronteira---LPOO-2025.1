package jogo.itens.executaveis.armas;

import jogo.itens.Item;

public class Arma extends Item {
    private int dano;
    private int alcance;
    // TODO: adicionar sistema de municao
    // (no momento, todas as armas teriam "municao infinita")

    public Arma(String tipo, int peso, int durabilidade, int dano, int alcance) {
        super(tipo, peso, durabilidade);

        setDano(dano);
        setAlcance(alcance);
    }

    // Possivel implementacao
    /*
    public void usar(EventoCriatura criatura) {
        int distancia = criatura.getDistancia();
        
        if(this.alcance <= distancia) {
            int vidaCriaturaAtual = criatura.getVida();
            criatura.setVida(vidaCriaturaAtual - this.dano);
        }

        super.setDurabilidade(super.getDurabilidade() - 1);
    }
    */

    final public void setDano(int dano) {
        this.dano = dano;
    }

    final public void setAlcance(int alcance) {
        this.alcance = alcance;
    }

    public int getDano() {
        return dano;
    }

    public int getAlcance() {
        return alcance;
    }

    @Override
    public String toString() {
        return  super.toString +
                "Tipo: Arma\n" + 
                "Dano: " + this.dano + "\n" +
                "Alcance: " + this.alcance + "\n";
    }


}

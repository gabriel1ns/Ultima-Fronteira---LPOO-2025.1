package jogo.itens.armas;

import jogo.eventos.criatura.EventoCriatura;
import jogo.itens.Item;

public abstract class Arma extends Item {
    static private String TIPO = "Arma";

    private int dano;
    private int alcance;
    // TODO adicionar sistema de municao (no momento todas as armas teriam "municao infinita")

    public Arma(String nome, int peso, int durabilidade, int dano, int alcance) {
        super(nome, TIPO, peso, durabilidade, 1);

        setDano(dano);
        setAlcance(alcance);
    }

    public void usar(EventoCriatura criatura) {
        if(this.alcance >= criatura.getDistancia())
            criatura.setVida(criatura.getVida() - this.dano);

        super.setDurabilidade(super.getDurabilidade() - 1);
    }

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
        return  super.toString() +
                "Dano: " + this.dano + "\n" +
                "Alcance: " + this.alcance + "\n";
    }


}

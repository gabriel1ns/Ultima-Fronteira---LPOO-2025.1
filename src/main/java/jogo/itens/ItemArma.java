package jogo.itens;

import jogo.eventos.EventoCriatura;

public class ItemArma extends Item implements IItemPerecivel {
    static private String TIPO = "Arma";

    private final int dano;
    private final int alcance;

    private int durabilidade;
    // TODO adicionar sistema de municao

    public ItemArma(String nome, int peso, int durabilidade, int dano, int alcance) {
        super(nome, TIPO, peso, 1);

        this.dano = dano;
        this.alcance = alcance;
    }

    public void usar(EventoCriatura criatura) {
        if(this.alcance >= criatura.getDistancia())
            criatura.diminuirVida(dano);

        decrementarDurabilidade();
    }

    @Override
    public void decrementarDurabilidade() {
        setDurabilidade(getDurabilidade() - 1);
    }

    @Override
    public boolean estaPerecido() {
        return durabilidade <= 0;
    }

    public int getDurabilidade() {
        return durabilidade;
    }

    public void setDurabilidade(int durabilidade) {
        this.durabilidade = durabilidade;
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
                "Dano: " + dano + "\n" +
                "Alcance: " + alcance + "\n" + 
                "Durabilidade: " + durabilidade + " turnos\n";
    }


}

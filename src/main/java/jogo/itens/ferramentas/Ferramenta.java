package jogo.itens.ferramentas;

import jogo.itens.Item;

public abstract class Ferramenta extends Item {
    private static String TIPO = "Ferramenta";

    private int eficiencia;

    public Ferramenta(String nome, int eficiencia, int peso, int durabilidade) {
        super(nome, TIPO, peso, durabilidade, 1);

        setEficiencia(eficiencia);
    }

    final public void setEficiencia(int eficiencia) {
        this.eficiencia = eficiencia;
    }

    public int getEficiencia() {
        return eficiencia;
    }

    @Override
    public String toString() {
        return  super.toString() + 
                "Eficiencia: " + this.eficiencia + "\n";
    }


}

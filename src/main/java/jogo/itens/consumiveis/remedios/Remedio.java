package jogo.itens.consumiveis.remedios;

import jogo.itens.Item;

public class Remedio extends Item {
    private String tipo;
    private int efeito;

    public Remedio(String tipo, int peso, int durabilidade, int efeito) {
        super(tipo, peso, durabilidade);

        setTipo(tipo);
        setEfeito(efeito);
    }

    final public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    final public void setEfeito(int efeito) {
        this.efeito = efeito;
    }

    public String getTipo() {
        return tipo;
    }

    public int getEfeito() {
        return efeito;
    }

    @Override
    public String toString() {
        return  super.toString() + 
                "Tipo: Remedio\n" + 
                "Efeito: " + efeito + "\n";
    }


}

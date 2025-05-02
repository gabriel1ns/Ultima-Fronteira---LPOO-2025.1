package jogo.eventos.criatura;

import jogo.eventos.Evento;

public abstract class EventoCriatura extends Evento {
    private String tipo;
    private int vida;
    private int dano;
    private int distancia;

    public EventoCriatura(String tipo, String descricao, int vida, int dano, int distancia) {
        super(tipo, descricao, distancia);

        setVida(vida);
        setDano(dano);
        setDistancia(distancia);
    }

    final public void setVida(int vida) {
        this.vida = vida;
    }

    final public void setDano(int dano) {
        this.dano = dano;
    }

    final public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getVida() {
        return vida;
    }

    public int getDano() {
        return dano;
    }

    public int getDistancia() {
        return distancia;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "Vida: " + this.vida + "\n" +
                "Dano: " + this.dano + "\n" +
                "Distancia: " + this.distancia + "\n";
    }
}

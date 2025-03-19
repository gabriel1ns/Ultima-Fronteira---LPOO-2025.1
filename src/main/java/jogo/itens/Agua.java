package jogo.itens;

import jogo.personagem.Personagem;

public class Agua extends Item {
    private boolean pureza;
    private int volume;

    public Agua(boolean pureza, int volume) {
        super("Agua", volume);

        setPureza(pureza);
        setVolume(volume);
    }

    public void consumir(Personagem personagem) {
        personagem.alterarSede(volume);

        if(!pureza)
            personagem.alterarVida(-1 * volume);
    }

    final public void setPureza(boolean pureza) {
        this.pureza = pureza;
    }

    final public void setVolume(int volume) {
        this.volume = volume;
    }

    public boolean getPureza() {
        return this.pureza;
    }

    @Override
    public String toString() {
        return "Agua" + "\nPureza: " + pureza + "\nVolume: " + volume;
    }
}

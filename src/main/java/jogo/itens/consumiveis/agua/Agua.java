package jogo.itens.consumiveis.agua;

import jogo.itens.consumiveis.Consumivel;
import jogo.personagem.Personagem;

public class Agua extends Consumivel {
    private boolean pureza;
    private int volume;

    public Agua(boolean pureza, int volume) {
        super("Agua", 1, 2, volume);

        setPureza(pureza);
        setVolume(volume);
    }

    @Override
    public void consumir(Personagem personagem) {
        personagem.setSede(personagem.getSede() + this.volume);

        if(!this.pureza) 
            personagem.setVida(personagem.getVida() - volume);
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
        return  super.toString() +
                "Volume: " + this.volume + "\n" +
                "Pureza: " + this.pureza + "\n";
    }


}

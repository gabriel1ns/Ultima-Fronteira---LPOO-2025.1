package jogo.itens.consumiveis;

import jogo.itens.Item;
import jogo.personagem.Personagem;

public class Agua extends Item implements Consumivel {
    private boolean pureza;
    private int volume;

    public Agua(boolean pureza, int volume) {
        super("Agua", volume, 0);

        setPureza(pureza);
        setVolume(volume);
    }

    @Override
    public void consumir(Personagem personagem) {
        int sedeAtual = personagem.getSede();
        personagem.setSede(sedeAtual + this.volume);

        if(!pureza) {
            int vidaAtual = personagem.getVida();
            personagem.setVida(vidaAtual - volume);
        }
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
        return  super.toString +
                "Tipo: Agua\n" + 
                "Volume: " + this.volume + "\n" +
                "Pureza: " + this.pureza + "\n";
    }


}

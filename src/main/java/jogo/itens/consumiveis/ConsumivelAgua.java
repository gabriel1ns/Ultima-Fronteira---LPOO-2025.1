package jogo.itens.consumiveis;

import jogo.enums.personagem.AtributosEnum;
import jogo.personagem.Personagem;

public class ConsumivelAgua extends Consumivel {
    private boolean pureza;
    private int volume;

    public ConsumivelAgua(boolean pureza, int volume) {
        super("Agua", 2, volume, AtributosEnum.SEDE, volume);

        setPureza(pureza);
        setVolume(volume);
    }

    @Override
    public void consumir(Personagem personagem) {
        super.consumir(personagem);

        if(!getPureza()) 
            personagem.mudarAtributo(AtributosEnum.VIDA, volume);
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

    public int getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "Volume: " + volume + "\n" +
                "Pureza: " + (getPureza()? "pura" : "impura") + "\n";
    }


}

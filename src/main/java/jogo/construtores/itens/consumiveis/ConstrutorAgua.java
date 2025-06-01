package jogo.construtores.itens.consumiveis;

import jogo.enums.itens.consumiveis.AguaEnum;
import jogo.sistema.itens.consumiveis.ConsumivelAgua;

public class ConstrutorAgua {
    public static ConsumivelAgua construirAgua(AguaEnum agua, int volume) {
        return new ConsumivelAgua(agua.getPureza(), volume);
    }
}

package jogo.construtores.itens;

import jogo.enums.itens.ArmasEnum;
import jogo.itens.ItemArma;

public class ConstrutorArma {
    public static ItemArma construirArma(ArmasEnum arma, int quantidade) {
        return new ItemArma(arma.getNome(), arma.getPeso(), arma.getDurabilidade(), arma.getDano(), arma.getAlcance(), quantidade);
    }
}

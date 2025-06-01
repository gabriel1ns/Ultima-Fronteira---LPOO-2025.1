package jogo.construtores.itens.consumiveis;

import jogo.enums.itens.consumiveis.AlimentosEnum;
import jogo.itens.consumiveis.ConsumivelAlimento;

public class ConstrutorAlimento {
    public static ConsumivelAlimento construirAlimento(AlimentosEnum alimento, int quantidade) {
        return new ConsumivelAlimento(alimento.getNOME(), alimento.getPESO(), 
        alimento.getVALOR_NUTRICIONAL(), alimento.getPRAZO_DE_VALIDADE(), quantidade);
    }
}

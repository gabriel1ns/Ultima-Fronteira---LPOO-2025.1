package jogo.construtores.itens.consumiveis;

import jogo.enums.itens.consumiveis.AlimentosEnum;
import jogo.sistema.itens.consumiveis.ConsumivelAlimento;

public class ConstrutorAlimento {
    public static ConsumivelAlimento construirAlimento(AlimentosEnum alimento, int quantidade) {
        return new ConsumivelAlimento(
            alimento.getNome(), 
            alimento.getPeso(), 
            alimento.getValorNutricional(), 
            alimento.getPrazoDeValidade(), 
            quantidade
        );
    }
}

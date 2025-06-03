package jogo.construtores.itens.consumiveis;

import jogo.enums.itens.consumiveis.RemediosEnum;
import jogo.sistema.itens.consumiveis.ConsumivelRemedio;

public class ConstrutorRemedio {
    public static ConsumivelRemedio construirRemedio(RemediosEnum remedio, int quantidade) {
        return new ConsumivelRemedio(
            remedio.getNome(), 
            remedio.getPeso(), 
            remedio.getEfeito(), 
            quantidade
        );
    }
}

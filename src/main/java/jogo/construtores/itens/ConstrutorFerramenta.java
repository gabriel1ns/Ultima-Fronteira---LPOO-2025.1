package jogo.construtores.itens;

import jogo.enums.itens.FerramentasEnum;
import jogo.sistema.itens.ItemFerramenta;

public class ConstrutorFerramenta {
    public static ItemFerramenta construirFerramenta(FerramentasEnum ferramenta, int quantidade) {
        return new ItemFerramenta(ferramenta.getNOME(), ferramenta.getEFICIENCIA(), ferramenta.getPESO(), ferramenta.getDURABILIDADE(), quantidade);
    }
}

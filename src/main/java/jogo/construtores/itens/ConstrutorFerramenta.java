package jogo.construtores.itens;

import jogo.enums.itens.FerramentasEnum;
import jogo.itens.ItemFerramenta;

public class ConstrutorFerramenta {
    public static ItemFerramenta construirFerramenta(FerramentasEnum ferramenta) {
        return new ItemFerramenta(ferramenta.getNOME(), ferramenta.getEFICIENCIA(), ferramenta.getPESO(), ferramenta.getDURABILIDADE());
    }
}

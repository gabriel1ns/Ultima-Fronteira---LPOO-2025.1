package jogo.eventos.descoberta;

import jogo.itens.Item;
import jogo.itens.ferramentas.FerramentaMachado;
import jogo.itens.materiais.MaterialMadeira;

public class EventoDescobertaArvore extends EventoDescoberta {
    private static final String NOME                = "Arvore";
    private static final String DESCRICAO           = "";
    private static final Item[] ITENS_DESCOBERTOS   = {new MaterialMadeira(4)};
    private static final Item ITEM_NECESSARIO       = new FerramentaMachado();

    public EventoDescobertaArvore(){
        super(NOME, DESCRICAO, ITENS_DESCOBERTOS, ITEM_NECESSARIO);
    }
}

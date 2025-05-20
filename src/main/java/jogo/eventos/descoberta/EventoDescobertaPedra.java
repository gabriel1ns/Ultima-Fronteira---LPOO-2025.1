package jogo.eventos.descoberta;


import jogo.itens.Item;
import jogo.itens.ferramentas.FerramentaPicareta;
import jogo.itens.materiais.MaterialPedra;

public class EventoDescobertaPedra extends EventoDescoberta {
    private static final String NOME                = "Pedra";
    private static final String DESCRICAO           = "Uma pedra foi descoberta";
    private static final Item[] ITENS_DESCOBERTOS   = {new MaterialPedra(4)};
    private static final Item ITEM_NECESSARIO       = new FerramentaPicareta();

    public EventoDescobertaPedra(){
        super(NOME, DESCRICAO, ITENS_DESCOBERTOS, ITEM_NECESSARIO);
    }
}

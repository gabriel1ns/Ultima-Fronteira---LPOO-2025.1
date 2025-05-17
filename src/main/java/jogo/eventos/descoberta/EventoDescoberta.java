package jogo.eventos.descoberta;

import jogo.eventos.Evento;
import jogo.itens.Item;
import jogo.personagem.Personagem;

public abstract class EventoDescoberta extends Evento {
    private String nome;
    private Item[] recursosEncontrados;

    public EventoDescoberta(String tipo, String descricao, Item[] recursosEncontrados) {
        super(tipo, descricao, 1);

        setRecursosEncontrados(recursosEncontrados);
    }

    public void atualizarInventario(Personagem personagem, Item item) {
        personagem.getInventario().adicionarItem(item);
    }

    final public void setRecursosEncontrados(Item[] recursosEncontrados) {
        this.recursosEncontrados = recursosEncontrados;
    }

    public Item[] getRecursosEncontrados() {
        return recursosEncontrados;
    }

    @Override
    public String toString() {
        String itens = "";
        for(Item item : recursosEncontrados) {
            itens += item.getNome();
        }

        return  super.toString() +
                "Recursos encontrados: " + itens + "\n";
    }
}

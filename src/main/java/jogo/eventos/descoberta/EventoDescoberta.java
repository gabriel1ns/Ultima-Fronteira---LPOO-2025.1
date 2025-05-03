package jogo.eventos.descoberta;

import jogo.eventos.Evento;
import jogo.itens.Item;

public abstract class EventoDescoberta extends Evento {
    private String nome;
    private Item[] recursosEncontrados;

    public EventoDescoberta(String nome, String descricao, Item[] recursosEncontrados) {
        super(nome, descricao);

        setRecursosEncontrados(recursosEncontrados);
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

        return  super.toString + 
                "Recursos encontrados: " + itens + "\n";
    }
}

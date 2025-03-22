package jogo.eventos;

import jogo.ambiente.Ambiente;
import jogo.itens.Item;
import jogo.personagem.Personagem;

public class EventoDescoberta extends Evento {
    private String tipo;
    private Item[] recursosEncontrados;

    public EventoDescoberta(String tipo, String descricao, Item[] recursosEncontrados) {
        super(tipo, descricao);

        setRecursosEncontrados(recursosEncontrados);
    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {
        if(this.tipo.equals("FonteDeAgua")) {
            // adicionar agua no inventario do jogador
        } // ...
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

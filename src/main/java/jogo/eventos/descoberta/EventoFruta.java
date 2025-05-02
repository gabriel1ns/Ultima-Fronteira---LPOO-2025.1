package jogo.eventos.descoberta;

import jogo.ambiente.Ambiente;
import jogo.itens.Item;
import jogo.itens.consumiveis.alimentos.Fruta;
import jogo.personagem.Personagem;

public class EventoFruta extends EventoDescoberta{
    public EventoFruta(String tipo, String descricao, Item[] recursosEncontrados) {
        super(tipo, descricao, recursosEncontrados);
    }
    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {
        Fruta fruta = new Fruta();
        atualizarInventario(personagem,fruta);
    }

}

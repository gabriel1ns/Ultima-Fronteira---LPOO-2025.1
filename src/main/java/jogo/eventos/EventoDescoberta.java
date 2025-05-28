package jogo.eventos;

import jogo.ambientes.Ambiente;
import jogo.itens.Item;
import jogo.personagem.Personagem;
import jogo.sistema.Inventario;
import jogo.utils.InputOutput;

public class EventoDescoberta extends Evento {

    private final Item[] itensDescobertos;
    private final Item itemNecessario;

    public EventoDescoberta(String nome, String descricao, Item[] itensDescobertos, Item itemNecessario) {
        super(nome, descricao, 1);

        this.itensDescobertos = itensDescobertos;
        this.itemNecessario = itemNecessario;
    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {
        InputOutput io = new InputOutput();
        
        Inventario inventario = personagem.getInventario();

        int indice = inventario.encontrarItem(itemNecessario);

        if(indice == -1) {
            io.print(personagem.getNome() + " não tem " + this.itemNecessario);
            // TODO mensagem para item nao encontrado
            return;
        }else {
            io.print(personagem.getNome() + " utilizou " + this.itemNecessario);
        }

        for(Item item: this.itensDescobertos) {
            inventario.adicionarItem(item);
        }

        inventario.getItens().get(indice).decrementarDurabilidade();
    }

    @Override
    public String toString() {
        String itens = "";
        for(Item item : this.itensDescobertos) {
            itens += item.getNome();
        }

        return  super.toString() +
                "Recursos encontrados: " + itens + "\n";
    }
}

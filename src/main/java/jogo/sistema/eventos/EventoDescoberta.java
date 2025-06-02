package jogo.sistema.eventos;

import jogo.construtores.ConstrutorItem;
import jogo.sistema.Inventario;
import jogo.sistema.Personagem;
import jogo.sistema.itens.IItemPerecivel;
import jogo.sistema.itens.Item;
import jogo.utils.InputOutput;

public class EventoDescoberta extends Evento {

    private final Item[] itensDescobertos;
    private final IItemPerecivel itemNecessario;

    private final InputOutput io = new InputOutput();

    public EventoDescoberta(String nome, String descricao, Enum<?>[] itensDescobertosEnums, int[] quantidades, IItemPerecivel itemNecessario) {
        super(nome, descricao, 1);

        this.itensDescobertos = new Item[itensDescobertosEnums.length];
        this.itemNecessario = itemNecessario;

        int i = 0;
        for(Enum<?> itemDescobertoEnum: itensDescobertosEnums) {
            itensDescobertos[i] = ConstrutorItem.construir(itemDescobertoEnum, quantidades[i]);
            i++;
        }
    }

    @Override
    public void executar(Personagem personagem) {

        super.setDuracao(0);

        Inventario inventario = personagem.getInventario();

        if(itemNecessario != null) {
            Item item = (Item) itemNecessario;

            int indice = inventario.encontrarItem(item);

            if(indice == -1) {
                io.print(personagem.getNome() + " precisa de " + item.getNome() + " para coletar os recursos");
                return;
            }

            personagem.getGerenciadorDeInventario().usarPerecivel(indice);
            io.print(personagem.getNome() + " utilizou " + item.getNome());
        }

        for(Item item: this.itensDescobertos) {
            inventario.adicionarItem(item);
            io.print(personagem.getNome() + " coletou " + item.getNome());
        }

    }


    @Override
    public String toString() {
        String itens = "";
        for(Item item : this.itensDescobertos)
            itens += item.getNome() + ", ";


        if (itens.endsWith(", ")) {
            itens = itens.substring(0, itens.length() - 2);
        }

        return  super.toString() +
                "\nRecursos encontrados: " + itens;
    }

}

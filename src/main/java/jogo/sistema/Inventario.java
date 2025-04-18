package jogo.sistema;
import jogo.itens.Item;

public class Inventario {
    private Item[] itens;
    private int capacidadeMaxima = 10;
    private int quantidadeAtual;

    public Inventario(int capacidadeMaxima) {
        this.itens = new Item[capacidadeMaxima];
        this.capacidadeMaxima = capacidadeMaxima;
        this.quantidadeAtual = 0;
    }

    public boolean adicionarItem(Item item) {
        if (estaCheio())
            return false;

        itens[quantidadeAtual] = item;
        quantidadeAtual++;

        return true;
    }

    public boolean removerItem(Item item) {
        int indice = encontrarItem(item);
        boolean contemItem = indice >= 0;
        
        if(contemItem) {
            for (int i = indice; i < quantidadeAtual - 1; i++)
                itens[i] = itens[i + 1];

            itens[quantidadeAtual - 1] = null;
            quantidadeAtual--;
        }

        return contemItem;
    }


    public int encontrarItem(Item item) {
        int indice = -1;
        
        for (int i = 0; i < quantidadeAtual; i++) {
            if (itens[i] == item) {
                indice = i;
                break;
            }
        }

        return indice;
    }


    public Item getItem(int indice) {
        if (indice >= 0 && indice < quantidadeAtual) {
            return itens[indice];
        }

        // TODO: Adicionar excecao
        return null;
    }


    public int getCapacidadeMaxima() {
        return this.capacidadeMaxima;
    }


    public int getQuantidadeAtual() {
        return this.quantidadeAtual;
    }


    public boolean estaCheio() {
        return quantidadeAtual >= capacidadeMaxima;
    }


    public void esvaziar() {
        for (int i = 0; i < quantidadeAtual; i++) {
            itens[i] = null;
        }
        quantidadeAtual = 0;
    }


    public boolean usarItem(Item item) {
        int indice = encontrarItem(item);
        boolean contemItem = indice >= 0;

        if (contemItem) {
            /*
             * TODO: Definir como "usar" diferentes tipos de item
             * (uma ferramenta e combinar materiais, por exemplo)
             * (interface?)
             */
        }

        return contemItem;
    }


    public Item[] getItens() {
        return this.itens;
    }


    @Override
    public String toString() {
        String resultado = "Inventário (" + quantidadeAtual + "/" + capacidadeMaxima + "):\n";

        for (int i = 0; i < quantidadeAtual; i++) {
            Item item = itens[i];
            resultado += "- " + item.getNome();
            if (item.getQuantidade() > 1) {
                resultado += " (" + item.getQuantidade() + ")";
            }
            resultado += "\n";
        }

        return resultado;
    }
}


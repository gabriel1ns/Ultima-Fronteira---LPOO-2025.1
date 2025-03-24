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
        if (quantidadeAtual >= capacidadeMaxima) {
            return false;
        }

        itens[quantidadeAtual] = item;
        quantidadeAtual++;
        return true;
    }

    public boolean removerItem(Item item) {
        for (int i = 0; i < quantidadeAtual; i++) {
            if (itens[i] == item) {

                for (int j = i; j < quantidadeAtual - 1; j++) {
                    itens[j] = itens[j + 1];
                }
                itens[quantidadeAtual - 1] = null;
                quantidadeAtual--;
                return true;
            }
        }
        return false;
    }


    public boolean contemItem(Item item) {
        for (int i = 0; i < quantidadeAtual; i++) {
            if (itens[i] == item) {
                return true;
            }
        }
        return false;
    }


    public Item getItem(int indice) {
        if (indice >= 0 && indice < quantidadeAtual) {
            return itens[indice];
        }
        return null;
    }


    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }


    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }


    public boolean estaCheio() {
        return quantidadeAtual >= capacidadeMaxima;
    }


    public void limpar() {
        for (int i = 0; i < quantidadeAtual; i++) {
            itens[i] = null;
        }
        quantidadeAtual = 0;
    }


    public boolean usarItem(Item item) {
        if (contemItem(item)) {
            boolean resultado = item.usar();

            if (resultado && item.isConsumivel()) {
                removerItem(item);
            }

            return resultado;
        }

        return false;
    }


    public Item[] getTodosItens() {
        Item[] resultado = new Item[quantidadeAtual];
        for (int i = 0; i < quantidadeAtual; i++) {
            resultado[i] = itens[i];
        }
        return resultado;
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


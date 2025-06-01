package jogo.sistema;
import java.util.ArrayList;

import jogo.construtores.itens.ConstrutorArma;
import jogo.enums.ItensEnum;
import jogo.enums.itens.ArmasEnum;
import jogo.itens.Item;
import jogo.itens.ItemArma;
import jogo.itens.ItemFerramenta;
import jogo.itens.ItemMaterial;
import jogo.itens.consumiveis.Consumivel;

public class Inventario {
    private final int capacidadeMaxima;
    private int quantidadeItens;

    private ArrayList<Item>[] itens;

    public Inventario(int capacidadeMaxima, Item[] itensIniciais) {

        this.capacidadeMaxima = capacidadeMaxima;
        this.quantidadeItens = 0;

        itens = new ArrayList[]{
            new ArrayList<Item>(), 
            new ArrayList<ItemArma>(),
            new ArrayList<Consumivel>(),
            new ArrayList<ItemFerramenta>(),
            new ArrayList<ItemMaterial>()
        };

        // add Punhos na lista de armas
        itens[ItensEnum.ARMA.getIndice()].add(ConstrutorArma.construirArma(ArmasEnum.PUNHOS, 1));
        
        for(Item item: itensIniciais) {
            itens[0].add(item);
            itens[ItensEnum.valueOf(item.getTipo().toUpperCase()).getIndice()].add(item);
            quantidadeItens++;
        }

    }

    public int encontrarItem(Item item) {
        return encontrarItem(item, 0, 0, false);
    }

    public int encontrarItem(Item item, int indiceInicial, int indiceSubArray, boolean reverso) {
        if(indiceSubArray < 0 || indiceSubArray >= 1 + ItensEnum.values().length)
            return -1;
        if(indiceInicial < 0 || indiceInicial >= itens[indiceSubArray].size())
            return -1;

        int indice = -1;
        
        int limite = reverso? -1 : itens[indiceSubArray].size();
        int incremento = reverso? -1 : 1;
        
        for (int i = indiceInicial; i != limite; i += incremento) {
            if (( itens[indiceSubArray].get(i).getNome() ).equals( item.getNome() ))  {
                indice = i;
                break;
            }
        }

        return indice;
    }

    public void adicionarItem(Item item) {
        int N = ItensEnum.valueOf(item.getTipo().toUpperCase()).getIndice();

        if(item instanceof ItemMaterial) {
            int indiceEm0 = encontrarItem(item);
            int indiceEmN = encontrarItem(item, 0, N, false);

            while(indiceEm0 != -1 && item.getQuantidade() > 0) {
                int diff = Item.QUANTIDADE_MAXIMA - getItens().get(indiceEm0).getQuantidade();
    
                getItens().get(indiceEm0).mudarQuantidade(item.getQuantidade());
                getItens(N).get(indiceEmN).mudarQuantidade(item.getQuantidade());
                
                item.mudarQuantidade(-diff);

                indiceEm0 = encontrarItem(item, indiceEm0+1, 0, false);
                indiceEmN = encontrarItem(item, indiceEmN+1, N, false);

            }
        }
        

        if(item.getQuantidade() > 0) {
            getItens().add(item);
            getItens(N).add(item);
            alterarQuantidadeDeItens(+1);
        }

    }

    public boolean removerItem(int indiceEm0, int quantidade) {
        Item item = getItens().get(indiceEm0); 

        return removerItem(item, quantidade);
    }

    public boolean removerItem(Item item, int quantidade) {
        int N = ItensEnum.valueOf(item.getTipo().toUpperCase()).getIndice();

        int indiceEm0 = encontrarItem(item, getItens().size()-1, 0, true);
        int indiceEmN = encontrarItem(item, getItens(N).size()-1, N, true);
        
        if(indiceEm0 == -1) return false;

        getItens().get(indiceEm0).mudarQuantidade(-quantidade);

        if(getItens().get(indiceEm0).getQuantidade() == 0){
            getItens().remove(indiceEm0);
            getItens(N).remove(indiceEmN);

            alterarQuantidadeDeItens(-1);
        }

        return true;
    }

    public void alterarQuantidadeDeItens(int dQuantidade) {
        setQuantidadeItens(getQuantidadeItens() + dQuantidade);
    }

    public boolean estaCheio() {
        return quantidadeItens > capacidadeMaxima;
    }

    public boolean estaVazio() {
        return quantidadeItens == 0;
    }

    public ArrayList<Item> getItens() {
        return getItens(0);
    }
    
    public ArrayList<Item> getItens(int indiceSubArray) {
        return itens[indiceSubArray];
    }

    public int getCapacidadeMaxima() {
        return this.capacidadeMaxima;
    }

    public int getQuantidadeItens() {
        return this.quantidadeItens;
    }

    public void setQuantidadeItens(int quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }

    @Override
    public String toString() {
        String resultado = "Inventário (" + quantidadeItens + "/" + capacidadeMaxima + "):\n";

        for (int i = 0; i < quantidadeItens; i++) {
            Item item = itens[0].get(i);
            resultado += "- " + item.getNome();
            if (item.getQuantidade() > 1) {
                resultado += " (" + item.getQuantidade() + ")";
            }
            resultado += "\n";
        }

        return resultado;
    }
}


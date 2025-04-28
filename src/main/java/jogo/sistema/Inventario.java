package jogo.sistema;
import jogo.itens.Item;
import jogo.itens.consumiveis.Consumivel;
import jogo.itens.executaveis.armas.Arma;
import jogo.itens.executaveis.ferramentas.Ferramenta;
import jogo.itens.materiais.Material;
import jogo.personagem.Personagem;

public class Inventario {
    private Item[] itens;
    private int capacidadeMaxima;
    private int quantidadeItens;

    // TODO métodos para remoção de armas, ferramentas e consumíveis de seus subconteiners
    private Arma[] armas;
    private Ferramenta[] ferramentas;
    private Consumivel[] consumiveis;
    private int quantidadeArmas;
    private int quantidadeFerramentas;
    private int quantidadeConsumiveis;

    public Inventario(int capacidadeMaxima) {
        this.itens = new Item[capacidadeMaxima + 10];
        this.capacidadeMaxima = capacidadeMaxima;
        this.quantidadeItens = 0;

        this.armas = new Arma[capacidadeMaxima];
        this.armas[0] = new Punhos();
        this.quantidadeArmas = 1;
        this.ferramentas = new Ferramenta[capacidadeMaxima];
        this.quantidadeFerramentas = 0;
        this.consumiveis = new Consumivel[capacidadeMaxima];
        this.quantidadeConsumiveis = 0;
    }

    public boolean adicionarItem(Item item) {
        if(quantidadeItens == itens.length) 
            return false;
        
        itens[quantidadeItens] = item;
        quantidadeItens++;

        if(item instanceof Arma arma) {
            armas[quantidadeArmas] = arma;
            quantidadeArmas++;
        } else if(item instanceof Ferramenta ferramenta) {
            ferramentas[quantidadeFerramentas] = ferramenta;
            quantidadeFerramentas++;
        } else if(item instanceof Consumivel consumivel) {
            consumiveis[quantidadeConsumiveis] = consumivel;
            quantidadeConsumiveis++;
        }

        return true;
    }

    public boolean removerItem(Item item) {
        int indice = encontrarItem(item);
        boolean contemItem = indice >= 0;
        
        if(contemItem) {
            for (int i = indice; i < quantidadeItens - 1; i++)
                itens[i] = itens[i + 1];

            itens[quantidadeItens - 1] = null;
            quantidadeItens--;
        }

        return contemItem;
    }


    public int encontrarItem(Item item) {
        int indice = -1;
        
        for (int i = 0; i < quantidadeItens; i++) {
            if (itens[i].equals(item)) {
                indice = i;
                break;
            }
        }

        return indice;
    }


    public Item getItem(int indice) {
        if (indice >= 0 && indice < quantidadeItens) {
            return itens[indice];
        }

        // TODO: Adicionar excecao
        return null;
    }


    public int getCapacidadeMaxima() {
        return this.capacidadeMaxima;
    }


    public int getQuantidadeItens() {
        return this.quantidadeItens;
    }


    public boolean estaCheio() {
        return quantidadeItens >= capacidadeMaxima;
    }


    public void esvaziar() {
        for (int i = 0; i < quantidadeItens; i++) {
            itens[i] = null;
        }
        quantidadeItens = 0;
    }

    public boolean usarItemConsumivel(Consumivel consumivel, Personagem personagem) {
        int indice = encontrarItem(consumivel);
        if(indice < 0) return false;

        consumivel.consumir(personagem);
        removerItem(consumivel);

        return true;
    }

    public boolean usarItemMaterial(Material material, Material[] materiais) {
        int indice = encontrarItem(material);
        if(indice < 0) return false;

        removerItem(material);
        for(Material m : materiais) removerItem(m);

        adicionarItem(material.combinar(materiais));

        return true;
    }


    public Item[] getItens() {
        return this.itens;
    }

    public Arma[] getArmas() {
        return this.armas;
    }


    @Override
    public String toString() {
        String resultado = "Inventário (" + quantidadeItens + "/" + capacidadeMaxima + "):\n";

        for (int i = 0; i < quantidadeItens; i++) {
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


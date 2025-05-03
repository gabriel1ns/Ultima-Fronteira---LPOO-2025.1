package jogo.sistema;
import jogo.itens.Item;
import jogo.itens.armas.Arma;
import jogo.itens.consumiveis.IConsumivel;
import jogo.itens.ferramentas.Ferramenta;
import jogo.itens.materiais.Material;
import jogo.personagem.Personagem;
import jogo.utils.IntMath;

public class Inventario {
    private Item[] itens;
    private int capacidadeMaxima;
    private int quantidadeItens;

    // TODO métodos para remoção de armas, ferramentas e consumíveis de seus subconteiners
    private Arma[] armas;
    private Ferramenta[] ferramentas;
    private IConsumivel[] consumiveis;
    private Material[] materiais;
    private int quantidadeArmas;
    private int quantidadeFerramentas;
    private int quantidadeConsumiveis;
    private int quantidadeMateriais;

    public Inventario(int capacidadeMaxima) {
        this.itens = new Item[capacidadeMaxima + 10];
        this.capacidadeMaxima = capacidadeMaxima;
        this.quantidadeItens = 0;

        this.armas = new Arma[capacidadeMaxima];
        this.armas[0] = new Punhos();
        this.quantidadeArmas = 1;

        this.ferramentas = new Ferramenta[capacidadeMaxima];
        this.quantidadeFerramentas = 0;

        this.consumiveis = new IConsumivel[capacidadeMaxima];
        this.quantidadeConsumiveis = 0;
    
        this.materiais = new Material[capacidadeMaxima];
        this.quantidadeMateriais = 0;
    }

    public boolean combinarMateriais(Material[] materiaisCombinados) {
        int combinacaoID = 0;
        Item itemCombinado;

        for(Material material: materiaisCombinados) 
            combinacaoID += material.getQuantidade() * IntMath.pow(Item.QUANTIDADE_MAXIMA, material.getID());

        itemCombinado = Material.combinacoesPossiveis.get(combinacaoID);
        
        if((itemCombinado == null)) 
            return false;

        for(Material material: materiaisCombinados) 
            removerItem(material);
        
        adicionarItem(itemCombinado);

        return true;
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
        } else if(item instanceof IConsumivel consumivel) {
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

    public boolean usarItemConsumivel(IConsumivel consumivel, Personagem personagem) {
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


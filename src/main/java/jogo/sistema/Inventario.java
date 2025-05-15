package jogo.sistema;
import java.util.ArrayList;

import jogo.eventos.criatura.EventoCriatura;
import jogo.itens.Item;
import jogo.itens.armas.Arma;
import jogo.itens.consumiveis.Consumivel;
import jogo.itens.ferramentas.Ferramenta;
import jogo.itens.materiais.Material;
import jogo.personagem.Personagem;
import jogo.utils.IntMath;

public class Inventario {
    static enum InventarioEnum {
        ARMA(1),
        CONSUMIVEL(2),
        FERRAMENTA(3),
        MATERIAL(4);

        private int indice;

        private InventarioEnum(int indice) {
            this.indice = indice;
        }

        public int getIndice() {
            return indice;
        }
    }

    //private ArrayList<ArrayList<Item> > itens;
    private int capacidadeMaxima;
    private int quantidadeItens;

    // TODO métodos para remoção de armas, ferramentas e consumíveis de seus subconteiners
    private ArrayList<Item>[] itens;

    public Inventario(int capacidadeMaxima) {
        // itens = new ArrayList<>(5);

        // itens.set(0, new ArrayList<Item>());
        // itens.set(1, new ArrayList<Arma>());
        // itens.set(1, new ArrayList<Item>());
        // itens.set(1, new ArrayList<Item>());
        // itens.set(1, new ArrayList<Item>());

        itens = new ArrayList[]{
            new ArrayList<Item>(), 
            new ArrayList<Arma>(),
            new ArrayList<Consumivel>(),
            new ArrayList<Ferramenta>(),
            new ArrayList<Material>()
        };

        //his.itens = new Item[capacidadeMaxima + 10];
        this.capacidadeMaxima = capacidadeMaxima;
        this.quantidadeItens = 0;
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
            removerItem(material, material.getQuantidade());
        
        adicionarItem(itemCombinado);

        return true;
    }

    public void adicionarItem(Item item) {
        int N = InventarioEnum.valueOf(item.getTipo().toUpperCase()).getIndice();

        int indiceEm0 = encontrarItem(item, 0, 0, false);
        int indiceEmN = encontrarItem(item, 0, N, false);

        while(indiceEm0 != -1 && item.getQuantidade() > 0) {
            int diff = Item.QUANTIDADE_MAXIMA - itens[0].get(indiceEm0).getQuantidade();

            itens[0].get(indiceEm0).mudarQuantidade(item.getQuantidade());
            itens[N].get(indiceEmN).mudarQuantidade(item.getQuantidade());
            
            item.mudarQuantidade(-diff);

            indiceEm0 = encontrarItem(item, indiceEm0, 0, false);
            indiceEmN = encontrarItem(item, indiceEmN, N, false);
        }

        if(item.getQuantidade() > 0) {
            itens[0].add(item);
            itens[N].add(item);
        }

        quantidadeItens++;
    }

    public boolean removerItem(Item item, int quantidade) {
        int N = InventarioEnum.valueOf(item.getTipo().toUpperCase()).getIndice();

        int indiceEm0 = encontrarItem(item, 0, 0, true);
        int indiceEmN = encontrarItem(item, 0, N, true);
        
        if(indiceEm0 == -1) return false;

        while(indiceEm0 != -1 && quantidade > 0) {
            int diff = itens[0].get(indiceEm0).getQuantidade() - quantidade;

            itens[0].get(indiceEm0).mudarQuantidade(-quantidade);
            itens[N].get(indiceEmN).mudarQuantidade(-quantidade);

            if(diff <= 0){
                itens[0].remove(indiceEm0);
                itens[N].remove(indiceEmN);
            }

            quantidade = (diff <= 0)? -1*diff : 0;
        }

        quantidadeItens--;
        return true;
    }

    public int encontrarItem(Item item) {
        int indice = -1;
        
        for (int i = 0; i < quantidadeItens; i++) {
            if ((itens[0].get(i).getNome()).equals(item.getNome())) {
                indice = i;
                break;
            }
        }

        return indice;
    }

    public int encontrarItem(Item item, int indiceInicial, int indiceSubArray, boolean reverso) throws IndexOutOfBoundsException {
        if(indiceInicial < 0 || indiceInicial >= itens[indiceSubArray].size())
            throw new IndexOutOfBoundsException("'indiceInicial' fora dos limites");
        if(indiceSubArray < 0 || indiceSubArray >= 1 + InventarioEnum.values().length)
            throw new IndexOutOfBoundsException("'indiceSubArray' fora dos limites");
        
        int indice = -1;
        
        int limite = reverso? -1 : itens[indiceSubArray].size();
        int incremento = reverso? -1 : 1;
        
        for (int i = indiceInicial; i != limite; i += incremento) {
            if ((itens[indiceSubArray].get(i).getNome()).equals(item.getNome())) {
                indice = i;
                break;
            }
        }

        return indice;
    }

    public void usarItemArma(int indice, EventoCriatura criatura) {
        Arma arma = (Arma) itens[InventarioEnum.ARMA.getIndice()].get(indice);

        arma.usar(criatura);

        if(arma.getDurabilidade() <= 0)
            removerItem(arma, 1);

    }

    public boolean usarItemConsumivel(int indice, Personagem personagem) {
        Consumivel consumivel = (Consumivel) itens[InventarioEnum.CONSUMIVEL.getIndice()].get(indice);

        consumivel.consumir(personagem);
        removerItem(consumivel, 1);

        return true;
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

    public ArrayList<Item> getItens() {
        return itens[0];
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


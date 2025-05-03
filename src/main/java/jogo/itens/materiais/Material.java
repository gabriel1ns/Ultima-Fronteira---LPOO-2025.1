package jogo.itens.materiais;

import java.util.Map;

import jogo.itens.Item;
import jogo.itens.ferramentas.Ferramenta;

public abstract class Material extends Item {
    private static final Map<Integer, Item> combinacoes;
    
    private final int ID;
    
    public Material(String nome, int peso, int quantidade, int id) {
        super(nome, peso, 0, quantidade);

        this.ID = id;
    }
}

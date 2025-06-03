package jogo.sistema.itens;

public class ItemMaterial extends Item {

    private static final String TIPO = "Material";
    
    private final int ID;
    
    public ItemMaterial(String nome, int peso, int quantidade, int id) {
        super(nome, TIPO, peso, quantidade);

        this.ID = id;
    }

    public ItemMaterial(ItemMaterial material) {
        super(material.getNome(), TIPO, material.getPeso(), material.getQuantidade());

        this.ID = material.getID();
    }

    public int getID() {
        return ID;
    }
}

package jogo.itens.materiais;

public class MaterialPedra extends Material {
    private final static String NOME        = "Pedra";
    private final static int PESO           = 3;

    public MaterialPedra(int quantidade) {
        super(NOME, PESO, quantidade);
    }
}

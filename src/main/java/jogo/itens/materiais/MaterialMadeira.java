package jogo.itens.materiais;

public class MaterialMadeira extends Material {
    private final static int ID = 2;

    private final static String NOME        = "Madeira";
    private final static int PESO           = 2;

    public MaterialMadeira(int quantidade) {
        super(NOME, PESO, quantidade, ID);
    }
}

package jogo.itens.materiais;

public class MaterialFerro extends Material {
    private final static String NOME        = "Ferro";
    private final static int PESO           = 5;

    public MaterialFerro(int quantidade) {
        super(NOME, PESO, quantidade);
    }
}

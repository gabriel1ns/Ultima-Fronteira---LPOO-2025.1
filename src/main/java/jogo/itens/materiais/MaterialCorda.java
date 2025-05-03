package jogo.itens.materiais;

public class MaterialCorda extends Material {
    private final static int ID = 0;

    private final static String NOME        = "Corda";
    private final static int PESO           = 2;

    public MaterialCorda(int quantidade) {
        super(NOME, PESO, quantidade, ID);
    }
}

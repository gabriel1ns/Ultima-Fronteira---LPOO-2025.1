package jogo.itens.materiais;

public class MaterialCorda extends Material {
    private final static String TIPO        = "Corda";
    private final static int PESO           = 2;
    private final static int RESISTENCIA    = 5;

    public MaterialCorda() {
        super(TIPO, PESO, RESISTENCIA);
    }
}

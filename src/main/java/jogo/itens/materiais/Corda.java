package jogo.itens.materiais;

public class Corda extends Material {
    private final static String TIPO        = "Corda";
    private final static int PESO           = 2;
    private final static int RESISTENCIA    = 5;

    public Corda() {
        super(TIPO, PESO, RESISTENCIA);
    }
}

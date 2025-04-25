package jogo.itens.materiais;

public class Ferro extends Material {
    private final static String TIPO        = "Ferro";
    private final static int PESO           = 5;
    private final static int RESISTENCIA    = 10;

    public Ferro() {
        super(TIPO, PESO, RESISTENCIA);
    }
}

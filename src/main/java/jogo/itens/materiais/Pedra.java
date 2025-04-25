package jogo.itens.materiais;

public class Pedra extends Material {
    private final static String TIPO        = "Pedra";
    private final static int PESO           = 3;
    private final static int RESISTENCIA    = 5;

    public Pedra() {
        super(TIPO, PESO, RESISTENCIA);
    }
}

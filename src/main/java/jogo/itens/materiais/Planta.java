package jogo.itens.materiais;

public class Planta extends Material {
    private final static String TIPO        = "Planta";
    private final static int PESO           = 1;
    private final static int RESISTENCIA    = 1;

    public Planta() {
        super(TIPO, PESO, RESISTENCIA);
    }
}

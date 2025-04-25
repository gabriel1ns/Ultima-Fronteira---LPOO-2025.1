package jogo.itens.materiais;

public class Madeira extends Material {
    private final static String TIPO        = "Madeira";
    private final static int PESO           = 2;
    private final static int RESISTENCIA    = 2;

    public Madeira() {
        super(TIPO, PESO, RESISTENCIA);
    }
}

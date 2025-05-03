package jogo.itens.materiais;

public class MaterialFerro extends Material {
    private final static String TIPO        = "Ferro";
    private final static int PESO           = 5;
    private final static int RESISTENCIA    = 10;

    public MaterialFerro() {
        super(TIPO, PESO, RESISTENCIA);
    }
}

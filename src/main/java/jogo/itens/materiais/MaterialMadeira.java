package jogo.itens.materiais;

public class MaterialMadeira extends Material {
    private final static String TIPO        = "Madeira";
    private final static int PESO           = 2;
    private final static int RESISTENCIA    = 2;

    public MaterialMadeira() {
        super(TIPO, PESO, RESISTENCIA);
    }
}

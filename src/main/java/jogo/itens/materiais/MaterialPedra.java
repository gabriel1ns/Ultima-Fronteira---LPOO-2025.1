package jogo.itens.materiais;

public class MaterialPedra extends Material {
    private final static String TIPO        = "Pedra";
    private final static int PESO           = 3;
    private final static int RESISTENCIA    = 5;

    public MaterialPedra() {
        super(TIPO, PESO, RESISTENCIA);
    }
}

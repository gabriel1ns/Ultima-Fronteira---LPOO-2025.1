package jogo.itens.materiais;

public class MaterialPlanta extends Material {
    private final static String TIPO        = "Planta";
    private final static int PESO           = 1;
    private final static int RESISTENCIA    = 1;

    public MaterialPlanta() {
        super(TIPO, PESO, RESISTENCIA);
    }
}

package jogo.itens.materiais;

public class MaterialPlanta extends Material {
    private final static String NOME        = "Planta";
    private final static int PESO           = 1;

    public MaterialPlanta(int quantidade) {
        super(NOME, PESO, quantidade);
    }
}

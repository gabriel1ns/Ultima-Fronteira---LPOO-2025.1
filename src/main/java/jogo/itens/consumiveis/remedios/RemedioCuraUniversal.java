package jogo.itens.consumiveis.remedios;

public class RemedioCuraUniversal extends Remedio {
    private final static String NOME        = "Cura Universal";
    private final static int PESO           = 1;
    private final static int DURABILIDADE   = 100;
    private final static int EFEITO         = 100;

    public RemedioCuraUniversal() {
        super(NOME, PESO, DURABILIDADE, EFEITO);
    }
}

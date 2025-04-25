package jogo.itens.consumiveis.alimentos;

public class Fruta extends Alimento {
    private final static String TIPO            = "Banana";
    private final static int PESO               = 1;
    private final static int VALOR_NUTRICIONAL  = 5;
    private final static int DURABILIDADE       = 20;

    public Fruta() {
        super(TIPO, PESO, VALOR_NUTRICIONAL, DURABILIDADE);
    }
}

package jogo.itens.consumiveis.alimentos;

public class AlimentoFruta extends Alimento {
    private final static String NOME            = "Banana";
    private final static int PESO               = 1;
    private final static int VALOR_NUTRICIONAL  = 10;
    private final static int DURABILIDADE       = 20;

    public AlimentoFruta() {
        super(NOME, PESO, VALOR_NUTRICIONAL, DURABILIDADE);
    }
}

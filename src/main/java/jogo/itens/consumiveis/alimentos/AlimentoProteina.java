package jogo.itens.consumiveis.alimentos;

public class AlimentoProteina extends Alimento {
    private final static String TIPO            = "Carne";
    private final static int PESO               = 5;
    private final static int VALOR_NUTRICIONAL  = 10;
    private final static int DURABILIDADE       = 10;

    public AlimentoProteina() {
        super(TIPO, PESO, VALOR_NUTRICIONAL, DURABILIDADE);
    }
}

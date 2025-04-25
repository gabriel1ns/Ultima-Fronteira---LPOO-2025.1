package jogo.itens.consumiveis.alimentos;

public class Proteina extends Alimento {
    private final static String TIPO            = "Carne";
    private final static int PESO               = 5;
    private final static int VALOR_NUTRICIONAL  = 10;
    private final static int DURABILIDADE       = 10;

    public Proteina() {
        super(TIPO, PESO, VALOR_NUTRICIONAL, DURABILIDADE);
    }
}

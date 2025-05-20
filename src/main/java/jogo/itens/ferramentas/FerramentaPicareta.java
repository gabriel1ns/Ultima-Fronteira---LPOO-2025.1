package jogo.itens.ferramentas;

public class FerramentaPicareta extends Ferramenta {
    private final static String NOME        = "Picareta";
    private final static int EFICIENCIA     = 5;
    private final static int PESO           = 5;
    private final static int DURABILIDADE   = 10;
    
    public FerramentaPicareta() {
        super(NOME, EFICIENCIA, PESO, DURABILIDADE);
    }
}

package jogo.itens.executaveis.ferramentas;

public class Picareta extends Ferramenta {
    private final static String TIPO        = "Picareta";
    private final static int EFICIENCIA     = 5;
    private final static int PESO           = 5;
    private final static int DURABILIDADE   = 10;
    
    public Picareta() {
        super(TIPO, EFICIENCIA, PESO, DURABILIDADE);
    }
}

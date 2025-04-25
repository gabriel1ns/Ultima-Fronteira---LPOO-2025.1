package jogo.itens.executaveis.ferramentas;

public class Machado extends Ferramenta {
    private final static String TIPO        = "Machado";
    private final static int EFICIENCIA     = 5;
    private final static int PESO           = 5;
    private final static int DURABILIDADE   = 10;
    
    public Machado() {
        super(TIPO, EFICIENCIA, PESO, DURABILIDADE);
    }
}

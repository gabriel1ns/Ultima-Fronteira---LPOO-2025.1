package jogo.itens.ferramentas;

public class FerramentaMachado extends Ferramenta {
    private final static String NOME        = "Machado";
    private final static int EFICIENCIA     = 5;
    private final static int PESO           = 5;
    private final static int DURABILIDADE   = 10;
    
    public FerramentaMachado() {
        super(NOME, EFICIENCIA, PESO, DURABILIDADE);
    }
}

package jogo.itens.executaveis.armas;

public class ArcoFlecha extends Arma {
    private final static String TIPO        = "Arco e Flecha";
    private final static int PESO           = 10;
    private final static int DURABILIDADE   = 20;
    private final static int DANO           = 5;
    private final static int ALCANCE        = 20;
 
    public ArcoFlecha() {
        super(TIPO, PESO, DURABILIDADE, DANO, ALCANCE);
    }
}

package jogo.itens.armas;

public class ArmaArcoFlecha extends Arma {
    private final static String NOME        = "Arco e Flecha";
    private final static int PESO           = 10;
    private final static int DURABILIDADE   = 20;
    private final static int DANO           = 5;
    private final static int ALCANCE        = 20;
 
    public ArmaArcoFlecha() {
        super(NOME, PESO, DURABILIDADE, DANO, ALCANCE);
    }
}

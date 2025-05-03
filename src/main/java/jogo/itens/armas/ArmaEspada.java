package jogo.itens.armas;

public class ArmaEspada extends Arma {
    private final static String NOME        = "Espada";
    private final static int PESO           = 5;
    private final static int DURABILIDADE   = 10;
    private final static int DANO           = 10;
    private final static int ALCANCE        = 2;
 
    public ArmaEspada() {
        super(NOME, PESO, DURABILIDADE, DANO, ALCANCE);
    }
}

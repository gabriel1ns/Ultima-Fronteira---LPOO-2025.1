package jogo.itens.armas;

public class ArmaPunhos extends Arma { //TODO consertar atributos
    private final static String NOME        = "Punhos";
    private final static int PESO           = 0;
    private final static int DURABILIDADE   = 100000;
    private final static int DANO           = 5;
    private final static int ALCANCE        = 50;
 
    public ArmaPunhos() {
        super(NOME, PESO, DURABILIDADE, DANO, ALCANCE);
    }
}

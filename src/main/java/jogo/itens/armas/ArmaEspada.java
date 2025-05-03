package jogo.itens.armas;

public class ArmaEspada extends Arma {
    private final static String TIPO        = "Espada";
    private final static int PESO           = 5;
    private final static int DURABILIDADE   = 10;
    private final static int DANO           = 10;
    private final static int ALCANCE        = 2;
 
    public ArmaEspada() {
        super(TIPO, PESO, DURABILIDADE, DANO, ALCANCE);
    }

    // @Override
    // public String toString() {
    //     // TODO Auto-generated method stub
    //     return super.toString();
    // }
}

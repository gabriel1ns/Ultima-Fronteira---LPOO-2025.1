package jogo.itens.consumiveis.alimentos;

public class Proteina extends Alimento {
    private final static String TIPO                = "Carne";
    private final static int PESO                   = 5;
    private final static int VALOR_NUTRICIONAL      = 10;
    private final static int DURABILIDADE           = 10;
    private int quantidade;

    public Proteina() {
        super(TIPO, PESO, VALOR_NUTRICIONAL, DURABILIDADE);
        this.quantidade = 1;
    }

    public Proteina(int quantidade) {
        super(TIPO, PESO * quantidade, VALOR_NUTRICIONAL * quantidade, DURABILIDADE);
        this.quantidade = quantidade > 0 ? quantidade : 1;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return super.toString() + " - Quantidade: " + quantidade;
    }
}

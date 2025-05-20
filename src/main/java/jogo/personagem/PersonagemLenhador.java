package jogo.personagem;

public class PersonagemLenhador extends Personagem {
    private final static int MAX_VIDA = 100;
    private final static int MAX_FOME = 100;
    private final static int MAX_SEDE = 100;
    private final static int MAX_ENERGIA = 100;
    private final static int MAX_SANIDADE = 100;
    private final static int CAPACIDADE_DO_INVENTARIO = 10;

    public PersonagemLenhador(String nome) {
        super(nome, MAX_VIDA, MAX_FOME, MAX_SEDE, MAX_ENERGIA, MAX_SANIDADE, CAPACIDADE_DO_INVENTARIO);
    }
}

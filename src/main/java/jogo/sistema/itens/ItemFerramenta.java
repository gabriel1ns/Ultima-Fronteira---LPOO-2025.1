package jogo.sistema.itens;

public class ItemFerramenta extends Item implements IItemPerecivel {
    private static final String TIPO = "Ferramenta";

    private int durabilidade;

    public ItemFerramenta(String nome, int peso, int durabilidade, int quantidade) {
        super(nome, TIPO, peso, quantidade);

        this.durabilidade = durabilidade;
    }

    @Override
    public void decrementarDurabilidade() {
        setDurabilidade(getDurabilidade() - 1);
    }

    @Override
    public boolean estaPerecido() {
        return getDurabilidade() <= 0;
    }

    public int getDurabilidade() {
        return durabilidade;
    }

    public void setDurabilidade(int durabilidade) {
        this.durabilidade = durabilidade;
    }

    @Override
    public String toString() {
        return  super.toString() + 
                "Durabilidade: " + durabilidade + "\n";
    }


}

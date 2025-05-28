package jogo.itens;

public class ItemFerramenta extends Item implements IItemPerecivel {
    private static String TIPO = "Ferramenta";

    private final int eficiencia;
    private int durabilidade;

    public ItemFerramenta(String nome, int eficiencia, int peso, int durabilidade) {
        super(nome, TIPO, peso, 1);

        this.eficiencia = eficiencia;
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

    public int getEficiencia() {
        return eficiencia;
    }

    @Override
    public String toString() {
        return  super.toString() + 
                "Eficiencia: " + eficiencia + "\n" +
                "Durabilidade: " + durabilidade + "\n";
    }


}

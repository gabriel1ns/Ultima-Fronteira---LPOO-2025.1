package jogo.sistema.itens;

public class ItemArma extends Item implements IItemPerecivel {
    static private final String TIPO = "Arma";

    private final int dano;
    private final int alcance;
    private int durabilidade;
    
    public ItemArma(String nome, int peso, int durabilidade, int dano, int alcance, int quantidade) {
        super(nome, TIPO, peso, quantidade);

        this.dano = dano;
        this.alcance = alcance;
        this.durabilidade = durabilidade;
    }

    @Override
    public void decrementarDurabilidade() {
        setDurabilidade(getDurabilidade() - 1);
    }

    @Override
    public boolean estaPerecido() {
        return durabilidade <= 0;
    }

    public int getDurabilidade() {
        return durabilidade;
    }

    public void setDurabilidade(int durabilidade) {
        this.durabilidade = durabilidade;
    }

    public int getDano() {
        return dano;
    }

    public int getAlcance() {
        return alcance;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "Dano: " + dano + "\n" +
                "Alcance: " + alcance + "\n" + 
                "Durabilidade: " + durabilidade + " turnos\n";
    }


}

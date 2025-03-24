package jogo.itens;


public abstract class Item {
    protected String nome;
    protected int peso;
    protected int durabilidade;
    private boolean consumivel;
    protected int quantidade;

    protected String toString = "Nome: " + this.nome + "\n" +
            "Peso: " + this.peso + "\n" +
            "Durabilidade: " + this.durabilidade + "\n";

    public Item(String nome, int peso, int durabilidade) {
        setNome(nome);
        setPeso(peso);
        setDurabilidade(durabilidade);
    }

    final public void setNome(String nome) {
        this.nome = nome;
    }

    final public void setPeso(int peso) {
        this.peso = peso;
    }

    final public void setDurabilidade(int durabilidade) {
        this.durabilidade = durabilidade;
    }

    public String getNome() {
        return nome;
    }

    public int getPeso() { //caso tenha alguma interação futura, como uma espada mt pesada etc
        return peso;
    }

    public int getDurabilidade() {
        return durabilidade;
    }

    public boolean isConsumivel() {
        return consumivel;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public boolean usar() {
        return true;
    }
}




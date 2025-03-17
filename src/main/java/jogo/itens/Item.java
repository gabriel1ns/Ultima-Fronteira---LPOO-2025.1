package jogo.itens;

public abstract class Item {
    private String nome;
    private int peso;

    public Item(String nome, int peso) {
        this.nome = nome;
        this.peso = peso;
    }

    public String getNome() {
        return nome;
    }

    public int getPeso() {
        return peso;
    }

    public abstract void usar();
}

package jogo.itens;

public abstract class Item {
    private final static int QUANTIDADE_MAXIMA = 4;

    private String nome;
    private int peso;
    private int durabilidade;
    private int quantidade;

    public Item(String nome, int peso, int durabilidade, int quantidade) {
        setNome(nome);
        setPeso(peso);
        setDurabilidade(durabilidade);
        setQuantidade(quantidade);
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

    final public void setQuantidade(int quantidade) {
        if(quantidade > QUANTIDADE_MAXIMA) this.quantidade = QUANTIDADE_MAXIMA;
        else if(quantidade < 0) this.quantidade = 0;
        else this.quantidade = quantidade;
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

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return  "Nome: " + this.nome + "\n" + 
                "Peso: " + this.peso + "\n" + 
                "Durabilidade: " + this.durabilidade + "\n" + 
                "Quantidade: " + this.quantidade + "\n";
    }
}




package jogo.sistema.itens;

public abstract class Item {
    public final static int QUANTIDADE_MAXIMA = 4;

    final private String nome;
    final private String tipo;
    // TODO interação com o peso dos itens e o inventário
    final private int peso;

    private int quantidade;

    public Item(String nome, String tipo, int peso, int quantidade) {
        this.nome = nome;
        this.tipo = tipo;
        this.peso = peso;
        setQuantidade(quantidade);
    }

    public void mudarQuantidade(int dQuantidade) {
        setQuantidade(getQuantidade() + dQuantidade);
    }

    public String getNome() {
        return nome;
    }


    public String getTipo() {
        return tipo;
    }

    public int getPeso() { //caso tenha alguma interação futura, como uma espada mt pesada etc
        return peso;
    }

    public int getQuantidade() {
        return quantidade;
    }

    final public void setQuantidade(int quantidade) {
        if(quantidade > QUANTIDADE_MAXIMA) this.quantidade = QUANTIDADE_MAXIMA;
        else if(quantidade < 0) this.quantidade = 0;
        else this.quantidade = quantidade;
    }    

    @Override
    public String toString() {
        return  "Nome: " + this.nome + "\n" + 
                "Peso: " + this.peso + "\n" + 
                "Quantidade: " + this.quantidade + "\n";
    }
}




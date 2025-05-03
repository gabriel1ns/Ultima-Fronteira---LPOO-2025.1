package jogo.eventos.climatico;

import jogo.eventos.Evento;

public abstract class EventoClimatico extends Evento {
    private String nome;
    private int duracao;

    public EventoClimatico(String nome, String descricao, int duracao) {
        super(nome, descricao);

        setNome(nome);
        setDuracao(duracao);
    }

    final public void setNome(String nome) {
        this.nome = nome;
    }

    final public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getNome() {
        return nome;
    }

    public int getDuracao() {
        return duracao;
    }

    @Override
    public String toString() {
        return  super.toString + 
                "Duracao: " + this.duracao + "\n";
    }
}

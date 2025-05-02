package jogo.eventos;

import jogo.itens.Item;

public abstract class Evento implements EventoInterface {
    private String nome;
    private String descricao;
    private int duracao;
    private Item[] itensDescobertos;

    public Evento(String nome, String descricao, int duracao) {
        this.nome = nome;
        this.descricao = descricao;
        setDuracao(duracao);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public Item[] getItensDescobertos() {
        return itensDescobertos;
    }

    public void setItensDescobertos(Item[] itensDescobertos) {
        this.itensDescobertos = itensDescobertos;
    }
}
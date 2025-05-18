package jogo.eventos;

public abstract class Evento implements EventoInterface {
    private final String nome;
    private final String descricao;

    private int duracao;

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
}
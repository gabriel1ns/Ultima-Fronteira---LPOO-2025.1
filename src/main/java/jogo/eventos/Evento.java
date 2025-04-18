package jogo.eventos;

public abstract class Evento implements EventoInterface {
    private String nome;
    private String descricao;

    protected String toString = "Evento: " + nome + "\n" +
                                "Descricao: " + descricao + "\n";

    public Evento(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
} // criar as subclasses dps (dificuldade falar com monitora)
    // lol
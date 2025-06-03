package jogo.sistema;

public class Ambiente {
    private final String nome;
    private final String descricao;
    private final Enum<?>[] eventosPossiveis;
    private final int[] probabilidadeDeEventos;
    private final int dificuldadeDeExploracao;

    public Ambiente(String nome, String descricao, Enum<?>[] eventosPossiveis, int[] probabilidadeDeEventos, 
                    int dificuldadeDeExploracao) {
        this.nome = nome;
        this.descricao = descricao;
        this.eventosPossiveis = eventosPossiveis;
        this.probabilidadeDeEventos = probabilidadeDeEventos;
        this.dificuldadeDeExploracao = dificuldadeDeExploracao;

        assert(eventosPossiveis.length == probabilidadeDeEventos.length);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getDificuldadeDeExploracao() {
        return dificuldadeDeExploracao;
    }

    public Enum<?>[] getEventosPossiveis() {
        return eventosPossiveis;
    }
    
    public int[] getProbabilidadeDeEventos() {
        return probabilidadeDeEventos;
    }

    @Override
    public String toString() {
        return  nome + "\n" +
                descricao + "\n" +
                "Dificuldade de exploração: " + dificuldadeDeExploracao + "\n";
    }

}

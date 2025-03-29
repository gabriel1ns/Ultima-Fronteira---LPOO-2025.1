package jogo.ambiente;

import jogo.eventos.Evento;
import jogo.gerenciadores.GerenciadorDeEventos;
import jogo.itens.Item;
import jogo.personagem.Personagem;

public abstract class Ambiente {
    private String nome;
    private String descricao;
    private int dificuldadeDeExploracao = 5;

    private Item[] recursosDisponiveis;
    private Evento[] eventosPossiveis;
    private int[] probabilidadeDeEventos;

    protected String toString = "Nome: " + this.nome + "\n" +
                                "Descricao: " + this.descricao;

    public Ambiente(Item[] recursosDisponiveis) {
        setRecursosDisponiveis(recursosDisponiveis);
    }

    public void explorar(Personagem personagem) {
        int energiaAtual = personagem.getEnergia();
        personagem.setEnergia(energiaAtual - dificuldadeDeExploracao);

        gerarEvento(personagem);
    }

    public void gerarEvento(Personagem personagem) {
        GerenciadorDeEventos gerenciador = new GerenciadorDeEventos(eventosPossiveis, probabilidadeDeEventos);
        Evento evento = gerenciador.sortearEvento();
        
        evento.executar(this, personagem);
    }

    final public void setNome(String nome) {
        this.nome = nome;
    }

    final public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    final public void setDificuldadeDeExploracao(int dificuldadeDeExploracao) {
        this.dificuldadeDeExploracao = dificuldadeDeExploracao;
    }

    final public void setRecursosDisponiveis(Item[] recursosDisponiveis) {
        this.recursosDisponiveis = recursosDisponiveis;
    }

    final public void setEventosPossiveis(Evento[] eventosPossiveis) {
        this.eventosPossiveis = eventosPossiveis;
    }

    final public void setProbabilidadeDeEventos(int[] probabilidadeDeEventos) {
        this.probabilidadeDeEventos = probabilidadeDeEventos;
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

    public Item[] getRecursosDisponiveis() {
        return recursosDisponiveis;
    }

    public Evento[] getEventosPossiveis() {
        return eventosPossiveis;
    }
    
    public int[] getProbabilidadeDeEventos() {
        return probabilidadeDeEventos;
    }

    @Override
    public String toString() {
        return nome + ": " + descricao;
    }
}

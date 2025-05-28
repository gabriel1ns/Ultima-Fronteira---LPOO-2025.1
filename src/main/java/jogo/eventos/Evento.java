package jogo.eventos;

import jogo.ambientes.Ambiente;
import jogo.personagem.Personagem;

public abstract class Evento {
    private final String nome;
    private final String descricao;

    private int duracao;

    public Evento(String nome, String descricao, int duracao) {
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
    }

    public abstract void executar(Ambiente ambiente, Personagem personagem);

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

    @Override
    public String toString() {
        return  "Nome: " + nome + "\n" + 
                "Descrição: " + descricao + "\n" + 
                "Duração: " + duracao + " turnos";
    }

    
}
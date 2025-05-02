package jogo.eventos.climatico;

import jogo.eventos.Evento;
import jogo.personagem.Personagem;

public abstract class EventoClimatico extends Evento {
    private String tipo;
    private int duracao;

    public EventoClimatico(String tipo, String descricao, int duracao) {
        super(tipo, descricao, duracao);
        setTipo(tipo);
        setDuracao(duracao);
    }

    final public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    final public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getTipo() {
        return tipo;
    }

    public int getDuracao() {
        return duracao;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "Duracao: " + this.duracao + "\n";
    }


}

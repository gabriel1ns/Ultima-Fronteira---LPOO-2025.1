package jogo.eventos;

import jogo.ambiente.Ambiente;
import jogo.personagem.Personagem;

public class EventoClimatico extends Evento {
    private String tipo;
    private int duracao;

    public EventoClimatico(String tipo, String descricao, int duracao) {
        super(tipo, descricao);

        setTipo(tipo);
        setDuracao(duracao);
    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {
        if(this.tipo.equals("Calor")) {
            // aumentar consumo de sede
        } // ...
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
        return  super.toString + 
                "Duracao: " + this.duracao + "\n";
    }
}

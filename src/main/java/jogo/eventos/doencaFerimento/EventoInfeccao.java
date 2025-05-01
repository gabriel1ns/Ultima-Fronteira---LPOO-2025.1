package jogo.eventos.doencaFerimento;

import jogo.ambiente.Ambiente;
import jogo.personagem.Personagem;

public class EventoInfeccao extends EventoDoencaFerimento{
    private int progressao;
    private int estadoAtual;

    public EventoInfeccao(int progressao, int estadoAtual) {
        super("Infecção", "", estadoAtual, " ");
        this.progressao = progressao;
        this.estadoAtual = estadoAtual;
    }


    public int getProgressao() {
        return progressao;
    }

    public void setProgressao(int progressao) {
        this.progressao = progressao;
    }

    public int getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(int estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {

    }

    @Override
    public void aplicarFerimento(Personagem personagem) {

    }
}

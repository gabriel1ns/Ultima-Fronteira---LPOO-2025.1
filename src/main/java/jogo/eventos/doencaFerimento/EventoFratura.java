package jogo.eventos.doencaFerimento;

import jogo.ambiente.Ambiente;
import jogo.personagem.Personagem;

public class EventoFratura extends EventoDoencaFerimento {
    private int efeitoSanidade;

    public EventoFratura(int impacto, String curaDisponivel, int efeitoSanidade){
        super("Fratura", " ", impacto, curaDisponivel);
    }


    @Override
    public void aplicarFerimento(Personagem personagem) {
        if (personagem == null) return;

        personagem.setVida(personagem.getVida() - getImpacto());
        personagem.setSanidade(personagem.getSanidade() - efeitoSanidade);
    }

    // fazer metodo para tratar o ferimento dps

    public int getEfeitoSanidade() {
        return efeitoSanidade;
    }

    public void setEfeitoSanidade(int efeitoSanidade) {
        this.efeitoSanidade = efeitoSanidade;
    }


    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {

    }


}


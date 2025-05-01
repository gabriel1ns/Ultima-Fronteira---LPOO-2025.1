package jogo.eventos.criatura;

import jogo.ambiente.Ambiente;
import jogo.personagem.Personagem;

public class EventoCobra extends EventoCriatura{
    private int poderAtaque;

    public EventoCobra(String tipo, String descricao, int vida, int dano, int distancia) {
        super(tipo, descricao, vida, dano, distancia);
        setPoderAtaque(poderAtaque);
    }

    public EventoCobra(String descricao, int vida, int dano) {
        super("Cobra", descricao, vida, dano,3);
        setPoderAtaque(dano);
    }

    public boolean atacar(EventoCriatura alvo) {
        if (getDistancia() <= 5) {
            int danoTotal = getDano() + poderAtaque;
            alvo.setVida(alvo.getVida() - danoTotal);
            return true;
        }
        return false;
    }


    public int getPoderAtaque() {
        return poderAtaque;
    }

    public void setPoderAtaque(int poderAtaque) {
        this.poderAtaque = poderAtaque;
    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {

    }
}

package jogo.eventos.criatura;


import jogo.ambiente.Ambiente;
import jogo.personagem.Personagem;

public class EventoUrso extends EventoCriatura {
    private int poderAtaque;

    public EventoUrso(String tipo, String descricao, int vida, int dano, int distancia, int poderAtaque) {
        super(tipo, descricao, vida, dano, distancia);
        setPoderAtaque(poderAtaque);
    }


    public EventoUrso(String descricao, int vida, int dano) {
        super("Urso", descricao, vida, dano, 3);
        setPoderAtaque(dano * 5);
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

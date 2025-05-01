package jogo.eventos.criatura;


import jogo.ambiente.Ambiente;
import jogo.itens.consumiveis.alimentos.Proteina;
import jogo.personagem.Personagem;

public class EventoLobo extends EventoCriatura {
    private int poderAtaque;
    private int proteinaColetada;

    public EventoLobo(String tipo, String descricao, int vida, int dano, int distancia, int poderAtaque) {
        super(tipo, descricao, vida, dano, distancia);
        setPoderAtaque(poderAtaque);
        this.proteinaColetada = 0;
    }


    public EventoLobo(String descricao, int vida, int dano) {
        super("Lobo", descricao, vida, dano, 3);
        setPoderAtaque(dano * 2);
        this.proteinaColetada = 0;
    }

    public boolean atacar(EventoCriatura alvo) {
        if (getDistancia() <= 5) {
            int danoTotal = getDano() + poderAtaque;
            alvo.setVida(alvo.getVida() - danoTotal);
            proteinaColetada += 1;
            return true;
        }
        return false;
    }

    public Proteina droparProteina() {
        if (proteinaColetada > 0) {proteinaColetada--;
            return new Proteina(1);
        }
        return null;
    }


    public final void setPoderAtaque(int poderAtaque) {
        this.poderAtaque = poderAtaque > 0 ? poderAtaque : 1;
    }

    public int getPoderAtaque() {
        return poderAtaque;
    }

    public int getProteinaColetada() {
        return proteinaColetada;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Poder de Ataque: " + this.poderAtaque + "\n" +
                "Proteína Coletada: " + this.proteinaColetada + "\n";
    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {

    }
}
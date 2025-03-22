package jogo.eventos;

import jogo.ambiente.Ambiente;
import jogo.personagem.Personagem;

public class EventoCriatura extends Evento {
    private String tipo;
    private int vida;
    private int dano;
    private int distancia;

    public EventoCriatura(String tipo, String descricao, int vida, int dano, int distancia) {
        super(tipo, descricao);

        setVida(vida);
        setDano(dano);
        setDistancia(distancia);
    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {
        if(this.vida >= 0) {
            int vidaAtual = personagem.getVida();
            personagem.setVida(vidaAtual - this.dano);
        }        

        if(this.tipo.equals("Cobra")) {
            // aplicar EventoDoencaFerimento do tipo veneno
        } // ...
    }

    final public void setVida(int vida) {
        this.vida = vida;
    }

    final public void setDano(int dano) {
        this.dano = dano;
    }

    final public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getVida() {
        return vida;
    }

    public int getDano() {
        return dano;
    }

    public int getDistancia() {
        return distancia;
    }

    @Override
    public String toString() {
        return  super.toString +
                "Vida: " + this.vida + "\n" + 
                "Dano: " + this.dano + "\n" +
                "Distancia: " + this.distancia + "\n";
    }
}

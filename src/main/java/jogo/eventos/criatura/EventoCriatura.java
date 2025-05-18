package jogo.eventos.criatura;

import jogo.eventos.Evento;
import jogo.itens.consumiveis.alimentos.AlimentoProteina;
import jogo.personagem.Personagem;

public abstract class EventoCriatura extends Evento {
    private String tipo;
    private int vida;
    private int dano;
    private int distancia;
    private int QuantProteina;
    private int duracao;

    public EventoCriatura(String tipo, String descricao, int vida, int dano, int distancia, int duracao) {
        super(tipo, descricao, duracao);

        setVida(vida);
        setDano(dano);
        setDistancia(distancia);
    }

    public void atacarPersonagem (Personagem personagem) {
        int vidaAtual = personagem.getVida();
        int vidaAtaque = vidaAtual - this.dano;
        personagem.setVida(vidaAtaque);
    }

    public void adicionarProteina(Personagem personagem) {
        if (this.getVida() <= 0) {

            AlimentoProteina proteina = new AlimentoProteina();
            personagem.getInventario().adicionarItem(proteina);
        }
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    protected abstract int getQuantProteina();


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
    public int getDuracao() {
        return duracao;
    }

    @Override
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "Vida: " + this.vida + "\n" +
                "Dano: " + this.dano + "\n" +
                "Distancia: " + this.distancia + "\n";
    }
}

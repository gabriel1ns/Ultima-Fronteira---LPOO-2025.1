package jogo.eventos;

import jogo.enums.personagem.AtributosEnum;
import jogo.itens.ItemArma;
import jogo.personagem.Personagem;
import jogo.utils.InputOutput;

public class EventoCriatura extends Evento {

    private final InputOutput io = new InputOutput();

    private int vida;
    private int dano;
    private int distancia;

    public EventoCriatura(String nome, String descricao, int vida, int dano, int distancia) {
        super(nome, descricao, 1);

        setVida(vida);
        setDano(dano);
        setDistancia(distancia);
    }

    @Override
    public void executar(Personagem personagem) {
        io.print(super.getNome() + " ataca " + personagem.getNome() + "!");

        personagem.mudarAtributo(AtributosEnum.VIDA, -1*dano);
    }

    private void adicionarEspolios(Personagem personagem) {
        // TODO
    }

    public boolean serAtacada(ItemArma arma, Personagem personagem) {

        if(arma.getAlcance() < getDistancia())
            return false;

        setVida(getVida() - arma.getDano());

        if(getVida() <= 0) {
            io.print(personagem.getNome() + " derrotou " + super.getNome());

            super.setDuracao(0);
            adicionarEspolios(personagem);
        }

        return true;
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
        return  super.toString() +
                "Vida: " + this.vida + "\n" +
                "Dano: " + this.dano + "\n" +
                "Distancia: " + this.distancia + "\n";
    }
}

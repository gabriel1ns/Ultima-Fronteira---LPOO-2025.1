package jogo.eventos;

import jogo.Ambiente;
import jogo.construtores.itens.consumiveis.ConstrutorAlimento;
import jogo.enums.itens.consumiveis.AlimentosEnum;
import jogo.enums.personagem.AtributosEnum;
import jogo.itens.consumiveis.ConsumivelAlimento;
import jogo.personagem.Personagem;

public class EventoCriatura extends Evento {

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
    public void executar(Ambiente ambiente, Personagem personagem) {
        personagem.mudarAtributo(AtributosEnum.VIDA, -1*dano);
    }

    public void adicionarProteina(Personagem personagem) {
        if (this.getVida() <= 0) {

            ConsumivelAlimento carne = ConstrutorAlimento.construirAlimento(AlimentosEnum.CARNE);
            personagem.getInventario().adicionarItem(carne);
        }
    }

    public void diminuirVida(int dVida) {
        assert(dVida >= 0);

        setVida(getVida() - dVida);

        if(getVida() <= 0) {
            super.setDuracao(0);
        }
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
        return  "Nome: " + super.getNome() + "\n" +
                "Vida: " + this.vida + "\n" +
                "Dano: " + this.dano + "\n" +
                "Distancia: " + this.distancia + "\n";
    }
}

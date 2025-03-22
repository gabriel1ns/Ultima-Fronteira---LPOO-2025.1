package jogo.itens;

import jogo.ambiente.Ambiente;
import jogo.personagem.Personagem;

public class Ferramenta extends Item {
    private String tipo;
    private int eficiencia;

    public Ferramenta(String tipo, int eficiencia, int peso, int durabilidade) {
        super(tipo, peso, durabilidade);

        setTipo(tipo);
        setEficiencia(eficiencia);
    }

    // combinacao de materiais
    public Ferramenta(String tipo, int eficiencia) {
        super(tipo, 0, 0);

        setTipo(tipo);
        setEficiencia(eficiencia);
    }

    public void usar(Ambiente ambiente, Personagem personagem) {
        if(tipo.equals("Machado")) {
            // alterar reserva de madeira do ambiente e aumentar reserva do jogador
        } // ...
    }

    final public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    final public void setEficiencia(int eficiencia) {
        this.eficiencia = eficiencia;
    }

    public String getTipo() {
        return tipo;
    }

    public int getEficiencia() {
        return eficiencia;
    }

    @Override
    public String toString() {
        return  super.toString + 
                "Tipo: Ferramenta\n" +
                "Eficiencia: " + this.eficiencia + "\n";
    }
}

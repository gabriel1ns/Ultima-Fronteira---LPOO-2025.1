package jogo.eventos;

import jogo.ambiente.Ambiente;
import jogo.personagem.Personagem;

public class EventoDoencaFerimento extends Evento {
    private String tipo;
    private int impacto;
    private String curaDisponivel;

    public EventoDoencaFerimento(String tipo, String descricao, int impacto, String curaDisponivel) {
        super(tipo, descricao);

        setImpacto(impacto);
        setCuraDisponivel(curaDisponivel);
    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {
        if(this.tipo.equals("Infeccao")) {
            // aumentar taxa de decaimento dos atributos
        } // ...
    }

    final public void setImpacto(int impacto) {
        this.impacto = impacto;
    }

    final public void setCuraDisponivel(String curaDisponivel) {
        this.curaDisponivel = curaDisponivel;
    }

    public int getImpacto() {
        return impacto;
    }

    public String getCuraDisponivel() {
        return curaDisponivel;
    }

    @Override
    public String toString() {
        return  super.toString + 
                "Impacto: " + this.impacto + "\n" +
                "Cura disponivel: " + this.curaDisponivel + "\n";
    }

}

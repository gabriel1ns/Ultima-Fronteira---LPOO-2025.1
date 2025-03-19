package jogo.itens;

import jogo.personagem.Personagem;

public class Alimento extends Item {
    private int valorNutricional;
    private String tipo;
    private int prazoDeValidade;

    public Alimento(String tipo, int peso, int valorNutricional, int prazoDeValidade) {
        super(tipo, peso);
        setTipo(tipo);
        setValorNutricional(valorNutricional);
        setPrazoDeValidade(prazoDeValidade);
    }
        
    @Override
    public void consumir(Personagem personagem) {
        personagem.alterarFome(valorNutricional);

        if(prazoDeValidade <= 0)
            personagem.alterarVida(-1 * valorNutricional);

    }

    public void decrementarPrazoDeValidade() {
        this.prazoDeValidade--;
    }

    final public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    final public void setValorNutricional(int valorNutricional) {
        this.valorNutricional = valorNutricional;
    }

    final public void setPrazoDeValidade(int prazoDeValidade) {
        this.prazoDeValidade = prazoDeValidade;
    }

    @Override
    public String toString() {
        return "Alimento: " + tipo 
                + "\nValor Nutricional: " + valorNutricional
                + "\nPrazo de validade: " + prazoDeValidade;
    }
    
}

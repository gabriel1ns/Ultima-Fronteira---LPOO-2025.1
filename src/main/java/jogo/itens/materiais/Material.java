package jogo.itens.materiais;

import java.util.Map;

import jogo.itens.Item;
import jogo.itens.executaveis.ferramentas.Ferramenta;

public class Material extends Item {
    final private Map<String, Item> combinacoes = Map.of(
        "FerroMadeira", new Ferramenta("Machado", 5)
    );
    
    private String tipo;
    private int resistencia;

    public Material(String tipo, int peso, int resistencia) {
        super(tipo, peso, 0);
        
        setTipo(tipo);
        setResistencia(resistencia);
    }

    public Item combinar(Material material2) {
        Item novoItem;
        String tipo2 = material2.getTipo();
        
        // Quando a.compareTo(b) < 0 --> a < b
        String combinacao = this.tipo.compareTo(tipo2) < 0 ? this.tipo + tipo2 : tipo2 + this.tipo;
        int novaDurabilidade = this.resistencia + material2.getResistencia();
        
        novoItem = combinacoes.get(combinacao);
        novoItem.setDurabilidade(novaDurabilidade);
        novoItem.setPeso(novaDurabilidade);

        return novoItem;
    }

    final public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    final public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public String getTipo() {
        return tipo;
    }

    public int getResistencia() {
        return resistencia;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "Resistencia: " + resistencia + "\n";
    }




}

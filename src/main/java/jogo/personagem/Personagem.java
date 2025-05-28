package jogo.personagem;
import jogo.enums.personagem.AtributosEnum;
import jogo.itens.Item;
import jogo.sistema.Inventario;

public class Personagem {
    
    private static final int    MIN_ATRIBUTO = 0;
    private final int[]         MAX_ATRIBUTOS;

    final private String        nome;
    private int[]               atributos = new int[AtributosEnum.values().length];
    private Inventario          inventario;

    public Personagem(String nome, int maxVida, int maxFome, int maxSede, int maxEnergia, int maxSanidade, 
    int capacidadeDoInventario, Item[] itensIniciais) {
        
        this.nome = nome;
        
        MAX_ATRIBUTOS = new int[]{maxVida, maxFome, maxSede, maxEnergia, maxSanidade};
        
        atributos[AtributosEnum.VIDA.getIndice()] = maxVida;
        atributos[AtributosEnum.FOME.getIndice()] = maxFome;
        atributos[AtributosEnum.SEDE.getIndice()] = maxSede;
        atributos[AtributosEnum.ENERGIA.getIndice()] = maxEnergia;
        atributos[AtributosEnum.SANIDADE.getIndice()] = maxSanidade;
        
        this.inventario = new Inventario(capacidadeDoInventario, itensIniciais);
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void mudarAtributo(AtributosEnum atributo, int valor) {
        valor = normalizarValorAtributo(atributo, valor);

        atributos[atributo.getIndice()] = valor;
    }

    public int getAtributo(AtributosEnum atributo) {
        return atributos[atributo.getIndice()];
    }

    private int normalizarValorAtributo(AtributosEnum atributo, int valor) {
        int indice = atributo.getIndice();
        
        if(valor < MIN_ATRIBUTO) return MIN_ATRIBUTO;
        if(valor > MAX_ATRIBUTOS[indice]) return MAX_ATRIBUTOS[indice];
        return valor;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {

        String ret = "";

        for(AtributosEnum atributo: AtributosEnum.values())
            ret += atributo.name() + ": " + atributos[atributo.getIndice()] + "\n";
        ret += "Inventário: " + getInventario().toString();

        return ret;
    }
}


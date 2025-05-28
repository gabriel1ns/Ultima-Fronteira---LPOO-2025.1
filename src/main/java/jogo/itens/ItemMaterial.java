package jogo.itens;

import java.util.Map;

import jogo.enums.itens.MateriaisEnum;
import jogo.itens.armas.ArmaArcoFlecha;
import jogo.itens.armas.ArmaEspada;
import jogo.itens.ferramentas.FerramentaMachado;
import jogo.itens.ferramentas.FerramentaPicareta;
import jogo.utils.IntMath;

public class ItemMaterial extends Item {
    

    private static String TIPO = "Material";
    private static final int QUANTIDADE_MAXIMA = Item.QUANTIDADE_MAXIMA;

    public static final Map<Integer, Item> combinacoesPossiveis = Map.of(
        // sum(id, id=idMax, quantidade_id * QMAX^id), itemCorrespodente
        
        // MACHADO
        2 * IntMath.pow(QUANTIDADE_MAXIMA, MateriaisEnum.MADEIRA.getId()) + 
        2 * IntMath.pow(QUANTIDADE_MAXIMA, MateriaisEnum.PEDRA.getId()), 
        new FerramentaMachado(),

        // PICARETA
        2 * IntMath.pow(QUANTIDADE_MAXIMA, MateriaisEnum.MADEIRA.getId()) +
        3 * IntMath.pow(QUANTIDADE_MAXIMA, MateriaisEnum.PEDRA.getId()),
        new FerramentaPicareta(),

        // ESPADA
        2 * IntMath.pow(QUANTIDADE_MAXIMA, MateriaisEnum.MADEIRA.getId()) +
        2 * IntMath.pow(QUANTIDADE_MAXIMA, MateriaisEnum.FERRO.getId()),
        new ArmaEspada(),

        // ARCO E FLECHA
        4 * IntMath.pow(QUANTIDADE_MAXIMA, MateriaisEnum.MADEIRA.getId()) +
        1 * IntMath.pow(QUANTIDADE_MAXIMA, MateriaisEnum.CORDA.getId()) +
        1 * IntMath.pow(QUANTIDADE_MAXIMA, MateriaisEnum.PEDRA.getId()),
        new ArmaArcoFlecha(),

        // CORDA
        2 * IntMath.pow(QUANTIDADE_MAXIMA, MateriaisEnum.PLANTA.getId()),
        new MaterialCorda(1)

    );
    
    private final int ID;
    
    public ItemMaterial(String nome, int peso, int quantidade, int id) {
        super(nome, TIPO, peso, quantidade);

        this.ID = id;
    }

    public int getID() {
        return ID;
    }
}

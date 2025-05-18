package jogo.itens.materiais;

import java.util.Map;

import jogo.itens.Item;
import jogo.itens.armas.ArmaArcoFlecha;
import jogo.itens.armas.ArmaEspada;
import jogo.itens.ferramentas.FerramentaMachado;
import jogo.itens.ferramentas.FerramentaPicareta;
import jogo.utils.IntMath;

public abstract class Material extends Item {
    static enum MateriaisEnum {
        CORDA(0),
        FERRO(1),
        MADEIRA(2),
        PEDRA(3),
        PLANTA(4);

        private int id;

        MateriaisEnum(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

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
    
    public Material(String nome, int peso, int quantidade) {
        super(nome, TIPO, peso, 0, quantidade);

        this.ID = MateriaisEnum.valueOf(nome.toUpperCase()).getId();
    }

    public int getID() {
        return ID;
    }
}

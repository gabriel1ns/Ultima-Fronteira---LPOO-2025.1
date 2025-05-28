package jogo.enums.personagem;

import jogo.construtores.itens.ConstrutorArma;
import jogo.construtores.itens.ConstrutorFerramenta;
import jogo.construtores.itens.ConstrutorMaterial;
import jogo.construtores.itens.consumiveis.ConstrutorAgua;
import jogo.construtores.itens.consumiveis.ConstrutorAlimento;
import jogo.construtores.itens.consumiveis.ConstrutorRemedio;
import jogo.enums.itens.ArmasEnum;
import jogo.enums.itens.FerramentasEnum;
import jogo.enums.itens.MateriaisEnum;
import jogo.enums.itens.consumiveis.AguaEnum;
import jogo.enums.itens.consumiveis.AlimentosEnum;
import jogo.enums.itens.consumiveis.RemediosEnum;
import jogo.itens.Item;

public enum ClassesEnum {
    SOBREVIVENTE
    (100, 100, 100, 100, 100, 6, new Item[]{
        ConstrutorAlimento.construirAlimento(AlimentosEnum.CARNE),
        ConstrutorAlimento.construirAlimento(AlimentosEnum.BANANA),
        ConstrutorAgua.construirAgua(AguaEnum.PURA, 4),
        ConstrutorArma.construirArma(ArmasEnum.LANCA)
    }),

    LENHADOR
    (90, 110, 90, 120, 80, 6, new Item[]{
        ConstrutorFerramenta.construirFerramenta(FerramentasEnum.MACHADO),
        ConstrutorMaterial.construirMaterial(MateriaisEnum.MADEIRA, 4)
    }),

    MEDICO
    (150, 80, 90, 80, 120, 5, new Item[]{
        ConstrutorRemedio.construirRemedio(RemediosEnum.CURA_UNIVERSAL)
    }),
    
    ENGENHEIRO
    (80, 140, 80, 140, 100, 9, new Item[]{
        ConstrutorFerramenta.construirFerramenta(FerramentasEnum.PICARETA),
        ConstrutorMaterial.construirMaterial(MateriaisEnum.PEDRA, 4)
    });

    private final int maxVida;
    private final int maxFome;
    private final int maxSede;
    private final int maxEnergia;
    private final int maxSanidade; 
    private final int capacidadeDoInventario;
    private final Item[] itensIniciais;

    private ClassesEnum(int maxVida, int maxFome, int maxSede, int maxEnergia, int maxSanidade,
            int capacidadeDoInventario, Item[] itensIniciais) {
        this.maxVida = maxVida;
        this.maxFome = maxFome;
        this.maxSede = maxSede;
        this.maxEnergia = maxEnergia;
        this.maxSanidade = maxSanidade;
        this.capacidadeDoInventario = capacidadeDoInventario;
        this.itensIniciais = itensIniciais;
    }

    public int getMaxVida() {
        return maxVida;
    }

    public int getMaxFome() {
        return maxFome;
    }

    public int getMaxSede() {
        return maxSede;
    }

    public int getMaxEnergia() {
        return maxEnergia;
    }

    public int getMaxSanidade() {
        return maxSanidade;
    }

    public int getCapacidadeDoInventario() {
        return capacidadeDoInventario;
    }

    public Item[] getItensIniciais() {
        return itensIniciais;
    }
}

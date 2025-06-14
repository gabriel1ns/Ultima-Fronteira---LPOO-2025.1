package jogo.enums.personagem;

import org.apache.commons.lang3.StringUtils;

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
import jogo.sistema.itens.Item;

public enum PersonagemClassesEnum {
    SOBREVIVENTE
    (100, 100, 100, 100, 100, 6, new Item[]{
        ConstrutorAlimento.construirAlimento(AlimentosEnum.CARNE, 1),
        ConstrutorAlimento.construirAlimento(AlimentosEnum.FRUTAS_SILVESTRES, 1),
        ConstrutorFerramenta.construirFerramenta(FerramentasEnum.RECIPIENTE, 1),
        ConstrutorAgua.construirAgua(AguaEnum.PURA, 4),
        ConstrutorArma.construirArma(ArmasEnum.LANCA, 1)
    },
    "Adiciona um alimento aleatório no inventário"),

    LENHADOR
    (90, 110, 90, 120, 80, 6, new Item[]{
        ConstrutorFerramenta.construirFerramenta(FerramentasEnum.MACHADO, 1),
        ConstrutorMaterial.construirMaterial(MateriaisEnum.MADEIRA, 4)
    },
    "Adiciona um material aleatório no inventário"),

    MEDICO
    (150, 80, 90, 80, 120, 5, new Item[]{
        ConstrutorRemedio.construirRemedio(RemediosEnum.CHA_DE_FOLHAS, 1),
        ConstrutorRemedio.construirRemedio(RemediosEnum.REMENDOS, 1),
        ConstrutorRemedio.construirRemedio(RemediosEnum.ANTIDOTO, 1),
    },
    "Realiza uma autocura"),
    
    ENGENHEIRO
    (80, 140, 80, 140, 100, 9, new Item[]{
        ConstrutorFerramenta.construirFerramenta(FerramentasEnum.PICARETA, 1),
        ConstrutorMaterial.construirMaterial(MateriaisEnum.PEDRA, 4),
    },
    "Conserta uma ferramenta aleatória do inventário"),
    
    // DBG
    // (10, 10, 10, 10, 10, 10, new Item[]{
    //     ConstrutorMaterial.construirMaterial(MateriaisEnum.MADEIRA, 4),
    //     ConstrutorMaterial.construirMaterial(MateriaisEnum.FERRO, 4),
    //     ConstrutorMaterial.construirMaterial(MateriaisEnum.CORDA, 4),
    //     ConstrutorMaterial.construirMaterial(MateriaisEnum.PEDRA, 4)
    // })
    ;

    private final int maxVida;
    private final int maxFome;
    private final int maxSede;
    private final int maxEnergia;
    private final int maxSanidade; 
    private final int capacidadeDoInventario;
    private final Item[] itensIniciais;
    private final String habilidadeEspecial;

    private PersonagemClassesEnum(int maxVida, int maxFome, int maxSede, int maxEnergia, int maxSanidade,
            int capacidadeDoInventario, Item[] itensIniciais, String habilidadeEspecial) {
        this.maxVida = maxVida;
        this.maxFome = maxFome;
        this.maxSede = maxSede;
        this.maxEnergia = maxEnergia;
        this.maxSanidade = maxSanidade;
        this.capacidadeDoInventario = capacidadeDoInventario;
        this.itensIniciais = itensIniciais;
        this.habilidadeEspecial = habilidadeEspecial;
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

    public String getHabiliadeEspecial() {
        return habilidadeEspecial;
    }

    @Override
    public String toString() {
        String itensIniciaisStr = "";
        for(Item item: itensIniciais)
            itensIniciaisStr += item.getNome() + ", ";

        return  StringUtils.capitalize(name().toLowerCase()) + "\n" +
                "Vida maxima: " + maxVida + "\n" +
                "Fome maxima: " + maxFome + "\n" +
                "Sede maxima: " + maxSede + "\n" +
                "Energia maxima: " + maxEnergia + "\n" +
                "Sanidade maxima: " + maxSanidade + "\n" +
                "Itens iniciais: " + itensIniciaisStr + "\n" + 
                "Habilidade especial: " + habilidadeEspecial + "\n";
    }
}

package jogo.enums;

import jogo.enums.itens.ArmasEnum;
import jogo.enums.itens.FerramentasEnum;
import jogo.enums.itens.MateriaisEnum;

public enum CombinacoesEnum {
    MACHADO
    (new MateriaisEnum[]{
        MateriaisEnum.MADEIRA,
        MateriaisEnum.PEDRA
    },
    new int[]{
        2,
        2
    },
    FerramentasEnum.MACHADO,
    1),

    PICARETA
    (new MateriaisEnum[]{
        MateriaisEnum.MADEIRA,
        MateriaisEnum.PEDRA
    },
    new int[]{
        2,
        3
    },
    FerramentasEnum.PICARETA,
    1),

    ESPADA
    (new MateriaisEnum[]{
        MateriaisEnum.MADEIRA,
        MateriaisEnum.FERRO
    },
    new int[]{
        2,
        2
    },
    ArmasEnum.ESPADA,
    1),

    ARCOFLECHA
    (new MateriaisEnum[]{
        MateriaisEnum.MADEIRA,
        MateriaisEnum.CORDA,
        MateriaisEnum.PEDRA
    },
    new int[]{
        3,
        1,
        1
    },
    ArmasEnum.ARCOFLECHA,
    1),

    CORDA
    (new MateriaisEnum[]{
        MateriaisEnum.PLANTA
    },
    new int[]{
        2
    },
    MateriaisEnum.CORDA,
    1),
    
    LANCA
    (new MateriaisEnum[]{
        MateriaisEnum.MADEIRA,
        MateriaisEnum.PEDRA
    },
    new int[]{
        3,
        1
    },
    ArmasEnum.LANCA,
    1),
    
    FERRO
    (new MateriaisEnum[]{
        MateriaisEnum.PEDRA
    },
    new int[]{
        3
    },
    MateriaisEnum.FERRO,
    1),
    
    RECIPIENTE
    (new MateriaisEnum[]{
        MateriaisEnum.PLANTA,
        MateriaisEnum.CORDA
    },
    new int[]{
        2,
        2
    },
    FerramentasEnum.RECIPIENTE,
    1);

    private final MateriaisEnum[] materiaisCombinados;
    private final int[] quantidades;
    private final Enum<?> itemResultanteEnum;
    private final int quantidade;
    
    private CombinacoesEnum(MateriaisEnum[] materiaisCombinados, int[] quantidades, Enum<?> itemResultanteEnum, int quantidade) {
        this.materiaisCombinados = materiaisCombinados;
        this.quantidades = quantidades;
        this.itemResultanteEnum = itemResultanteEnum;
        this.quantidade = quantidade;
    }

    public MateriaisEnum[] getMateriaisCombinados() {
        return materiaisCombinados;
    }

    public int[] getQuantidades() {
        return quantidades;
    }

    public Enum<?> getItemResultanteEnum() {
        return itemResultanteEnum;
    }

    public int getQuantidade() {
        return quantidade;
    }

    
}

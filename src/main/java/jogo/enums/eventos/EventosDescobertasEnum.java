package jogo.enums.eventos;

import jogo.construtores.itens.ConstrutorArma;
import jogo.construtores.itens.ConstrutorFerramenta;
import jogo.enums.EventosEnum;
import jogo.enums.itens.ArmasEnum;
import jogo.enums.itens.FerramentasEnum;
import jogo.enums.itens.MateriaisEnum;
import jogo.enums.itens.consumiveis.AguaEnum;
import jogo.enums.itens.consumiveis.AlimentosEnum;
import jogo.itens.IItemPerecivel;

public enum EventosDescobertasEnum {
    GRAVETO
    ("Graveto", "Um pequeno graveto no chão foi descoberto",
    new Enum<?>[]{ 
        MateriaisEnum.MADEIRA 
    },
    new int[]{
        1
    },
    null),

    ARVORE
    ("Arvore", "Uma árvore razoável para ser cortada foi encontrada",
    new Enum<?>[]{ 
        MateriaisEnum.MADEIRA 
    },
    new int[]{
        4
    },
    ConstrutorFerramenta.construirFerramenta(FerramentasEnum.MACHADO, 1)),

    PEDRA_PEQUENA
    ("Pedra pequena", "Uma pequena pedra foi desoberta no chão",
    new Enum<?>[]{ 
        MateriaisEnum.PEDRA 
    },
    new int[]{
        1
    },
    null),
        
    ROCHA
    ("Rocha", "Uma grande rocha para ser minerada foi encontrada",
    new Enum<?>[]{ 
        MateriaisEnum.PEDRA
    },
    new int[]{
        4
    },
    ConstrutorFerramenta.construirFerramenta(FerramentasEnum.PICARETA, 1)),
    
    GALINHA
    ("Galinha", "Uma galinha silvestre foi encontrada para ser caçada",
    new Enum<?>[]{
        AlimentosEnum.CARNE
    },
    new int[]{
        1
    },
    ConstrutorArma.construirArma(ArmasEnum.LANCA, 1)),
    
    PEIXE
    ("Peixe", "Um pequeno peixe foi encontrado próximo ao lago",
    new Enum<?>[]{
        AlimentosEnum.PEIXE
    },
    new int[]{
        1
    },
    ConstrutorArma.construirArma(ArmasEnum.LANCA,1 )),
    
    FEIJOES_ENLATADOS
    ("Feijões enlatados", "Uma lata contendo feijões enlatados foi encontrada próximo a escombros",
    new Enum<?>[]{
        AlimentosEnum.FEIJOES_ENLATADOS
    },
    new int[]{
        1
    },
    null),
    
    AGUA_DE_LAGO
    ("Agua de lago", "Um lago contendo água foi descoberto. Não precisa ser purificada",
    new Enum<?>[]{
        AguaEnum.PURA
    },
    new int[]{
        4
    },
    ConstrutorFerramenta.construirFerramenta(FerramentasEnum.RECIPIENTE, 1)),
    
    AGUA_DE_GOTEIRA
    ("Agua de goteira", "Uma goteira de água foi descoberta. Precisa ser purificada",
    new Enum<?>[]{
        AguaEnum.IMPURA
    },
    new int[]{
        4
    },
    ConstrutorFerramenta.construirFerramenta(FerramentasEnum.RECIPIENTE, 1)),
    
    ARBUSTO
    ("Arbusto", "Um pequeno arbusto com frutas silvestres foi encontrado. Suas folhas também parecem úteis",
    new Enum<?>[]{
        AlimentosEnum.FRUTAS_SILVESTRES,
        MateriaisEnum.PLANTA
    },
    new int[]{
        1,
        2
    },
    null);
    
    public static final EventosEnum TIPO = EventosEnum.DESCOBERTA; 


    private final String nome;
    private final String descricao;
    private final Enum<?>[] itensDescobertos;
    private final int[] quantidades;
    private final IItemPerecivel itemNecessario;
    
    private EventosDescobertasEnum(String nome, String descricao, Enum<?>[] itensDescobertos, int[] quantidades, IItemPerecivel itemNecessario) {
        this.nome = nome;
        this.descricao = descricao;
        this.itensDescobertos = itensDescobertos;
        this.quantidades = quantidades;
        this.itemNecessario = itemNecessario;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Enum<?>[] getItensDescobertos() {
        return itensDescobertos;
    }

    public IItemPerecivel getItemNecessario() {
        return itemNecessario;
    }

    public int[] getQuantidades() {
        return quantidades;
    }

    
}

package jogo.enums.eventos;

import jogo.construtores.itens.ConstrutorArma;
import jogo.construtores.itens.ConstrutorFerramenta;
import jogo.construtores.itens.ConstrutorMaterial;
import jogo.construtores.itens.consumiveis.ConstrutorAgua;
import jogo.construtores.itens.consumiveis.ConstrutorAlimento;
import jogo.enums.itens.ArmasEnum;
import jogo.enums.itens.FerramentasEnum;
import jogo.enums.itens.MateriaisEnum;
import jogo.enums.itens.consumiveis.AguaEnum;
import jogo.enums.itens.consumiveis.AlimentosEnum;
import jogo.itens.Item;

public enum EventosDescobertasEnum {
    GRAVETO
    ("Graveto", "Um pequeno graveto no chão foi descoberto",
    new Item[]{ 
        ConstrutorMaterial.construirMaterial(MateriaisEnum.MADEIRA, 1) 
    },
    null),

    ARVORE
    ("Arvore", "Uma árvore razoável para ser cortada foi encontrada",
    new Item[]{ 
        ConstrutorMaterial.construirMaterial(MateriaisEnum.MADEIRA, 4) 
    },
    ConstrutorFerramenta.construirFerramenta(FerramentasEnum.MACHADO)),

    PEDRA_PEQUENA
    ("Pedra pequena", "Uma pequena pedra foi desoberta no chão",
    new Item[]{ 
        ConstrutorMaterial.construirMaterial(MateriaisEnum.PEDRA, 1) 
    },
    null),
        
    ROCHA
    ("Rocha", "Uma grande rocha para ser minerada foi encontrada",
    new Item[]{ 
        ConstrutorMaterial.construirMaterial(MateriaisEnum.PEDRA, 4)
    },
    ConstrutorFerramenta.construirFerramenta(FerramentasEnum.PICARETA)),
    
    GALINHA
    ("Galinha", "Uma galinha silvestre foi encontrada para ser caçada",
    new Item[]{
        ConstrutorAlimento.construirAlimento(AlimentosEnum.CARNE)
    },
    ConstrutorArma.construirArma(ArmasEnum.LANCA)),
    
    PEIXE
    ("Peixe", "Um pequeno peixe foi encontrado próximo ao lago",
    new Item[]{
        ConstrutorAlimento.construirAlimento(AlimentosEnum.PEIXE)
    },
    ConstrutorArma.construirArma(ArmasEnum.LANCA)),
    
    FEIJOES_ENLATADOS
    ("Feijões enlatados", "Uma lata contendo feijões enlatados foi encontrada próximo a escombros",
    new Item[]{
        ConstrutorAlimento.construirAlimento(AlimentosEnum.FEIJOES_ENLATADOS)
    },
    null),
    
    AGUA_DE_LAGO
    ("Agua de lago", "Um lago contendo água foi descoberto. Não precisa ser purificada",
    new Item[]{
        ConstrutorAgua.construirAgua(AguaEnum.PURA, 4)
    },
    ConstrutorFerramenta.construirFerramenta(FerramentasEnum.RECIPIENTE)),
    
    AGUA_DE_GOTEIRA
    ("Agua de goteira", "Uma goteira de água foi descoberta. Precisa ser purificada",
    new Item[]{
        ConstrutorAgua.construirAgua(AguaEnum.IMPURA, 4)
    },
    ConstrutorFerramenta.construirFerramenta(FerramentasEnum.RECIPIENTE)),
    
    ARBUSTO
    ("Arbusto", "Um pequeno arbusto com frutas silvestres foi encontrado. Suas folhas também parecem úteis",
    new Item[]{
        ConstrutorAlimento.construirAlimento(AlimentosEnum.FRUTAS_SILVESTRES),
        ConstrutorMaterial.construirMaterial(MateriaisEnum.PLANTA, 2)
    },
    null);
    
    private final String nome;
    private final String descricao;
    private final Item[] itensDescobertos;
    private final Item itemNecessario;
    
    private EventosDescobertasEnum(String nome, String descricao, Item[] itensDescobertos, Item itemNecessario) {
        this.nome = nome;
        this.descricao = descricao;
        this.itensDescobertos = itensDescobertos;
        this.itemNecessario = itemNecessario;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Item[] getItensDescobertos() {
        return itensDescobertos;
    }

    public Item getItemNecessario() {
        return itemNecessario;
    }
}

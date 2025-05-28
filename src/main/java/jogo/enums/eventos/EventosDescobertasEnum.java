package jogo.enums.eventos;

import jogo.construtores.itens.ConstrutorFerramenta;
import jogo.construtores.itens.ConstrutorMaterial;
import jogo.enums.itens.FerramentasEnum;
import jogo.enums.itens.MateriaisEnum;
import jogo.itens.Item;

public enum EventosDescobertasEnum {
    GRAVETO("Graveto", "Um graveto foi encontrado",
        new Item[]{ ConstrutorMaterial.construirMaterial(MateriaisEnum.MADEIRA, 1) },
        null),

    ARVORE("Arvore", "Uma arvore foi encontrada",
        new Item[]{ ConstrutorMaterial.construirMaterial(MateriaisEnum.MADEIRA, 4) },
        ConstrutorFerramenta.construirFerramenta(FerramentasEnum.MACHADO)),

    PEDRA_PEQUENA("Pedra pequena", "Uma pequena pedra foi encontrada",
        new Item[]{ ConstrutorMaterial.construirMaterial(MateriaisEnum.PEDRA, 1) },
        null),
        
    PEDRA_GRANDE("Rocha", "Uma rocha foi encontrada",
        new Item[]{ ConstrutorMaterial.construirMaterial(MateriaisEnum.PEDRA, 4)},
        ConstrutorFerramenta.construirFerramenta(FerramentasEnum.PICARETA));
    
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

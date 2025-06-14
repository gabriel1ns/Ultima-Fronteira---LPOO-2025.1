package jogo.enums.eventos;

import jogo.enums.EventosEnum;
import jogo.enums.personagem.PersonagemAtributosEnum;

public enum EventosClimaticosEnum {
    CALOR_EXTREMO
    ("Calor extremo", "Uma onda de calor assola o ambiente, deixando todos com mais sede",
    2,
    new PersonagemAtributosEnum[]{
        PersonagemAtributosEnum.SEDE,
        PersonagemAtributosEnum.ENERGIA
    },
    new int[]{
        -10,
        -5
    }),

    NEVASCA
    ("Nevasca", "Uma nevasca cai incessantemente, causando hipotermia, mas retendo a sede e a fome", 
    2,
    new PersonagemAtributosEnum[]{
        PersonagemAtributosEnum.VIDA,
        PersonagemAtributosEnum.SEDE,
        PersonagemAtributosEnum.FOME
    },
    new int[]{
        -5,
        +2,
        +1
    }),

    TEMPESTADE
    ("Tempestade", "Chuvas e trovões recaem sobre o ambiente, causando medo e maior dificuldade de locomoção",
    2,
    new PersonagemAtributosEnum[]{
        PersonagemAtributosEnum.ENERGIA,
        PersonagemAtributosEnum.SANIDADE
    },
    new int[]{
        -10,
        -5
    });
    
    public static final EventosEnum TIPO = EventosEnum.CLIMATICO; 

    private final String nome;
    private final String descricao;
    private final int duracao; 
    private final PersonagemAtributosEnum[] atributosAfetados;
    private final int[] efeitos;
    
    private EventosClimaticosEnum(String nome, String descricao, int duracao, PersonagemAtributosEnum[] atributosAfetados,
            int[] efeitos) {
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.atributosAfetados = atributosAfetados;
        this.efeitos = efeitos;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public PersonagemAtributosEnum[] getAtributosAfetados() {
        return atributosAfetados;
    }

    public int[] getEfeitos() {
        return efeitos;
    }

    
}

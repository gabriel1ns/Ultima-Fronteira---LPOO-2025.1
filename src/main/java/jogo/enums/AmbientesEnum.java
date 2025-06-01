package jogo.enums;

import jogo.enums.eventos.EventosClimaticosEnum;
import jogo.enums.eventos.EventosCriaturasEnum;
import jogo.enums.eventos.EventosDescobertasEnum;
import jogo.enums.eventos.EventosDoencaFerimentosEnum;

public enum AmbientesEnum {
    CAVERNA
    ("Caverna", "Um ambiente subterrâneo que pode oferecer abrigo contra o clima, mas esconde perigos desconhecidos",
    new Enum[]{
        EventosClimaticosEnum.CALOR_EXTREMO,
        EventosCriaturasEnum.URSO,
        EventosCriaturasEnum.CORVO,
        EventosDescobertasEnum.PEDRA_PEQUENA,
        EventosDescobertasEnum.ROCHA,
        EventosDescobertasEnum.AGUA_DE_GOTEIRA,
        EventosDoencaFerimentosEnum.CORTE
    },
    new int[]{
        1,
        5,
        3,
        5,
        3,
        3,
        1
    },
    5),

    FLORESTA
    ("Floresta", "Uma área rica em recursos naturais, mas também habitada por predadores",
    new Enum[]{
        EventosClimaticosEnum.TEMPESTADE,
        EventosCriaturasEnum.LOBO,
        EventosCriaturasEnum.COBRA,
        EventosDescobertasEnum.GRAVETO,
        EventosDescobertasEnum.ARVORE,
        EventosDescobertasEnum.ARBUSTO,
        EventosDescobertasEnum.GALINHA,
        EventosDoencaFerimentosEnum.ENVENENAMENTO
    },
    new int[]{
        5,
        5,
        1,
        3,
        5,
        3,
        1,
        1
    },
    3),

    LAGORIO
    ("Lago/rio", "Regiões planas e vastas, ricas em água, rios e vida aquática",
    new Enum[]{
        EventosClimaticosEnum.NEVASCA,
        EventosClimaticosEnum.TEMPESTADE,
        EventosDescobertasEnum.GRAVETO,
        EventosDescobertasEnum.PEDRA_PEQUENA,
        EventosDescobertasEnum.PEIXE,
        EventosDescobertasEnum.ARBUSTO,
        EventosDoencaFerimentosEnum.INFECCAO
    },
    new int[]{
        1,
        1,
        3,
        3,
        5,
        3,
        1
    },
    1),

    MONTANHA
    ("Montanha", "Uma região de difícil acesso, mas rico em minério e pedras preciosas",
    new Enum[]{
        EventosClimaticosEnum.NEVASCA,
        EventosCriaturasEnum.LOBO,
        EventosDescobertasEnum.PEDRA_PEQUENA,
        EventosDescobertasEnum.ROCHA
    },
    new int[]{
        3,
        3,
        5,
        5
    },
    5),

    RUINAS
    ("Ruinas", "Restos de antigas construções que podem conter suprimentos valiosos ou armadilhas",
    new Enum[]{
        EventosClimaticosEnum.TEMPESTADE,
        EventosDescobertasEnum.FEIJOES_ENLATADOS,
        EventosDescobertasEnum.PEDRA_PEQUENA
    },
    new int[]{
        3,
        5,
        3
    },
    5);
    
    private final String nome;
    private final String descricao;
    private final Enum<?>[] eventosPossiveis;
    private final int[] probabilidadeDeEventos; 
    private final int dificuldadeDeExploracao;
    
    private AmbientesEnum(String nome, String descricao, Enum<?>[] eventosPossiveis, int[] probabilidadeDeEventos,
            int dificuldadeDeExploracao) {
        this.nome = nome;
        this.descricao = descricao;
        this.eventosPossiveis = eventosPossiveis;
        this.probabilidadeDeEventos = probabilidadeDeEventos;
        this.dificuldadeDeExploracao = dificuldadeDeExploracao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Enum<?>[] getEventosPossiveis() {
        return eventosPossiveis;
    }

    public int[] getProbabilidadeDeEventos() {
        return probabilidadeDeEventos;
    }

    public int getDificuldadeDeExploracao() {
        return dificuldadeDeExploracao;
    }

    
}

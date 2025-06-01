package jogo.enums.eventos;

import jogo.enums.itens.consumiveis.RemediosEnum;

public enum EventosDoencaFerimentosEnum {
    INFECCAO
    ("Infecção", "Uma infecção súbita te assola",
    15,
    -5,
    RemediosEnum.CHA_DE_FOLHAS),

    CORTE
    ("Corte", "Um corte inesperado aparece em seu corpo",
    15,
    -2,
    RemediosEnum.REMENDOS),

    ENVENENAMENTO
    ("Envenenamento", "Uma cobra peçonhenta lhe picou",
    20,
    -2,
    RemediosEnum.ANTIDOTO);


    private final String nome;
    private final String descricao;
    private final int duracao;
    private final int efeitoNegativo;
    private final RemediosEnum remedioParaCura;
   
    private EventosDoencaFerimentosEnum(String nome, String descricao, int duracao, int efeitoNegativo,
            RemediosEnum remedioParaCura) {
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.efeitoNegativo = efeitoNegativo;
        this.remedioParaCura = remedioParaCura;
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

    public int getEfeitoNegativo() {
        return efeitoNegativo;
    }

    public RemediosEnum getRemedioParaCura() {
        return remedioParaCura;
    }

    
    
}

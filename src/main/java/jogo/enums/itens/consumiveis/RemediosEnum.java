package jogo.enums.itens.consumiveis;

public enum RemediosEnum {
    CURA_UNIVERSAL("Cura universal", 10, 100);

    private final String NOME;
    private final int PESO;
    private final int EFEITO;

    private RemediosEnum(String nome, int peso, int efeito) {
        this.NOME = nome;
        this.PESO = peso;
        this.EFEITO = efeito;
    }

    public String getNOME() {
        return NOME;
    }

    public int getPESO() {
        return PESO;
    }

    public int getEFEITO() {
        return EFEITO;
    }
}

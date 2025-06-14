package jogo.enums.itens.consumiveis;

public enum AguaEnum {
    PURA(true),
    IMPURA(false);

    private final boolean pureza;

    AguaEnum(boolean pureza) {
        this.pureza = pureza;
    }

    public boolean getPureza() {
        return pureza;
    }
}

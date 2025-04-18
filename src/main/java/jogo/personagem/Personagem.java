package jogo.personagem;
import jogo.ambiente.Ambiente;
import jogo.sistema.Inventario;

public class Personagem {
    private final int MIN_VIDA = 0;
    private final int MAX_VIDA = 100;
    private final int MIN_FOME = 0;
    private final int MAX_FOME = 100;
    private final int MIN_SEDE = 0;
    private final int MAX_SEDE = 100;
    private final int MIN_ENERGIA = 0;
    private final int MAX_ENERGIA = 100;
    private final int MIN_SANIDADE = 0;
    private final int MAX_SANIDADE = 100;

    final private String nome;
    private int vida;
    private int fome;
    private int sede;
    private int energia;
    private int sanidade;
    private Ambiente localizacao;
    private Inventario inventario;

    public Personagem(String nome, Ambiente localizacaoInicial) {
        this.nome = nome;
        this.vida = 100;
        this.fome = 100;
        this.sede = 100;
        this.energia = 100;
        this.sanidade = 100;
        this.localizacao = localizacaoInicial;
        this.inventario = new Inventario(10);
    }

    public Inventario getInventario() {
        return inventario;
    }

    public Ambiente getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Ambiente localizacao) {
        this.localizacao = localizacao;
    }
    
    private static int checarLimites(int valor, int limInferior, int limSuperior) {
        if(valor < limInferior) return limInferior;
        if(valor > limSuperior) return limSuperior;
        return valor;
    }

    // Getters e Setters
    public int getFome() {
        return fome;
    }

    public void setFome(int fome) {
        fome = checarLimites(fome, MIN_FOME, MAX_FOME);

        this.fome = fome;
    }

    public int getSede() {
        return sede;
    }

    public void setSede(int sede) {
        sede = checarLimites(sede, MIN_SEDE, MAX_SEDE);

        this.sede = sede;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        energia = checarLimites(energia, MIN_ENERGIA, MAX_ENERGIA);

        this.energia = energia;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        vida = checarLimites(vida, MIN_VIDA, MAX_VIDA);

        this.vida = vida;
    }

    public int getSanidade() {
        return sanidade;
    }

    public void setSanidade(int sanidade) {
        sanidade = checarLimites(sanidade, MIN_SANIDADE, MAX_SANIDADE);

        this.sanidade = sanidade;
    }

    public String getNome() {
        return nome;
    }
}


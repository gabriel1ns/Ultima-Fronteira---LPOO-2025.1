package jogo.personagem;
import jogo.sistema.Inventario;

public class Personagem {
    private int MIN_VIDA = 0;
    private int MIN_FOME = 0;
    private int MIN_SEDE = 0;
    private int MIN_ENERGIA = 0;
    private int MIN_SANIDADE = 0;

    private int MAX_VIDA = 100;
    private int MAX_FOME = 100;
    private int MAX_SEDE = 100;
    private int MAX_ENERGIA = 100;
    private int MAX_SANIDADE = 100;

    final private String nome;
    private int vida;
    private int fome;
    private int sede;
    private int energia;
    private int sanidade;
    private Inventario inventario;

    public static Personagem novoPersonagem(String nome, String classeEscolhida) {
        // TODO adicionar mais classes de personagem
        if(classeEscolhida.equals("Lenhador")) return new PersonagemLenhador(nome);
        else return new PersonagemSobrevivente(nome);
    }

    public Personagem(String nome, int maxVida, int maxFome, int maxSede, int maxEnergia, int maxSanidade, int capacidadeDoInventario) {
        this.nome = nome;
        this.MAX_VIDA = maxVida;
        this.MAX_FOME = maxFome;
        this.MAX_SEDE = maxSede;
        this.MAX_ENERGIA = maxEnergia;
        this.MAX_SANIDADE = maxSanidade;

        setVida(MAX_VIDA);
        setFome(MAX_FOME);
        setSede(MAX_SEDE);
        setEnergia(MAX_ENERGIA);
        setSanidade(MAX_SANIDADE);
        this.inventario = new Inventario(capacidadeDoInventario);
    }

    public Inventario getInventario() {
        return inventario;
    }

    private int checarLimites(int valor, int limInferior, int limSuperior) {
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

    @Override
    public String toString() {
        return  "Nome: " + this.nome + "\n" +
                "Vida: " + this.vida + "\n" +
                "Fome: " + this.fome + "\n" +
                "Sede: " + this.sede + "\n" +
                "Energia: " + this.energia + "\n" +
                "Sanidade: " + this.sanidade + "\n" +
                "Inventario: " + this.inventario.toString() + "\n";
    }
}


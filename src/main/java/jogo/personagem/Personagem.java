package jogo.personagem;

import jogo.ambiente.Ambiente;

public class Personagem {
    private final int MIN_VIDA = 0;
    private final int MAX_VIDA = 100;
    private final int MIN_FOME = 0;
    private final int MAX_FOME = 100;
    private final int MIN_SEDE = 0;
    private final int MAX_SEDE = 100;

    private String nome;
    private int vida;
    private int fome;
    private int sede;
    private int energia;
    private int sanidade;
    private Ambiente localizacao;

    public Personagem(String nome, Ambiente localizacaoInicial) {
        this.nome = nome;
        this.vida = 100;
        this.fome = 100;
        this.sede = 100;
        this.energia = 100;
        this.sanidade = 100;
        this.localizacao = localizacaoInicial;
    }

    public Ambiente getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Ambiente localizacao) {
        this.localizacao = localizacao;
    }

    public void alterarVida(int incremento) {
        int novoValor = this.vida + incremento;
        
        if(novoValor < MIN_VIDA) novoValor = MIN_VIDA;
        if(novoValor > MAX_VIDA) novoValor = MAX_VIDA;
    
        this.vida = novoValor;
    }

    public void alterarFome(int incremento) {
        int novoValor = this.fome + incremento;
        
        if(novoValor < MIN_FOME) novoValor = MIN_FOME;
        if(novoValor > MAX_FOME) novoValor = MAX_FOME;
    
        this.fome = novoValor;
    }

    public void alterarSede(int incremento) {
        int novoValor = this.sede + incremento;
        
        if(novoValor < MIN_SEDE) novoValor = MIN_SEDE;
        if(novoValor > MAX_SEDE) novoValor = MAX_SEDE;
    
        this.sede = novoValor;
    }
    
    // Getters e Setters
    public int getFome() {
        return fome;
    }

    public void setFome(int fome) {
        this.fome = fome;
    }

    public int getSede() {
        return sede;
    }

    public void setSede(int sede) {
        this.sede = sede;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getSanidade() {
        return sanidade;
    }

    public void setSanidade(int sanidade) {
        this.sanidade = sanidade;
    }

    public String getNome() {
        return nome;
    }
}
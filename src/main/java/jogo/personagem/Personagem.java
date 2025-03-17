package jogo.personagem;

import jogo.ambiente.Ambiente;

public class Personagem {
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
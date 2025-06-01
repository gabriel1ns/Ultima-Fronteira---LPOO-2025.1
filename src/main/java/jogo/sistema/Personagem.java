package jogo.sistema;
import java.util.ArrayList;
import java.util.Random;

import jogo.construtores.itens.ConstrutorMaterial;
import jogo.construtores.itens.consumiveis.ConstrutorAlimento;
import jogo.enums.ItensEnum;
import jogo.enums.itens.MateriaisEnum;
import jogo.enums.itens.consumiveis.AlimentosEnum;
import jogo.enums.personagem.PersonagemAtributosEnum;
import jogo.enums.personagem.PersonagemClassesEnum;
import jogo.gerenciadores.GerenciadorDeInventario;
import jogo.sistema.itens.Item;
import jogo.sistema.itens.ItemFerramenta;
import jogo.sistema.itens.ItemMaterial;
import jogo.sistema.itens.consumiveis.ConsumivelAlimento;
import jogo.utils.InputOutput;

public class Personagem {
    
    private static final int    MIN_ATRIBUTO = 0;
    private final int[]         MAX_ATRIBUTOS;

    private final String                    nome;
    private final String                    classe;
    private final String                    habilidadeEspecial;
    private final int[]                     atributos = new int[PersonagemAtributosEnum.values().length];
    private final Inventario                inventario;
    private final GerenciadorDeInventario   gerenciadorDeInventario;
    
    private int habilidadeEspecialCooldown = 0;

    private final InputOutput io = new InputOutput();

    public Personagem(String nome, String classe, String habilidadeEspecial, int maxVida, int maxFome, int maxSede, int maxEnergia, int maxSanidade, 
           int capacidadeDoInventario, Item[] itensIniciais) {
        
        this.nome = nome.length() > 0? nome : "Aventureiro";
        this.classe = classe;
        this.habilidadeEspecial = habilidadeEspecial;
        
        MAX_ATRIBUTOS = new int[]{maxVida, maxFome, maxSede, maxEnergia, maxSanidade};
        
        atributos[PersonagemAtributosEnum.VIDA.getIndice()] = maxVida;
        atributos[PersonagemAtributosEnum.FOME.getIndice()] = maxFome;
        atributos[PersonagemAtributosEnum.SEDE.getIndice()] = maxSede;
        atributos[PersonagemAtributosEnum.ENERGIA.getIndice()] = maxEnergia;
        atributos[PersonagemAtributosEnum.SANIDADE.getIndice()] = maxSanidade;
        
        this.inventario = new Inventario(capacidadeDoInventario, itensIniciais);
        this.gerenciadorDeInventario = new GerenciadorDeInventario(this);
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void mudarAtributo(PersonagemAtributosEnum atributo, int valor) {
        setAtributo(atributo, getAtributo(atributo) + valor);

        io.print(nome + (valor > 0? " ganhou " : " perdeu ") + Math.abs(valor) + " de " + atributo.name().toLowerCase());
    }

    public void setAtributo(PersonagemAtributosEnum atributo, int valor) {
        valor = normalizarValorAtributo(atributo, valor);
        atributos[atributo.getIndice()] = valor;
    }

    public int getAtributo(PersonagemAtributosEnum atributo) {
        return atributos[atributo.getIndice()];
    }

    private int normalizarValorAtributo(PersonagemAtributosEnum atributo, int valor) {
        int indice = atributo.getIndice();
        
        if(valor < MIN_ATRIBUTO) return MIN_ATRIBUTO;
        if(valor > MAX_ATRIBUTOS[indice]) return MAX_ATRIBUTOS[indice];
        return valor;
    }

    public void usarHabilidadeEspecial() {
        io.print(getNome() + " ativou a habilidade especial");

        Random random = new Random();

        switch(PersonagemClassesEnum.valueOf(classe.toUpperCase())) {
            case SOBREVIVENTE -> {
                AlimentosEnum[] alimentos = AlimentosEnum.values();
                ConsumivelAlimento alimento = ConstrutorAlimento.construirAlimento(alimentos[random.nextInt(alimentos.length)], 1);

                io.print(getNome() + " coletou " + alimento.getNome());

                inventario.adicionarItem(alimento);
            }
            case LENHADOR -> {
                MateriaisEnum[] materiais = MateriaisEnum.values();
                ItemMaterial material = ConstrutorMaterial.construirMaterial(materiais[random.nextInt(materiais.length)], 4);

                io.print(getNome() + " coletou " + material.getNome());

                inventario.adicionarItem(material);
            }
            case MEDICO -> {
                final int cura = 20;
                mudarAtributo(PersonagemAtributosEnum.VIDA, cura);
            }
            case ENGENHEIRO -> {
                ArrayList<Item> ferramentas = inventario.getItens(ItensEnum.FERRAMENTA.getIndice());
                ItemFerramenta ferramenta = (ItemFerramenta) ferramentas.get(random.nextInt(ferramentas.size()));
                final int efeito = 10;
                
                io.print(getNome() + " consertou " + ferramenta.getNome());

                ferramenta.setDurabilidade(ferramenta.getDurabilidade() + efeito);
            }
        }

        setHabilidadeEspecialCooldown(10);
    }

    public String getNome() {
        return nome;
    }

    public GerenciadorDeInventario getGerenciadorDeInventario() {
        return gerenciadorDeInventario;
    }

    public String getClasse() {
        return classe;
    }

    public int getHabilidadeEspecialCooldown() {
        return habilidadeEspecialCooldown;
    }

    public void setHabilidadeEspecialCooldown(int habilidadeEspecialCooldown) {
        this.habilidadeEspecialCooldown = habilidadeEspecialCooldown;
    }

    @Override
    public String toString() {

        String ret = "";

        ret +=  "Classe: " + classe + "\n" +
                "Habilidade especial: " + habilidadeEspecial + "\n";

        for(PersonagemAtributosEnum atributo: PersonagemAtributosEnum.values())
            ret += atributo.name() + ": " + atributos[atributo.getIndice()] + "\n";
        
        ret += getInventario().toString();

        return ret;
    }
}


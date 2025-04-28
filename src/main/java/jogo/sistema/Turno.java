package jogo.sistema;

import java.util.ArrayList;
import java.util.Scanner;

import jogo.ambiente.Ambiente;
import jogo.eventos.Evento;
import jogo.eventos.criatura.EventoCriatura;
import jogo.gerenciadores.GerenciadorDeAmbientes;
import jogo.gerenciadores.GerenciadorDeEventos;
import jogo.sistema.Inventario;
import jogo.itens.consumiveis.Agua;
import jogo.itens.consumiveis.alimentos.Alimento;
import jogo.itens.executaveis.armas.Arma;
import jogo.itens.materiais.Material;
import jogo.itens.Item;
import jogo.itens.consumiveis.Consumivel;
import jogo.itens.consumiveis.alimentos.Alimento;
import jogo.personagem.Personagem;
import jogo.utils.InputOutput;

public class Turno {
    private final Personagem personagem;
    private Ambiente ambienteAtual;
    private final GerenciadorDeAmbientes gerenciadorDeAmbientes;
    private final GerenciadorDeEventos gerenciadorDeEventos;
    private final InputOutput io;

    private int dVida;
    private int dSede;
    private int dFome;
    private int dEnergia;
    private int dSanidade;

    public Turno(Personagem personagem, Ambiente ambienteInicial, GerenciadorDeAmbientes gerenciadorDeAmbientes,
                InputOutput io) {
        this.personagem = personagem;
        this.ambienteAtual = ambienteInicial;
        this.gerenciadorDeAmbientes = gerenciadorDeAmbientes;
        this.io = io;

        this.gerenciadorDeEventos = new GerenciadorDeEventos(ambienteInicial.getEventosPossiveis(),
                                                            ambienteInicial.getProbabilidadeDeEventos());
    }

    public void iniciarTurno() {
        io.print(" INÍCIO DO TURNO ");

        dVida = 0;
        dSede = 2;
        dFome = 5;
        dEnergia = ambienteAtual.getDificuldadeDeExploracao();

        faseDeInicio();
        faseDeAcao();
        faseDeEeventos();
        faseDeManutencao();

        io.print("FIM DO TURNO");
    }

    private void faseDeInicio() {
        io.print("Status do personagem: ");
        io.print(personagem.toString());

        io.print("Ambiente atual");
        io.print(ambienteAtual.getNome());

    }

    private void faseDeAcao() {
        EventoCriatura criatura = gerenciadorDeEventos.getEventoCriaturaAtivo();
        boolean isEventoCriaturaAtivo = criatura != null;
        
        io.print("O que você deseja fazer?");
        io.print("1. Mudar de ambiente");
        io.print("2. Gerenciar inventário");
        if(!isEventoCriaturaAtivo) {
            io.print("3. Explorar " + ambienteAtual.getNome());
        } else {
            io.print("3. Batalhar " + criatura.getNome());
        }

        int escolha = Integer.parseInt(io.getInput("Escolha uma opção: "));

        switch (escolha) {
        case 1:
            dEnergia *= 5;
            dSede *= 5;    
            dFome *= 5;    

            this.ambienteAtual = gerenciadorDeAmbientes.sortearAmbiente();

            gerenciadorDeEventos.setEventosPossiveis(ambienteAtual.getEventosPossiveis());
            gerenciadorDeEventos.setProbabilidadeDeEventos(ambienteAtual.getProbabilidadeDeEventos());

            break;
        case 2:
            dEnergia = -5;

            gerenciarInventario();

            break;
        case 3:
            if(isEventoCriaturaAtivo) {
                faseDeAtaque(criatura);

                break;
            }

            gerenciadorDeEventos.adicionarEventoAleatorio();

            break;
        default:
            io.print("Escolha inválida.");
        }
    }

    private void faseDeEeventos() {
        gerenciadorDeEventos.acionarEventos(ambienteAtual, personagem);
    }

    private void faseDeManutencao() {
        personagem.setFome(personagem.getFome() - dFome);
        personagem.setSede(personagem.getSede() - dSede);
        personagem.setEnergia(personagem.getEnergia() - dEnergia);

        if (personagem.getVida() <= 0) {
            io.print("Você morreu");
            
            // TODO alterar
            System.exit(0);
        }

        while(personagem.getInventario().estaCheio()) {
            io.print("Necessário remover itens do inventário");

            descartarItemDoInventario(personagem.getInventario());
        }
    }

    private void faseDeAtaque(EventoCriatura criatura) {
        Arma[] armas = personagem.getInventario().getArmas();

        for(int i = 1; i <= armas.length; i++) {
            if(armas[i] == null) break;
            io.print(i + ". " + armas[i].toString());
        }

        int escolha = Integer.parseInt(io.getInput("Escolha sua arma (1-" + armas.length + ")"));
            
        personagem.getInventario().usarItemArma(escolha, criatura);
    }

    private void gerenciarInventario() {
        Inventario inventario = personagem.getInventario();
        boolean fecharInventario = false;

        while (!fecharInventario) {
            io.print("\nInventário");
            io.print(inventario.toString());

            io.print("\n1. Usar item");
            io.print("2. Descartar item");
            io.print("3. Acabar o turno");

            int escolha = Integer.parseInt(io.getInput("Escolha uma opção: "));

            switch (escolha) {
            case 1:
                usarItemDoInventario(inventario);
                break;
            case 2:
                descartarItemDoInventario(inventario);
                break;
            case 3:
                fecharInventario = true;
                break;
            default:
                io.print("Opção inválida!");
            }
        }
    }

    private void usarItemDoInventario(Inventario inventario) {
        if (inventario.getQuantidadeItens() == 0) {
            io.print("Inventário vazio!");
            return;
        }

        int indice = 0;
        while(indice < 1 || indice > inventario.getQuantidadeItens())
            indice = Integer.parseInt(io.getInput("Escolha o item: (1-" + inventario.getQuantidadeItens() + "):"));

        Item item = inventario.getItem(indice - 1);

        if (item != null) {
            if(item instanceof Consumivel consumivel && 
            inventario.usarItemConsumivel(consumivel, personagem)) {
                io.print("Item consumido");
            } else if(item instanceof Material material &&
            inventario.usarItemMaterial(material, materiais)) {
                io.print("Materiais combinados");
            } else {
                io.print("Não foi possível usar o item.");
            }
        }
    }

    private void descartarItemDoInventario(Inventario inventario) {
        if (inventario.getQuantidadeItens() == 0) {
            io.print("Inventário vazio!");
            return;
        }

        int indice = 0;
        while(indice < 1 || indice > inventario.getQuantidadeItens())
            indice = Integer.parseInt(io.getInput("Qual item deseja descartar? (1-" + inventario.getQuantidadeItens() + "):"));

        Item item = inventario.getItem(indice - 1);
        if (item != null) {
            inventario.removerItem(item);
            io.print("Item " + item.getNome() + " descartado!");
        }
    }
}
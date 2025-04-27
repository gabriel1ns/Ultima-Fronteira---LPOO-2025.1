package jogo.sistema;

import java.util.Scanner;

import jogo.ambiente.Ambiente;
import jogo.gerenciadores.GerenciadorDeAmbientes;
import jogo.gerenciadores.GerenciadorDeEventos;
import jogo.sistema.Inventario;
import jogo.itens.consumiveis.Agua;
import jogo.itens.consumiveis.alimentos.Alimento;
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
    private Item[] itensDescobertos = new Item[100];

    public Turno(Personagem personagem, Ambiente ambienteInicial, GerenciadorDeAmbientes gerenciadorDeAmbientes,
                InputOutput io) {
        this.personagem = personagem;
        this.ambienteAtual = ambienteInicial;
        this.gerenciadorDeAmbientes = gerenciadorDeAmbientes;
        this.io = io;

        this.gerenciadorDeEventos = new GerenciadorDeEventos();
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

        // int opcao = Integer.parseInt(io.getInput("Deseja remover algum item do inventário?"));

        // while(opcao != 0 && personagem.getInventario().getQuantidadeAtual() > 0) {
        //     io.print(personagem.getInventario().toString());
        //     opcao = Integer.parseInt(io.getInput("Digite o item que deseja remover ou 0"));

        //     Item item = personagem.getInventario().getItem(opcao);
        //     personagem.getInventario().removerItem(item);
        // }

    }

    private void faseDeAcao() {
        io.print("O que você deseja fazer?");
        io.print("1. Mudar de ambiente");
        io.print("2. Usar item do inventário");
        if(/*TODO checker para um EventoCriatura ativo */)
            io.print("3. Explorar " + ambienteAtual.getNome());
        else
            io.print("3. Batalhar " /* + criatura.getNome() */);

        int escolha = Integer.parseInt(io.getInput("Escolha uma opção: "));

        switch (escolha) {
        case 1:
            dEnergia *= 5;
            dSede *= 5;    
            dFome *= 5;    

            this.ambienteAtual = gerenciadorDeAmbientes.sortearAmbiente();
            break;
        case 2:
            dEnergia = -5;

            gerenciarInventario();
            break;
        default:
            System.out.println("Escolha inválida.");
        }
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
                System.out.println("Opção inválida!");
            }
        }
    }

    private void usarItemDoInventario(Inventario inventario) {
        if (inventario.getQuantidadeAtual() == 0) {
            io.print("Inventário vazio!");
            return;
        }

        int indice = 0;
        while(indice < 1 || indice > inventario.getQuantidadeAtual())
            indice = Integer.parseInt(io.getInput("Escolha o item: (1-" + inventario.getQuantidadeAtual() + "):"));

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
        if (inventario.getQuantidadeAtual() == 0) {
            io.print("Inventário vazio!");
            return;
        }

        int indice = 0;
        while(indice < 1 || indice > inventario.getQuantidadeAtual())
            indice = Integer.parseInt(io.getInput("Qual item deseja descartar? (1-" + inventario.getQuantidadeAtual() + "):"));

        Item item = inventario.getItem(indice - 1);
        if (item != null) {
            inventario.removerItem(item);
            io.print("Item " + item.getNome() + " descartado!");
        }
    }


    private void faseDeManutencao() {
        personagem.setFome(personagem.getFome() - dFome);
        personagem.setSede(personagem.getSede() - dSede);
        personagem.setEnergia(personagem.getEnergia() - dEnergia);

        if (personagem.getVida() <= 0) {
            System.out.println("Você morreu");
            System.exit(0);
        }

        while(personagem.getInventario().estaCheio()) {
            io.print("Necessário remover itens do inventário");

            descartarItemDoInventario(personagem.getInventario());
        }
    }
}
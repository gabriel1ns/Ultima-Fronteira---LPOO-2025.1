package jogo.sistema;

import java.util.Scanner;

import jogo.ambiente.Ambiente;
import jogo.gerenciadores.GerenciadorDeAmbientes;
import jogo.sistema.Inventario;
import jogo.itens.consumiveis.Agua;
import jogo.itens.consumiveis.alimentos.Alimento;
import jogo.itens.Item;
import jogo.itens.consumiveis.alimentos.Alimento;
import jogo.personagem.Personagem;
import jogo.utils.InputOutput;

public class Turno {
    private final Personagem personagem;
    private final Ambiente ambienteAtual;
    private final GerenciadorDeAmbientes gerenciadorDeAmbientes;
    private final InputOutput io;

    public Turno(Personagem personagem, Ambiente ambienteInicial, GerenciadorDeAmbientes gerenciadorDeAmbientes,
                InputOutput io) {
        this.personagem = personagem;
        this.ambienteAtual = ambienteInicial;
        this.gerenciadorDeAmbientes = gerenciadorDeAmbientes;
        this.io = io;
    }

    public void iniciarTurno() {
        io.print(" INÍCIO DO TURNO ");

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
        io.print(ambienteAtual.toString());

        int opcao = Integer.parseInt(io.getInput("Deseja remover algum item do inventário?"));

        while(opcao != 0 && personagem.getInventario().getQuantidadeAtual() > 0) {
            io.print(personagem.getInventario().toString());
            opcao = Integer.parseInt(io.getInput("Digite o item que deseja remover ou 0"));

            Item item = personagem.getInventario().getItem(opcao);
            personagem.getInventario().removerItem(item);
        }

    }

    private void faseDeAcao() {
        System.out.println("O que você deseja fazer?");
        System.out.println("1. Mudar de ambiente");
        /*
        System.out.println("2. Consumir alimento teste (dbg)");
        System.out.println("3. Consumir agua teste (dbg)");
         */
        System.out.println("2. Abrir inventário");
        System.out.print("Escolha uma opção: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();


        switch (escolha) {
            case 1:
                gerenciadorDeAmbientes.mudarAmbiente(jogador, scanner);
                break;

                /*
            case 2:
                alimentoTst.consumir(jogador);
                System.out.println("Jogador consumiu " + alimentoTst);
                break;

            case 3:
                aguaTst.consumir(jogador);
                System.out.println("Jogador consumiu " + aguaTst);
                break;

                 */
            case 2:
                gerenciarInventario();
                break;
            default:
                System.out.println("Escolha inválida.");
        }
    }

    private void gerenciarInventario() {
        Inventario inventario = jogador.getInventario();
        boolean fecharInventario = false;

        while (!fecharInventario) {
            System.out.println("\nInventário");
            System.out.println(inventario);

            System.out.println("\n1. Usar item");
            System.out.println("2. Descartar item");
            System.out.println("3. Voltar ao turno");
            System.out.print("Escolha uma opção: ");

            int escolha = scanner.nextInt();
            scanner.nextLine();

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
            System.out.println("Inventário vazio!");
            return;
        }

        System.out.println("Escolha o item: (1-" + inventario.getQuantidadeAtual() + "):");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice < 1 || indice > inventario.getQuantidadeAtual()) {
            System.out.println("Você não possui item nesse slot.");
            return;
        }

        Item item = inventario.getItem(indice - 1);
        if (item != null) {
            if (inventario.usarItem(item)) {
                System.out.println("Item usado com sucesso!");
            } else {
                System.out.println("Não foi possível usar o item.");
            }
        }
    }

    private void descartarItemDoInventario(Inventario inventario) {
        if (inventario.getQuantidadeAtual() == 0) {
            System.out.println("Inventário vazio!");
            return;
        }

        System.out.println("Qual item deseja descartar? (1-" + inventario.getQuantidadeAtual() + "):");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice < 1 || indice > inventario.getQuantidadeAtual()) {
            System.out.println("Você não possui item nesse slot.");
            return;
        }

        Item item = inventario.getItem(indice - 1);
        if (item != null) {
            inventario.removerItem(item);
            System.out.println("Item " + item.getNome() + " descartado!");
        }
    }


    private void faseDeManutencao() {
        System.out.println("Atualizando atributos:");
        jogador.setFome(jogador.getFome() - 10);
        jogador.setSede(jogador.getSede() - 15);
        jogador.setEnergia(jogador.getEnergia() - 5);

        if (jogador.getFome() <= 0 || jogador.getSede() <= 0 || jogador.getEnergia() <= 0) {
            System.out.println("Você morreu");
            System.exit(0);
        }

        System.out.println("Status do jogador após o turno:");
        System.out.println("Fome: " + jogador.getFome());
        System.out.println("Sede: " + jogador.getSede());
        System.out.println("Energia: " + jogador.getEnergia());

        aguaTst.setPureza(!aguaTst.getPureza());
    }
}
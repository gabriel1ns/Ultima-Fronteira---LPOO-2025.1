package jogo.sistema;

import java.util.Scanner;

import jogo.gerenciadores.GerenciadorDeAmbientes;
import jogo.sistema.Inventario;
import jogo.itens.consumiveis.Agua;
import jogo.itens.consumiveis.alimentos.Alimento;
import jogo.itens.Item;
import jogo.itens.consumiveis.alimentos.Alimento;
import jogo.personagem.Personagem;

public class Turno {
    private final Personagem jogador;
    private final GerenciadorDeAmbientes gerenciadorDeAmbientes;
    private final Scanner scanner;

    public Turno(Personagem jogador, GerenciadorDeAmbientes gerenciadorDeAmbientes) {
        this.jogador = jogador;
        this.gerenciadorDeAmbientes = gerenciadorDeAmbientes;
        this.scanner = new Scanner(System.in);
    }

    public void iniciarTurno() {
        System.out.println(" INÍCIO DO TURNO ");


        faseDeInicio();
        faseDeAcao();
        faseDeManutencao();

        System.out.println("FIM DO TURNO");
    }

    private void faseDeInicio() {
        System.out.println("");
        System.out.println("Status do Personagem:");
        System.out.println("Nome: " + jogador.getNome());
        System.out.println("Vida: " + jogador.getVida());
        System.out.println("Fome: " + jogador.getFome());
        System.out.println("Sede: " + jogador.getSede());
        System.out.println("Energia: " + jogador.getEnergia());
        System.out.println("Sanidade: " + jogador.getSanidade());
        System.out.println("Ambiente atual: " + jogador.getLocalizacao().getNome());
    }

    Alimento alimentoTst = new Alimento("Banana", 1, 5, 5);
    Agua aguaTst = new Agua(true, 5);

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
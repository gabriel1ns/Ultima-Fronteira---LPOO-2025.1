package jogo.sistema;

import jogo.personagem.Personagem;
import jogo.gerenciadores.GerenciadorDeAmbientes;
import java.util.Scanner;

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

    private void faseDeAcao() {
        System.out.println("O que você deseja fazer?");
        System.out.println("1. Mudar de ambiente");
        System.out.print("Escolha uma opção: ");
        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                gerenciadorDeAmbientes.mudarAmbiente(jogador, scanner);
                break;
            default:
                System.out.println("Escolha inválida.");
        }
    }

    // atributos do personagem estão baixando de maneira fixa, fazer condicional pra cada tipo de ação
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
    }
}
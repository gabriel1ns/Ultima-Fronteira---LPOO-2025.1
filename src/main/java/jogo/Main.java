package jogo;

import jogo.personagem.Personagem;
import jogo.gerenciadores.GerenciadorDeAmbientes;
import jogo.sistema.Turno;
import jogo.ambiente.Ambiente;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do seu personagem: ");
        String nomePersonagem = scanner.nextLine();

        System.out.println("Bem-vindo ao ÚLTIMA FRONTEIRA, " + nomePersonagem + "!");

        GerenciadorDeAmbientes gerenciadorDeAmbientes = new GerenciadorDeAmbientes();
        Ambiente localizacaoInicial = gerenciadorDeAmbientes.getAmbientes().get(0);  // mudar isso aqui, ele sempre começa na floresta, o correto é randomizar


        Personagem jogador = new Personagem(nomePersonagem, localizacaoInicial);

        Turno turno = new Turno(jogador, gerenciadorDeAmbientes);

        for (int i = 1; i <= 10; i++) {
            System.out.println("Turno " + i);
            turno.iniciarTurno();
            System.out.println();
        } // resolver saida do turno quando implementar as outras classes que faltam

        System.out.println("Fim do jogo!");
        scanner.close();
    }
}
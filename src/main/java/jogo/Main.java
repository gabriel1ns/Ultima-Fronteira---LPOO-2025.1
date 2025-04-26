package jogo;
import jogo.itens.consumiveis.Agua;
import jogo.itens.consumiveis.alimentos.Alimento;
import jogo.personagem.Personagem;
import jogo.gerenciadores.GerenciadorDeAmbientes;
import jogo.sistema.Turno;
import jogo.ambiente.Ambiente;

import java.util.Scanner;

import jogo.itens.consumiveis.alimentos.Alimento;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do seu personagem: ");
        String nomePersonagem = scanner.nextLine();

        System.out.println("Bem-vindo ao ÚLTIMA FRONTEIRA, " + nomePersonagem + "!");

        GerenciadorDeAmbientes gerenciadorDeAmbientes = new GerenciadorDeAmbientes();
        Ambiente localizacaoInicial = gerenciadorDeAmbientes.getAmbientes().get(0);

        // mudar isso aqui, ele sempre começa na floresta, o correto é randomizar


        Personagem jogador = new Personagem(nomePersonagem, localizacaoInicial);

        jogador.getInventario().adicionarItem(new Alimento("Banana", 2, 15, 0));
        jogador.getInventario().adicionarItem(new Agua(true, 20));
        System.out.println("Tome uma colher de chá, receba itens básicos pra começar a sua jornada:");
        System.out.println(jogador.getInventario());

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
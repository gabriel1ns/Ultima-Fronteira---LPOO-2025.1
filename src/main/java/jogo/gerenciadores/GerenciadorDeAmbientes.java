package jogo.gerenciadores;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jogo.ambiente.Ambiente;
import jogo.ambiente.AmbienteFloresta;
import jogo.itens.Item;
import jogo.personagem.Personagem;

public class GerenciadorDeAmbientes {
    private final List<Ambiente> ambientes;

    public GerenciadorDeAmbientes() {
        this.ambientes = new ArrayList<>();


        // maneira mais simplória de chamar e definir os ambientes transformar em subclasses para adicionar atributos dps
        ambientes.add(new AmbienteFloresta(new Item[2]));
        /*
        ambientes.add(new Ambiente("Montanha", "Uma região de difícil acesso, mas rica em minérios e pedras preciosas."));
        ambientes.add(new Ambiente("Caverna", "Um ambiente subterrâneo que pode oferecer abrigo contra o clima, mas esconde perigos desconhecidos."));
        ambientes.add(new Ambiente("Lago e Rio", "Regiões ricas em água, mas que podem esconder riscos como afogamento ou criaturas aquáticas."));
        ambientes.add(new Ambiente("Ruínas Abandonadas", "Restos de antigas construções que podem conter suprimentos valiosos ou armadilhas."));
        */
    }

    public List<Ambiente> getAmbientes() {
        return ambientes;
    }

    public void mudarAmbiente(Personagem jogador, Scanner scanner) {
        System.out.println("Escolha um ambiente:");

        for (int i = 0; i < ambientes.size(); i++) {
            System.out.println((i + 1) + ". " + ambientes.get(i).getNome());
        }
        System.out.print("Digite o número do ambiente: ");
        int escolha = scanner.nextInt();
        if (escolha > 0 && escolha <= ambientes.size()) {
            Ambiente novoAmbiente = ambientes.get(escolha - 1);
            jogador.setLocalizacao(novoAmbiente);
            System.out.println("Você está na " + novoAmbiente.getNome());
        } else {
            System.out.println("Escolha inválida. Tente novamente.");
        }
    }

    public void modificarRecursos(Ambiente ambiente) {
        // alterar ambiente.recursosDisponiveis
    }
}
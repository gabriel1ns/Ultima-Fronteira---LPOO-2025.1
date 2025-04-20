package jogo.gerenciadores;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jogo.ambiente.*;
import jogo.itens.Item;
import jogo.personagem.Personagem;

public class GerenciadorDeAmbientes {
    private final List<Ambiente> ambientes;

    public GerenciadorDeAmbientes() {
        this.ambientes = new ArrayList<>();
        ambientes.add(new AmbienteFloresta(new Item[2]));
        ambientes.add(new AmbienteCaverna(new Item[2]));
        ambientes.add(new AmbienteLagoRio(new Item[2]));
        ambientes.add(new AmbienteMontanha(new Item[2]));
        ambientes.add(new AmbienteRuinas(new Item[2]));
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


            if (jogador.getLocalizacao() == novoAmbiente) {
                System.out.println("Você já está em " + novoAmbiente.getNome());
                return;
            }

            System.out.println("Viajando para " + novoAmbiente.getNome());


            jogador.setLocalizacao(novoAmbiente);
        } else {
            System.out.println("Escolha inválida.");
        }
    }
}

   
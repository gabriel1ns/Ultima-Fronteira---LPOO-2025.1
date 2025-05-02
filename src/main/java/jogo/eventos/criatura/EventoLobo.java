package jogo.eventos.criatura;

import jogo.ambiente.Ambiente;
import jogo.itens.consumiveis.alimentos.Proteina;
import jogo.personagem.Personagem;
import java.util.Scanner;
import jogo.utils.InputOutput;


public class EventoLobo extends EventoCriatura {
    private int poderAtaque;
    private Scanner scanner = new Scanner(System.in);
    private InputOutput io;

    public EventoLobo(String descricao, int vida, int dano) {
        super("Lobo", descricao, vida, dano, 3);
        this.poderAtaque = dano * 2;
    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {

        io.print("Você encontrou um lobo! " + getDescricao());
        io.print("O lobo tem " + getVida() + " pontos de vida.");

        boolean combateAtivo = true;

        while (combateAtivo) {
            io.print("\n Turno de Combate");
            io.print("1. Atacar");
            io.print("2. Tentar fugir");
            io.print("Escolha uma ação: ");

            int escolha = 1;
            try {
                escolha = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
            }

            if (escolha == 2) {
                // Fuga
                if (Math.random() < 0.5) {
                    io.print("Você conseguiu fugir do lobo!");
                    personagem.setSanidade(personagem.getSanidade() - 5);
                    io.print("Você perdeu 5 de sanidade por fugir.");
                    combateAtivo = false;
                } else {
                    io.print("Você não conseguiu fugir do lobo!");
                    // Volta o turno de ataque pro lobo
                    atacarPersonagem(personagem);
                }
                continue;
            }

            int danoPersonagem = calcularDanoPersonagem(personagem);
            setVida(getVida() - danoPersonagem);
            personagem.setEnergia(personagem.getEnergia() - 10);
            io.print("Você atacou o lobo, causou " + danoPersonagem + " de dano!");

            if (getVida() <= 0) {
                io.print("Você derrotou o lobo!");
                Proteina proteina = new Proteina(1);
                personagem.getInventario().adicionarItem(proteina);
                io.print("Proteína adicionada ao seu inventário!");
                combateAtivo = false;
                continue;
            }

            atacarPersonagem(personagem);

            if (personagem.getVida() <= 0) {
                io.print("Você foi derrotado pelo lobo!");
                combateAtivo = false;
                continue;
            }

            mostrarStatus(personagem);
        }
    }

    private void atacarPersonagem(Personagem personagem) {
        int danoLobo = getDano() + poderAtaque;
        // - energia do personagem, + dano
        danoLobo = (int)(danoLobo * (1 + (100 - personagem.getEnergia()) / 100.0));

        personagem.setVida(personagem.getVida() - danoLobo);
        personagem.setSanidade(personagem.getSanidade() - 5);

        System.out.println("O lobo atacou você e causou " + danoLobo + " de dano!");
        System.out.println("Você perdeu 5 de sanidade pelo susto!");
    }

    private int calcularDanoPersonagem(Personagem personagem) {
        int danoBase = 10;
        double modificadorEnergia = personagem.getEnergia() / 100.0;
        double modificadorSanidade = personagem.getSanidade() / 100.0;
        int danoFinal = (int)(danoBase * modificadorEnergia * modificadorSanidade);
        return Math.max(1, danoFinal); //alterar esse dano final assim que tivermos os atributos settados
    }

    private void mostrarStatus(Personagem personagem) {
        io.print("\n-Status Atual");
        io.print(personagem.toString()); // nao vai funfar pq o metodo é void, depois arrumamos isso

    }

    public int getPoderAtaque() {
        return poderAtaque;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Poder de Ataque: " + this.poderAtaque + "\n";
    }
}
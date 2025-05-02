package jogo.eventos.criatura;

import jogo.ambiente.Ambiente;
import jogo.itens.consumiveis.alimentos.Proteina;
import jogo.personagem.Personagem;
import java.util.Scanner;
import jogo.utils.InputOutput;

public class EventoUrso extends EventoCriatura {
    private int poderAtaque;
    private int poderDefesa;
    private Scanner scanner = new Scanner(System.in);
    private InputOutput io;

    public EventoUrso(String descricao, int vida, int dano) {
        super("Urso", descricao, vida, dano, 4);
        this.poderAtaque = dano * 3;
        this.poderDefesa = 5;
    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {
        io.print("Você encontrou um urso! " + getDescricao());
        io.print("O urso tem " + getVida() + " pontos de vida.");

        boolean combateAtivo = true;

        while (combateAtivo) {
            io.print("\nTurno de Combate");
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
                if (Math.random() < 0.3) {
                    io.print("Você conseguiu fugir do urso!");
                    personagem.setSanidade(personagem.getSanidade() - 10);
                    io.print("Você perdeu 10 de sanidade por fugir.");
                    combateAtivo = false;
                } else {
                    io.print("Você não conseguiu fugir do urso!");
                    atacarPersonagem(personagem);
                }
                continue;
            }

            int danoPersonagem = calcularDanoPersonagem(personagem);

            danoPersonagem = Math.max(1, danoPersonagem - poderDefesa);
            setVida(getVida() - danoPersonagem);
            personagem.setEnergia(personagem.getEnergia() - 15);
            io.print("Você atacou o urso e causou " + danoPersonagem + " de dano!");
            io.print("Você gastou 15 de energia.");

            if (getVida() <= 0) {
                io.print("Você derrotou o urso!");
                Proteina proteina = new Proteina(3);
                personagem.getInventario().adicionarItem(proteina);
                io.print("Você obteve 3 proteínas do urso!");
                combateAtivo = false;
                continue;
            }

            atacarPersonagem(personagem);

            if (personagem.getVida() <= 0) {
                io.print("Você foi derrotado pelo urso!");
                combateAtivo = false;
                continue;
            }

            mostrarStatus(personagem);
        }
    }

    private void atacarPersonagem(Personagem personagem) {
        int danoUrso = getDano() + poderAtaque;
        double modificador = 1 + (100 - personagem.getEnergia()) / 100.0;
        danoUrso = (int) Math.round(danoUrso * modificador);

        personagem.setVida(personagem.getVida() - danoUrso);
        personagem.setSanidade(personagem.getSanidade() - 8);

        io.print("O urso atacou você e causou " + danoUrso + " de dano!");
        io.print("Você perdeu 8 de sanidade pelo terror!");
    }

    private int calcularDanoPersonagem(Personagem personagem) {
        int danoBase = 12;
        double modificadorEnergia = personagem.getEnergia() / 100.0;
        double modificadorSanidade = personagem.getSanidade() / 100.0;
        int danoFinal = (int) Math.round(danoBase * modificadorEnergia * modificadorSanidade);

        return Math.max(1, danoFinal);
    }

    private void mostrarStatus(Personagem personagem) {
        io.print("\n--- Status Atual ---");
        io.print(personagem.toString());
        io.print("Vida do urso: " + getVida());
    }

    public int getPoderAtaque() {
        return poderAtaque;
    }

    public int getPoderDefesa() {
        return poderDefesa;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Poder de Ataque: " + this.poderAtaque + "\n" +
                "Poder de Defesa: " + this.poderDefesa + "\n";
    }
}
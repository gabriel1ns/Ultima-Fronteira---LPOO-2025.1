package jogo.eventos.criatura;

import jogo.ambiente.Ambiente;
import jogo.itens.consumiveis.alimentos.Proteina;
import jogo.personagem.Personagem;
import java.util.Scanner;
import jogo.utils.InputOutput;

public class EventoCobra extends EventoCriatura {
    private int poderAtaque;
    private int danoVeneno;
    private boolean personagemEnvenenado;
    private int turnosVeneno;
    private Scanner scanner = new Scanner(System.in);
    private InputOutput io;

    public EventoCobra(String descricao, int vida, int dano) {
        super("Cobra", descricao, vida, dano, 2);
        this.poderAtaque = Math.round(dano * 1.5f);
        this.danoVeneno = 5;
        this.personagemEnvenenado = false;
        this.turnosVeneno = 0;
    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {

        io.print("Você encontrou uma cobra! " + getDescricao());
        io.print("A cobra tem " + getVida() + " pontos de vida.");

        boolean combateAtivo = true;

        while (combateAtivo) {
            io.print("\n--- Turno de Combate ---");

            // Aplicar efeito de veneno se o personagem estiver envenenado
            if (personagemEnvenenado) {
                aplicarEfeitoVeneno(personagem);
            }

            // Verificar se o personagem morreu pelo veneno
            if (personagem.getVida() <= 0) {
                io.print("Você morreu envenenado!");
                combateAtivo = false;
                continue;
            }

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
                if (Math.random() < 0.6) {
                    io.print("Você conseguiu fugir da cobra!");
                    personagem.setSanidade(personagem.getSanidade() - 3);
                    io.print("Você perdeu 3 de sanidade por fugir.");
                    combateAtivo = false;
                } else {
                    io.print("Você não conseguiu fugir da cobra!");
                    atacarPersonagem(personagem);
                }
                continue;
            }

            int danoPersonagem = calcularDanoPersonagem(personagem);
            setVida(getVida() - danoPersonagem);
            personagem.setEnergia(personagem.getEnergia() - 8);
            io.print("Você atacou a cobra e causou " + danoPersonagem + " de dano!");
            io.print("Você gastou 8 de energia.");

            if (getVida() <= 0) {
                io.print("Você derrotou a cobra!");

                Proteina proteina = new Proteina(1);
                personagem.getInventario().adicionarItem(proteina);
                io.print("Você obteve 1 proteína da cobra!");
                combateAtivo = false;
                continue;
            }


            atacarPersonagem(personagem);

            if (personagem.getVida() <= 0) {
                io.print("Você foi derrotado pela cobra!");
                combateAtivo = false;
                continue;
            }

            mostrarStatus(personagem);
        }
    }

    private void atacarPersonagem(Personagem personagem) {
        int danoCobra = getDano() + poderAtaque;
        float modificador = 1 + (100 - personagem.getEnergia()) / 100.0f;
        danoCobra = Math.round(danoCobra * modificador);
        personagem.setVida(personagem.getVida() - danoCobra);
        personagem.setSanidade(personagem.getSanidade() - 4);

        io.print("A cobra atacou você e causou " + danoCobra + " de dano!");
        io.print("Você perdeu 4 de sanidade pelo susto!");


        if (!personagemEnvenenado && Math.random() < 0.7) {
            personagemEnvenenado = true;
            turnosVeneno = 3;
            io.print("A cobra te envenenou! Você sofrerá dano por " + turnosVeneno + " turnos.");
        }
    }

    private void aplicarEfeitoVeneno(Personagem personagem) {
        if (turnosVeneno > 0) {
            personagem.setVida(personagem.getVida() - danoVeneno);
            io.print("O veneno causa " + danoVeneno + " de dano!");
            turnosVeneno--;

            if (turnosVeneno == 0) {
                personagemEnvenenado = false;
                io.print("O efeito do veneno passou!");
            } else {
                io.print("Turnos restantes de veneno: " + turnosVeneno);
            }
        }
    }

    private int calcularDanoPersonagem(Personagem personagem) {

        int danoBase = 8;
        float modificadorEnergia = personagem.getEnergia() / 100.0f;
        float modificadorSanidade = personagem.getSanidade() / 100.0f;
        int danoFinal = Math.round(danoBase * modificadorEnergia * modificadorSanidade);
        return Math.max(1, danoFinal);
    }

    private void mostrarStatus(Personagem personagem) {
        io.print("\n--- Status Atual ---");
        io.print(personagem.toString());
        io.print("Vida da cobra: " + getVida());
        if (personagemEnvenenado) {
            io.print("Você está envenenado! Turnos restantes: " + turnosVeneno);
        }
    }

    public int getPoderAtaque() {
        return poderAtaque;
    }

    public int getDanoVeneno() {
        return danoVeneno;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Poder de Ataque: " + this.poderAtaque + "\n" +
                "Dano de Veneno: " + this.danoVeneno + "\n";
    }
}

package jogo;
import jogo.ambientes.Ambiente;
import jogo.gerenciadores.GerenciadorDeAmbientes;
import jogo.itens.consumiveis.ConsumivelAgua;
import jogo.itens.consumiveis.ConsumivelAlimento;
import jogo.itens.ferramentas.FerramentaPicareta;
import jogo.itens.materiais.MaterialMadeira;
import jogo.itens.materiais.MaterialPedra;
import jogo.personagem.Personagem;
import jogo.sistema.Turno;
import jogo.utils.InputOutput;

public class Main {
    public static void main(String[] args) {
        InputOutput io = new InputOutput();

        io.print("Bem-vindo ao ÚLTIMA FRONTEIRA!");

        String nomePersonagem = io.getInput("Diga o seu nome");
        int escolhaClassePersonagem = io.decisaoEmIntervalo("Decida sua classe", Personagem.CLASSES, Personagem.CLASSES.length);

        Personagem personagem = Personagem.novoPersonagem(nomePersonagem, escolhaClassePersonagem);

        GerenciadorDeAmbientes gerenciadorDeAmbientes = new GerenciadorDeAmbientes();
        Ambiente ambienteInicial = gerenciadorDeAmbientes.sortearAmbiente();
        //Ambiente ambienteInicial = new AmbienteCaverna();

        Turno turno = new Turno(personagem, ambienteInicial, gerenciadorDeAmbientes, io);

        for (int i = 1;;i++) {
            io.print("Turno" + i);
            turno.iniciarTurno();

            if(personagem.getVida() == 0) break;
            io.print("");
        } // resolver saida do turno quando implementar as outras classes que faltam

        io.print("Fim do jogo!");
    }
}
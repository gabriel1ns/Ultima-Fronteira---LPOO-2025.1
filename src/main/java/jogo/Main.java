package jogo;
import jogo.ambiente.Ambiente;
import jogo.gerenciadores.GerenciadorDeAmbientes;
import jogo.itens.consumiveis.agua.Agua;
import jogo.itens.consumiveis.alimentos.AlimentoFruta;
import jogo.personagem.Personagem;
import jogo.sistema.Turno;
import jogo.utils.InputOutput;

public class Main {
    public static void main(String[] args) {
        InputOutput io = new InputOutput();

        io.print("Bem-vindo ao ÚLTIMA FRONTEIRA!");

        String nomePersonagem = io.getInput("Diga o seu nome");
        String classePersonagem = io.getInput("Decida sua classe");

        Personagem personagem = Personagem.novoPersonagem(nomePersonagem, classePersonagem);
        
        // DBG - REMOVER OU ALTERAR DEPOIS
        personagem.getInventario().adicionarItem(new AlimentoFruta());
        personagem.getInventario().adicionarItem(new Agua(true, 20));
        System.out.println("Tome uma colher de chá, receba itens básicos pra começar a sua jornada:");
        System.out.println(personagem.getInventario());

        GerenciadorDeAmbientes gerenciadorDeAmbientes = new GerenciadorDeAmbientes();
        Ambiente ambienteInicial = gerenciadorDeAmbientes.sortearAmbiente();

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
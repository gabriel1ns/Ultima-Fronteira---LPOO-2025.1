package jogo;
import jogo.ambiente.Ambiente;
import jogo.gerenciadores.GerenciadorDeAmbientes;
import jogo.itens.consumiveis.agua.Agua;
import jogo.itens.consumiveis.alimentos.AlimentoFruta;
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
        
        // DBG - REMOVER OU ALTERAR DEPOIS
        personagem.getInventario().adicionarItem(new AlimentoFruta());
        personagem.getInventario().adicionarItem(new Agua(true, 20));
        personagem.getInventario().adicionarItem(new FerramentaPicareta());
        personagem.getInventario().adicionarItem(new MaterialPedra(2));
        personagem.getInventario().adicionarItem(new MaterialMadeira(2));
        System.out.println("Tome uma colher de chá, receba itens básicos pra começar a sua jornada:");
        System.out.println(personagem.getInventario());

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
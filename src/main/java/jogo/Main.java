package jogo;
import jogo.construtores.ConstrutorPersonagem;
import jogo.enums.personagem.AtributosEnum;
import jogo.enums.personagem.ClassesEnum;
import jogo.gerenciadores.GerenciadorDeAmbientes;
import jogo.personagem.Personagem;
import jogo.sistema.Turno;
import jogo.utils.InputOutput;

public class Main {
    public static void main(String[] args) {
        InputOutput io = new InputOutput();

        io.print("Bem-vindo ao ÚLTIMA FRONTEIRA!");

        String nomePersonagem = io.getInput("Diga o seu nome");
        int escolhaClassePersonagem = io.decisaoEmIntervalo("Decida sua classe", ClassesEnum.values());

        ClassesEnum classePersonagem = ClassesEnum.SOBREVIVENTE; 
        
        for(ClassesEnum classe: ClassesEnum.values()) {
            if(escolhaClassePersonagem == classe.ordinal()) {
                classePersonagem = classe;
                break;
            }
        }
        
        Personagem personagem = ConstrutorPersonagem.construirPersonagem(nomePersonagem, classePersonagem);

        GerenciadorDeAmbientes gerenciadorDeAmbientes = new GerenciadorDeAmbientes();
        Ambiente ambienteInicial = gerenciadorDeAmbientes.sortearAmbiente();
        //Ambiente ambienteInicial = ConstrutorAmbiente.construir(AmbientesEnum.MONTANHA);

        Turno turno = new Turno(personagem, ambienteInicial, gerenciadorDeAmbientes, io);

        for (int i = 1;;i++) {
            io.print("Turno" + i);
            turno.iniciarTurno();

            if(personagem.getAtributo(AtributosEnum.VIDA) == 0) break;
            io.print("");
        } // resolver saida do turno quando implementar as outras classes que faltam

        io.print("Fim do jogo!");
    }
}
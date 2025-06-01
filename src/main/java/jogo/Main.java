package jogo;
import jogo.construtores.ConstrutorPersonagem;
import jogo.enums.personagem.PersonagemAtributosEnum;
import jogo.enums.personagem.PersonagemClassesEnum;
import jogo.gerenciadores.GerenciadorDeAmbientes;
import jogo.sistema.Ambiente;
import jogo.sistema.Personagem;
import jogo.sistema.Turno;
import jogo.utils.InputOutput;

public class Main {
    public static void main(String[] args) {
        InputOutput io = new InputOutput();

        io.print("Bem-vindo ao ÚLTIMA FRONTEIRA!");

        String nomePersonagem = io.getInput("Diga o seu nome");
        int escolhaClassePersonagem = io.decisaoEmIntervalo("Decida sua classe", PersonagemClassesEnum.values());

        PersonagemClassesEnum classePersonagem = PersonagemClassesEnum.SOBREVIVENTE; 
        
        for(PersonagemClassesEnum classe: PersonagemClassesEnum.values()) {
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

            if(personagem.getAtributo(PersonagemAtributosEnum.VIDA) == 0) break;
            io.print("");
        } // resolver saida do turno quando implementar as outras classes que faltam

        io.print("Fim do jogo!");
    }
}
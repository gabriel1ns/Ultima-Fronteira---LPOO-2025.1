package jogo;
import jogo.construtores.ConstrutorItem;
import jogo.construtores.ConstrutorPersonagem;
import jogo.enums.itens.FerramentasEnum;
import jogo.enums.personagem.PersonagemAtributosEnum;
import jogo.enums.personagem.PersonagemClassesEnum;
import jogo.gerenciadores.GerenciadorDeAmbientes;
import jogo.sistema.Ambiente;
import jogo.sistema.Personagem;
import jogo.sistema.Turno;
import jogo.sistema.itens.Item;
import jogo.utils.InputOutput;

public class Main {
    public static void main(String[] args) {
        InputOutput io = new InputOutput();

        io.print("Bem-vindo a ÚLTIMA FRONTEIRA!");
        io.print("Você é um naufragado que agora se encontra numa ilha isolada de tudo e todos");
        io.print("Você terá " + Turno.QUANTIDADE_DE_TURNOS_PARA_VITORIA + " turnos para tentar sobreviver, ou construir uma jangada para fugir da ilha. Boa sorte!\n");

        String nomePersonagem = io.getInput("Diga o seu nome (ou deixe em branco para ser 'Aventureiro')");
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
        // DBG
        //ambienteInicial = ConstrutorAmbiente.construir(AmbientesEnum.DBG);

        Turno turno = new Turno(personagem, ambienteInicial, gerenciadorDeAmbientes, io);

        Item jangada = ConstrutorItem.construir(FerramentasEnum.JANGADA, 1);

        for (int i = 1;;i++) {
            io.print("Turno" + i);
            turno.iniciarTurno();

            if(personagem.getAtributo(PersonagemAtributosEnum.VIDA) == 0) break;
            io.print("");

            if(i == Turno.QUANTIDADE_DE_TURNOS_PARA_VITORIA) {
                io.print("PARABÉNS!");
                io.print(personagem.getNome() + "conseguiu sobreviver por " + Turno.QUANTIDADE_DE_TURNOS_PARA_VITORIA + " turnos!");

                int escolha = io.decisaoEmIntervalo("O que deseja fazer?", new String[]{
                    "Finalizar o jogo",
                    "Continuar no modo infinito"
                });

                if(escolha == 0) break;
            }



            if(personagem.getInventario().encontrarItem(jangada) != -1) {
                io.print("PARABÉNS!");
                io.print(personagem.getNome() + " construiu uma Jangada e conseguiu fugir da ilha!");

                int escolha = io.decisaoEmIntervalo("O que deseja fazer?", new String[]{
                    "Finalizar o jogo",
                    "Continuar no modo infinito"
                });

                if(escolha == 0) break;
            
                personagem.getInventario().removerItem(jangada, 1);
            }
        }

        io.print("Fim do jogo!");
    }
}
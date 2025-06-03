package jogo.utils;

import java.util.Scanner;
import java.util.function.Consumer;

public class InputOutput {
    // TODO migrar I/O do console pra GUI

    private final Scanner scanner;
    private static Consumer<String> globalLogger = null;

    public InputOutput() {

        scanner = new Scanner(System.in);
    }


    public static void setGlobalLogger(Consumer<String> logger) {
        globalLogger = logger;
    }

    public void print(String mensagem) {
        if (globalLogger != null) {
            globalLogger.accept(mensagem);
        } else {
            System.out.println("\n" + mensagem);
        }
    }

    public void print(String messagem, boolean pausarTela) {
        print(messagem);

        if(pausarTela) {
            getInput("Aperte qualquer tecla para continuar...");
        }
    }


    public String getInput() {
        return scanner.nextLine();
    }

    public String getInput(String entrada) {
        print(entrada);
        return getInput();
    }

    public int decisaoEmIntervalo(String mensagem, Object[] opcoes) {
        print(mensagem);

        int indice = -1;

        do {
            for(int i = 0; i < opcoes.length; i++)
                print(i+1 + ". " + opcoes[i].toString());

            print("Escolha: ");

            try {
                indice = Integer.parseInt(getInput());

                if(indice < 0 || indice > opcoes.length)
                    throw new NumberFormatException();

            } catch(NumberFormatException e) {
                print("Escolha inválida!");
                indice = -1;
            }

        } while(indice < 0 || indice > opcoes.length);

        print("");

        return indice-1;
    }
}
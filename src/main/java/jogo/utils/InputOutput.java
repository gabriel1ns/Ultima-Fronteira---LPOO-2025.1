package jogo.utils;

import java.util.Scanner;

public class InputOutput {
    // TODO migrar I/O do console pra GUI

    private final Scanner scanner;

    public InputOutput() {
        scanner = new Scanner(System.in);
    }

    public void print(String mensagem) {
        System.out.println("\n" + mensagem);
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
                // parseInt joga NumberFormatException
                indice = Integer.parseInt(getInput());

                if(indice < 0 || indice > opcoes.length) 
                    throw new NumberFormatException();
                
            } catch(NumberFormatException e) {
                print("Escolha inválida!");
            }

        } while(indice < 0 || indice > opcoes.length);
    
        print("");

        return indice-1;
    }
}

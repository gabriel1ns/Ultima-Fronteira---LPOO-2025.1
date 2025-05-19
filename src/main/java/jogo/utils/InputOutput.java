package jogo.utils;

import java.util.Scanner;

public class InputOutput {
    // TODO migrar I/O do console pra GUI

    private Scanner scanner;

    public InputOutput() {
        scanner = new Scanner(System.in);
    }

    public void print(String mensagem) {
        System.out.println(mensagem);
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public String getInput(String entrada) {
        print(entrada);
        return getInput();
    }

    public int decisaoEmIntervalo(String mensagem, Object[] opcoes, int tamanho) {
        print(mensagem);
        
        int indice;

        do {
            for(int i = 0; i < opcoes.length; i++)
                print(i+1 + ". " + opcoes[i].toString());
    
            indice = Integer.parseInt(getInput()) - 1;
            
            if(indice < -1 || indice >= tamanho) 
                print("Escolha inválida!");

        } while(indice < -1 || indice >= tamanho);
    
        return indice;
    }
}

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

    public String getInput(String entrada) {
        print(entrada);
        return scanner.nextLine();
    }
}

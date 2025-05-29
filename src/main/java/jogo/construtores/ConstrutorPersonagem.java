package jogo.construtores;

import jogo.enums.personagem.ClassesEnum;
import jogo.personagem.Personagem;

public class ConstrutorPersonagem {
    public static Personagem construirPersonagem(String nome, ClassesEnum classe) {
        return new Personagem(
            nome, 
            classe.getMaxVida(), 
            classe.getMaxFome(), 
            classe.getMaxSede(), 
            classe.getMaxEnergia(), 
            classe.getMaxSanidade(), 
            classe.getCapacidadeDoInventario(),
            classe.getItensIniciais()
        );
    }
}

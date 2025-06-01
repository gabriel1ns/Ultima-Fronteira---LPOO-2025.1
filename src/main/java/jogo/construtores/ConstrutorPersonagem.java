package jogo.construtores;

import jogo.enums.personagem.PersonagemClassesEnum;
import jogo.sistema.Personagem;

public class ConstrutorPersonagem {
    public static Personagem construirPersonagem(String nome, PersonagemClassesEnum classe) {
        return new Personagem(
            nome,
            classe.name(), 
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

package jogo.gerenciadores;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jogo.construtores.ConstrutorAmbiente;
import jogo.enums.AmbientesEnum;
import jogo.sistema.Ambiente;

public class GerenciadorDeAmbientes {
    private final List<Ambiente> ambientes;
    private int indiceAtual = -1;

    public GerenciadorDeAmbientes() {
        this.ambientes = new ArrayList<>();

        for(AmbientesEnum ambiente: AmbientesEnum.values()) 
            ambientes.add(ConstrutorAmbiente.construir(ambiente));

    }

    public List<Ambiente> getAmbientes() {
        return ambientes;
    }

    public Ambiente sortearAmbiente() {
        Random random = new Random();
        int indice = this.indiceAtual;

        while(indice == indiceAtual)
            indice = random.nextInt(ambientes.size());
        
        this.indiceAtual = indice;

        return ambientes.get(indice);
    }
}

   
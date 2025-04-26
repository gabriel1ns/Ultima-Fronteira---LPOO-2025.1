package jogo.gerenciadores;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jogo.ambiente.Ambiente;
import jogo.ambiente.AmbienteCaverna;
import jogo.ambiente.AmbienteFloresta;
import jogo.ambiente.AmbienteLagoRio;
import jogo.ambiente.AmbienteMontanha;
import jogo.ambiente.AmbienteRuinas;
import jogo.itens.Item;

public class GerenciadorDeAmbientes {
    private final List<Ambiente> ambientes;
    private int indiceAtual = -1;

    public GerenciadorDeAmbientes() {
        this.ambientes = new ArrayList<>();
        ambientes.add(new AmbienteFloresta(new Item[2]));
        ambientes.add(new AmbienteCaverna(new Item[2]));
        ambientes.add(new AmbienteLagoRio(new Item[2]));
        ambientes.add(new AmbienteMontanha(new Item[2]));
        ambientes.add(new AmbienteRuinas(new Item[2]));

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

   
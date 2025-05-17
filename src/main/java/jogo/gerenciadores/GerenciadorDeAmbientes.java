package jogo.gerenciadores;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jogo.ambiente.Ambiente;
import jogo.ambiente.AmbienteCaverna;
import jogo.ambiente.AmbienteFloresta;
import jogo.ambiente.AmbienteLagoRio;
import jogo.ambiente.AmbienteRuinas;

public class GerenciadorDeAmbientes {
    private final List<Ambiente> ambientes;
    private int indiceAtual = -1;

    public GerenciadorDeAmbientes() {
        this.ambientes = new ArrayList<>();
        ambientes.add(new AmbienteFloresta());
        ambientes.add(new AmbienteCaverna());
        ambientes.add(new AmbienteLagoRio());
        //ambientes.add(new AmbienteMontanha(new Evento[2])); - resolver esse pq precisa da picareta por exemplo
        ambientes.add(new AmbienteRuinas());

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

   
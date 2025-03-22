package jogo.gerenciadores;

import java.util.ArrayList;
import java.util.Random;

import jogo.eventos.Evento;


public class GerenciadorDeEventos {
    private Evento[] eventosPossiveis;
    private int[] probabilidadeDeEventos;

    public GerenciadorDeEventos(Evento[] eventosPossiveis, int[] probabilidadeDeEventos) {
        setEventosPossiveis(eventosPossiveis);
        setProbabilidadeDeEventos(probabilidadeDeEventos);
    }

    public Evento sortearEvento() {
        ArrayList<Evento> bolhaDeEventos = new ArrayList<>();
        Evento evento;

        for(int i = 0; i < probabilidadeDeEventos.length; i++) {
            evento = eventosPossiveis[i];
            int pesoDoEvento = probabilidadeDeEventos[i];

            for(int j = 0; j < pesoDoEvento; j++) {
                bolhaDeEventos.add(evento);
            }
        }

        Random seletorDeEvento = new Random();
        int indice = seletorDeEvento.nextInt(bolhaDeEventos.size());   
        
        evento = bolhaDeEventos.get(indice);
        return evento;
    }

    final public void setEventosPossiveis(Evento[] eventosPossiveis) {
        this.eventosPossiveis = eventosPossiveis;
    }

    final public void setProbabilidadeDeEventos(int[] probabilidadeDeEventos) {
        this.probabilidadeDeEventos = probabilidadeDeEventos;
    }

    public Evento[] getEventosPossiveis() {
        return eventosPossiveis;
    }

    public int[] getProbabilidadeDeEventos() {
        return probabilidadeDeEventos;
    }

} // criar metodos dps

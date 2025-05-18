package jogo.gerenciadores;

import java.util.ArrayList;
import java.util.Random;

import jogo.ambiente.Ambiente;
import jogo.eventos.Evento;
import jogo.eventos.criatura.EventoCriatura;
import jogo.personagem.Personagem;
import jogo.utils.InputOutput;


public class GerenciadorDeEventos {
    private final static int NUM_MAX_EVENTOS = 100;

    private Evento[] eventosPossiveis;
    private int[] probabilidadeDeEventos;
    
    private Evento[] eventosAtivos;
    private int quantidadeDeEventos;
    private EventoCriatura eventoCriaturaAtivo;

    public GerenciadorDeEventos(Evento[] eventosPossiveis, int[] probabilidadeDeEventos) {
        setEventosPossiveis(eventosPossiveis);
        setProbabilidadeDeEventos(probabilidadeDeEventos);
        assert(eventosPossiveis.length == probabilidadeDeEventos.length);
    
        eventosAtivos = new Evento[NUM_MAX_EVENTOS];
        quantidadeDeEventos = 0;
        eventoCriaturaAtivo = null;
    }

    public void adicionarEventoAleatorio() {
        if(quantidadeDeEventos == NUM_MAX_EVENTOS) return;

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

        if(evento instanceof EventoCriatura eventoCriatura)
            eventoCriaturaAtivo = eventoCriatura;
        
        eventosAtivos[quantidadeDeEventos] = evento;
        quantidadeDeEventos++;
    }

    public void acionarEventos(Ambiente ambiente, Personagem personagem) {
        ArrayList<Evento> eventosParaRemocao = new ArrayList<>();
        
        for(int i = 0; i < quantidadeDeEventos; i++) {
            Evento evento = eventosAtivos[i];

            if(evento.getDuracao() == 0) {
                eventosParaRemocao.add(evento);
                continue;
            }

            InputOutput io = new InputOutput();
            io.print("Executando " + evento.getNome());
            
            evento.executar(ambiente, personagem);

            if(!(evento instanceof EventoCriatura))
                evento.setDuracao(evento.getDuracao() - 1);
        }

        for(Evento evento: eventosParaRemocao)
            removerEvento(evento);
    }

    private void removerEvento(Evento eventoParaRemover) {
        for(int i = 0; i < quantidadeDeEventos; i++) {
            Evento evento = eventosAtivos[i];

            if(eventoParaRemover.equals(evento)) {
                if(eventoParaRemover instanceof EventoCriatura)
                    this.eventoCriaturaAtivo = null;

                for(int i2 = i; i2 < quantidadeDeEventos-1; i2++)
                    eventosAtivos[i2] = eventosAtivos[i2+1];

                quantidadeDeEventos--;
                return;
            }
        }
    }

    public int buscarEventoCriaturaAtivo() {
        for(int i = 0; i < quantidadeDeEventos; i++)
            if(eventosAtivos[i] instanceof EventoCriatura)
                return i;

        return -1;
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

    public Evento[] getEventosAtivos() {
        return eventosAtivos;
    }

    public EventoCriatura getEventoCriaturaAtivo() {
        return eventoCriaturaAtivo;
    }

} // criar metodos dps

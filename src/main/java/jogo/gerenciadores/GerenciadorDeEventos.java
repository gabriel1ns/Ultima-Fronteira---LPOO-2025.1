package jogo.gerenciadores;

import java.util.ArrayList;
import java.util.Random;

import jogo.construtores.ConstrutorEvento;
import jogo.sistema.Ambiente;
import jogo.sistema.Personagem;
import jogo.sistema.eventos.Evento;
import jogo.sistema.eventos.EventoClimatico;
import jogo.sistema.eventos.EventoCriatura;
import jogo.utils.InputOutput;


public class GerenciadorDeEventos {
    private final static int NUM_MAX_EVENTOS = 100;

    private Ambiente ambiente;
    private final Personagem personagem;

    private ArrayList<Evento> eventosAtivos;

    private final InputOutput io = new InputOutput();

    public GerenciadorDeEventos(Ambiente ambiente, Personagem personagem) {
        this.ambiente = ambiente;
        this.personagem = personagem;

        eventosAtivos = new ArrayList<>();
    }

    public void adicionarEventoAleatorio() {
        if(eventosAtivos.size() == NUM_MAX_EVENTOS) return;

        ArrayList<Enum<?>> bolhaDeEventos = new ArrayList<>();

        for(int i = 0; i < ambiente.getProbabilidadeDeEventos().length; i++) {
            int pesoDoEvento = ambiente.getProbabilidadeDeEventos()[i];

            for(int j = 0; j < pesoDoEvento; j++) {
                bolhaDeEventos.add(ambiente.getEventosPossiveis()[i]);
            }
        }

        Random seletorDeEvento = new Random();
        if (bolhaDeEventos.isEmpty()) {
            io.print("Nenhum evento possível para este ambiente no momento.", true);
            return;
        }
        int indiceAleatorio = seletorDeEvento.nextInt(bolhaDeEventos.size());

        try {
            Evento evento = ConstrutorEvento.construir(bolhaDeEventos.get(indiceAleatorio));
            eventosAtivos.add(evento);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao construir evento: " + e.getMessage());
        }
    }

    public boolean executarEventos() {
        ArrayList<Evento> eventosParaRemocao = new ArrayList<>();

        if(eventosAtivos.isEmpty())
            return false;

        // Executa os eventos
        for(Evento evento: eventosAtivos) {

            if(evento.getDuracao() == 0) {
                eventosParaRemocao.add(evento);
                continue;
            }
            io.print(evento.toString(), true);

            evento.executar(personagem); 
        }

        for(Evento evento: eventosParaRemocao) {
            eventosAtivos.remove(evento);
        }

        return true;
    }

    public void fugirDeEventoCriatura(EventoCriatura criatura) {
        int chanceDeSucesso = 7; // 70%

        Random rng = new Random();
        boolean resultado = rng.nextInt(11) < chanceDeSucesso; // 0 <= rng <= 10 

        if(resultado) {
            io.print(personagem.getNome() + " conseguiu fugir de " + criatura.getNome() + "!", true); 
            eventosAtivos.remove(criatura);
        } else {
            io.print(personagem.getNome() + " não conseguiu fugir de " + criatura.getNome() + ".", true); 
        }
    }

    public EventoCriatura buscarEventoCriaturaAtivo() {
        for(Evento evento: eventosAtivos)
            if(evento instanceof EventoCriatura criatura) return criatura;

        return null;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {

        // settando duração em 0 pra não remover os eventos enquanto itero pela coleção,
        // deixando o executarEventos cuidar disso
        for(Evento evento: eventosAtivos) {
            if(evento instanceof EventoClimatico eventoClimatico) {
                eventoClimatico.setDuracao(0);
            }
        }

        this.ambiente = ambiente;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public ArrayList<Evento> getEventosAtivos() {
        return eventosAtivos;
    }

    public void setEventosAtivos(ArrayList<Evento> eventosAtivos) {
        this.eventosAtivos = eventosAtivos;
    }
}
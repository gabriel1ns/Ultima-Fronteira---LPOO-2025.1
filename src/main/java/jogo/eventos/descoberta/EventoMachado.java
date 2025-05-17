package jogo.eventos.descoberta;

import jogo.ambiente.Ambiente;
import jogo.itens.Item;
import jogo.personagem.Personagem;
import jogo.utils.InputOutput;

public class EventoMachado extends EventoDescoberta {
    private InputOutput io;

    public EventoMachado(String tipo, String descricao, Item[] recursosEncontrados){
        super(tipo, descricao, recursosEncontrados);
    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {
        /*
        if (metodo que lê o inventário e vê se o personagem possui MACHADO == true){
        Madeira madeira = new Madeira();
        atualizarInventario(personagem,madeira(3));
        } else{
            io.print("Você não possui as ferramentas coletar madeira");
        }
         */
    }
}

package jogo.eventos.descoberta;


import jogo.ambiente.Ambiente;
import jogo.itens.Item;
import jogo.personagem.Personagem;
import jogo.utils.InputOutput;

public class EventoPicareta extends EventoDescoberta {
    private InputOutput io;

    public EventoPicareta(String tipo, String descricao, Item[] recursosEncontrados){
        super(tipo, descricao, recursosEncontrados);
    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {
        /*
        if (metodo que lê o inventário e vê se o personagem possui PICARETA == true){
        Pedra pedra = new Pedra();
        atualizarInventario(personagem,pedra(3));
        } else{
            io.print("Você não possui as ferramentas necessárias para coletar pedra");
        }
         */
    }
}

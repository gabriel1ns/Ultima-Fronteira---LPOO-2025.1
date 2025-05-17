package jogo.eventos.descoberta;

import jogo.ambiente.Ambiente;
import jogo.itens.Item;
import jogo.personagem.Personagem;
import jogo.utils.InputOutput;

public class EventoProteina extends EventoDescoberta{
    private InputOutput io;

    public EventoProteina(String tipo, String descricao, Item[] recursosEncontrados){
        super(tipo, descricao, recursosEncontrados);
    }

    @Override
    public void executar(Ambiente ambiente, Personagem personagem) {
        /*
        if (metodo que lê o inventário e vê se o personagem possui ARMA == true){
        Proteina proteina = new Proteina();
        atualizarInventario(personagem,proteina);
        } else{
            io.print("Você não possui as ferramentas necessárias para esfolar o animal.")
        }
         */
    }
}

package jogo.sistema.eventos;

import jogo.construtores.itens.consumiveis.ConstrutorRemedio;
import jogo.enums.itens.consumiveis.RemediosEnum;
import jogo.enums.personagem.PersonagemAtributosEnum;
import jogo.sistema.Personagem;
import jogo.sistema.itens.consumiveis.ConsumivelRemedio;
import jogo.utils.InputOutput;

public class EventoDoencaFerimento extends Evento {
    private final InputOutput io = new InputOutput();

    private final int efeitoNegativo;
    private final RemediosEnum remedioParaCura;
    
    public EventoDoencaFerimento(String nome, String descricao, int duracao, 
           int efeitoNegativo, RemediosEnum remedioParaCura) {
        super(nome, descricao, duracao);

        this.efeitoNegativo = efeitoNegativo;
        this.remedioParaCura = remedioParaCura;
    }

    @Override
    public void executar(Personagem personagem) {
        int indice = personagem.getInventario().encontrarItem(ConstrutorRemedio.construirRemedio(remedioParaCura, 1));

        if(indice != 1) {
            ConsumivelRemedio remedio = (ConsumivelRemedio) personagem.getInventario().getItens().get(indice);

            int escolha = io.decisaoEmIntervalo("Deseja utilizar o remedio?", new String[]{
                "Sim",
                "Não"
            });

            if(escolha == 0) {
                super.setDuracao(super.getDuracao() - remedio.getEfeito());
                remedio.consumir(personagem);

                if(super.getDuracao() == 0) return;
            }
        }

        personagem.mudarAtributo(PersonagemAtributosEnum.VIDA, efeitoNegativo);
        super.decrementarDuracao();
    }
    
    public int getEfeitoNegativo() {
        return efeitoNegativo;
    }
    
    public RemediosEnum getRemedioParaCura() {
        return remedioParaCura;
    }

    @Override
    public String toString() {
        return  super.toString() + 
                "Duração: " + super.getDuracao() + " turno" + (super.getDuracao() > 1? "s" : "") + "\n" +
                "Pode se curar com: " + remedioParaCura.getNOME() + "\n";
    }   
}

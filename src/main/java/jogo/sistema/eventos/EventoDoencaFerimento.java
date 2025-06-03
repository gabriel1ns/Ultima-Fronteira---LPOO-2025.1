package jogo.sistema.eventos;

import jogo.construtores.itens.consumiveis.ConstrutorRemedio;
import jogo.enums.itens.consumiveis.RemediosEnum;
import jogo.enums.personagem.PersonagemAtributosEnum;
import jogo.sistema.Personagem;
import jogo.sistema.itens.Item;
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
        Item remedioProcurado = ConstrutorRemedio.construirRemedio(remedioParaCura, 1);
        int indiceNaListaPrincipal = personagem.getInventario().encontrarItem(remedioProcurado);

        boolean remedioUsadoAutomaticamente = false;

        if (indiceNaListaPrincipal != -1) {
            Item itemEncontrado = personagem.getInventario().getItens().get(indiceNaListaPrincipal);
            if (itemEncontrado instanceof ConsumivelRemedio remedio) {

                io.print("Remédio '" + remedio.getNome() + "' encontrado. Usando automaticamente.");

                int duracaoReduzidaPeloRemedio = remedio.getEfeito();
                remedio.consumir(personagem);
                personagem.getInventario().removerItem(remedio, 1);

                super.setDuracao(Math.max(0, super.getDuracao() - duracaoReduzidaPeloRemedio));
                remedioUsadoAutomaticamente = true;

                if (super.getDuracao() == 0) {
                    io.print(personagem.getNome() + " curado com " + remedio.getNome() + "!");
                    return;
                } else {
                    io.print(personagem.getNome() + " usou " + remedio.getNome() + ". Duração restante do evento: " + super.getDuracao());
                }
            }
        }

        if (!remedioUsadoAutomaticamente) {
            io.print(getNome() + " afeta " + personagem.getNome() + " (sem remédio usado ou encontrado).");
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
                "\nDuração: " + super.getDuracao() + " turno" + (super.getDuracao() > 1? "s" : "") +
                "\nPode se curar com: " + remedioParaCura.getNome(); 
    }
}
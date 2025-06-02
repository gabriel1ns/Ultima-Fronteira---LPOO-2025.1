package jogo.sistema.eventos;

import jogo.construtores.itens.consumiveis.ConstrutorRemedio;
import jogo.enums.itens.consumiveis.RemediosEnum;
import jogo.enums.personagem.PersonagemAtributosEnum;
// import jogo.sistema.Inventario; // Not directly used
import jogo.sistema.Personagem;
import jogo.sistema.itens.Item;
import jogo.sistema.itens.consumiveis.ConsumivelRemedio;
import jogo.utils.InputOutput; // Make sure this is imported

public class EventoDoencaFerimento extends Evento {
    private final InputOutput io = new InputOutput(); // Uses the global logger if set

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
            if (itemEncontrado instanceof ConsumivelRemedio) {
                ConsumivelRemedio remedio = (ConsumivelRemedio) itemEncontrado;

                io.print("[EVENTO " + getNome() + "] Remédio '" + remedio.getNome() + "' encontrado. Usando automaticamente.");

                int duracaoReduzidaPeloRemedio = remedio.getEfeito();
                remedio.consumir(personagem);
                personagem.getInventario().removerItem(remedio, 1);

                super.setDuracao(Math.max(0, super.getDuracao() - duracaoReduzidaPeloRemedio));
                remedioUsadoAutomaticamente = true;

                if (super.getDuracao() == 0) {
                    io.print("[EVENTO " + getNome() + "] Curado com " + remedio.getNome() + "!");
                    return;
                } else {
                    io.print("[EVENTO " + getNome() + "] Usou " + remedio.getNome() + ". Duração restante do evento: " + super.getDuracao());
                }
            }
        }

        if (!remedioUsadoAutomaticamente) {
            io.print("[EVENTO " + getNome() + "] Afeta " + personagem.getNome() + " (sem remédio usado ou encontrado).");
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
        // This is printed by GerenciadorDeEventos using its own io instance,
        // so it will also use the global logger.
        // Ensure no trailing newline if the logger method adds one.
        return  super.toString() + // From Evento.java: "Evento: " + descricao
                "\nDuração: " + super.getDuracao() + " turno" + (super.getDuracao() > 1? "s" : "") +
                "\nPode se curar com: " + remedioParaCura.getNOME(); // No trailing \n
    }
}
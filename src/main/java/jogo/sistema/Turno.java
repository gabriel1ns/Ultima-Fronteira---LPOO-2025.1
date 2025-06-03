package jogo.sistema;

import jogo.enums.ItensEnum;
import jogo.enums.personagem.PersonagemAtributosEnum;
import jogo.gerenciadores.GerenciadorDeAmbientes;
import jogo.gerenciadores.GerenciadorDeEventos;
import jogo.gerenciadores.GerenciadorDeInventario;
import jogo.sistema.eventos.EventoCriatura;
import jogo.utils.InputOutput;

public class Turno {
    public final static int QUANTIDADE_DE_TURNOS_PARA_VITORIA = 30;

    private final Personagem personagem;
    private Ambiente ambienteAtual;
    private final GerenciadorDeAmbientes gerenciadorDeAmbientes;
    private final GerenciadorDeEventos gerenciadorDeEventos;
    private final InputOutput io;

    // private int dVida;
    private int dSede;
    private int dFome;
    private int dEnergia;
    // private int dSanidade;

    public Turno(Personagem personagem, Ambiente ambienteInicial, GerenciadorDeAmbientes gerenciadorDeAmbientes,
                InputOutput io) {
        this.personagem = personagem;
        this.ambienteAtual = ambienteInicial;
        this.gerenciadorDeAmbientes = gerenciadorDeAmbientes;
        this.io = io;

        this.gerenciadorDeEventos = new GerenciadorDeEventos(ambienteInicial, personagem);
    }

    public void iniciarTurno() {
        io.print(" INÍCIO DO TURNO ");

        dSede = -2;
        dFome = -5;
        dEnergia = -1*ambienteAtual.getDificuldadeDeExploracao();

        faseDeInicio();
        faseDeAcao();
        faseDeEventos();
        faseDeManutencao();

        io.print("FIM DO TURNO");
    }

    private void faseDeInicio() {
        io.print("Status do personagem: ");
        io.print(personagem.toString());

        io.print("Ambiente atual");
        io.print(ambienteAtual.toString());

    }

    @SuppressWarnings("null")
    private void faseDeAcao() {
        EventoCriatura criatura = gerenciadorDeEventos.buscarEventoCriaturaAtivo();
        boolean isEventoCriaturaAtivo = criatura != null;

        while(true){

            io.print("Digite <ctrl+C> para fechar o jogo");

            int escolha = io.decisaoEmIntervalo("O que você deseja fazer?", new String[]{

                "Mudar de ambiente" + (isEventoCriaturaAtivo? " (não é possível no momento)" : ""),
                "Gerenciar inventário (não acaba o turno)",
                (!isEventoCriaturaAtivo? "Explorar " + ambienteAtual.getNome() : "Batalhar " + criatura.getNome()),
                (!isEventoCriaturaAtivo? "Descansar" : "Tentar fugir"),
                "Usar habilidade especial" + (personagem.getHabilidadeEspecialCooldown() > 0 || isEventoCriaturaAtivo? " (não é possível no momento)" : ""),
                
            }) + 1;


            switch (escolha) {
            case 1 -> {
                if(isEventoCriaturaAtivo) {
                    io.print(personagem.getNome() + " está no meio de uma batalha!");
                    continue;
                }

                if(personagem.getAtributo(PersonagemAtributosEnum.ENERGIA) < 15) {
                    io.print(personagem.getNome() + " está cansado demais para explorar!");
                    continue;
                }

                dEnergia *= 5;
                dSede *= 5;
                dFome *= 5;
                
                this.ambienteAtual = gerenciadorDeAmbientes.sortearAmbiente();

                gerenciadorDeEventos.setAmbiente(ambienteAtual);
                }
            case 2 -> {
                gerenciarInventario();
                continue;
                }
            case 3 -> {
                if(isEventoCriaturaAtivo) {
                    faseDeAtaque(criatura);
                    break;
                }

                if(personagem.getAtributo(PersonagemAtributosEnum.ENERGIA) == 0) {
                    io.print(personagem.getNome() + " está cansado demais para explorar!");
                    dEnergia += 2;
                    continue;
                }

                gerenciadorDeEventos.adicionarEventoAleatorio();
                }
            case 4 -> {
                if(isEventoCriaturaAtivo) {
                    gerenciadorDeEventos.fugirDeEventoCriatura(criatura);
                    break;
                }

                io.print(personagem.getNome() + " está descansando");
                dEnergia = +15;
                }
            case 5 -> {
                if(isEventoCriaturaAtivo || personagem.getHabilidadeEspecialCooldown() > 0) {
                    io.print("Não é possível no momento");
                    continue;
                }
                
                personagem.usarHabilidadeEspecial();
                }
            default -> {
                io.print("Escolha inválida.");
                continue;
                }
            }

            break;
        }
    }

    private void faseDeManutencao() {
        personagem.getGerenciadorDeInventario().decrementarValidadeDosAlimentos();

        if(personagem.getAtributo(PersonagemAtributosEnum.FOME) == 0) {
            io.print(personagem.getNome() + " está com fome!");
            personagem.mudarAtributo(PersonagemAtributosEnum.VIDA, -5);
        }
        if(personagem.getAtributo(PersonagemAtributosEnum.SEDE) == 0) {
            io.print(personagem.getNome() + " está com sede!");
            personagem.mudarAtributo(PersonagemAtributosEnum.VIDA, -2);
        }

        io.print("E o tempo passa...");

        personagem.mudarAtributo(PersonagemAtributosEnum.FOME, dFome);
        personagem.mudarAtributo(PersonagemAtributosEnum.SEDE, dSede);
        personagem.mudarAtributo(PersonagemAtributosEnum.ENERGIA, dEnergia);

        if (personagem.getAtributo(PersonagemAtributosEnum.VIDA) == 0) {
            io.print("Você morreu!");
            return;
        }

        io.print("", true);

        while(personagem.getInventario().estaCheio()) {
            
            int diff = personagem.getInventario().getQuantidadeItens() - personagem.getInventario().getCapacidadeMaxima(); 

            io.print("Seu inventário atingiu a capacidade máxima");
            io.print("Necessário remover " + diff + (diff == 1? " item" : "itens"));

            personagem.getGerenciadorDeInventario().removerItens();
        }
    }

    private void faseDeEventos() {
        gerenciadorDeEventos.executarEventos();
    }

    private void faseDeAtaque(EventoCriatura criatura) {
        io.print(criatura.toString());

        personagem.getGerenciadorDeInventario().usarItemArma(criatura);
    }

    private void gerenciarInventario() {
        boolean fecharInventario = false;

        Inventario inventario = personagem.getInventario();
        GerenciadorDeInventario gerenciadorDeInventario = personagem.getGerenciadorDeInventario();

        while (!fecharInventario) {
            io.print(personagem.getInventario().toString());

            String[] opcoes = new String[]{
                "Usar consumivel" + (inventario.getItens(ItensEnum.CONSUMIVEL.getIndice()).isEmpty()? " (Nenhum disponivel)" : ""),
                "Combinar Materiais" + (inventario.getItens(ItensEnum.MATERIAL.getIndice()).isEmpty()? " (Nenhum disponivel)" : ""),
                "Descartar itens" + (inventario.getItens().isEmpty()? " (Inventario vazio)" : ""),
                "Fechar o inventario"
            };

            int escolha = io.decisaoEmIntervalo("Escolha uma ação", opcoes) + 1;

            switch (escolha) {
            case 1 -> gerenciadorDeInventario.usarItemConsumivel();
            case 2 -> gerenciadorDeInventario.combinarMateriais();
            case 3 -> gerenciadorDeInventario.removerItens();
            case 4 -> fecharInventario = true;
            default -> io.print("Opção inválida!");
            }
        }
    }
}
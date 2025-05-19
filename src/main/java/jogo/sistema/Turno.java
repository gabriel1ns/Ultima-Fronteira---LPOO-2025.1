package jogo.sistema;

import java.util.ArrayList;
import java.util.HashSet;

import jogo.ambiente.Ambiente;
import jogo.eventos.criatura.EventoCriatura;
import jogo.gerenciadores.GerenciadorDeAmbientes;
import jogo.gerenciadores.GerenciadorDeEventos;
import jogo.itens.Item;
import jogo.itens.armas.Arma;
import jogo.itens.consumiveis.Consumivel;
import jogo.itens.materiais.Material;
import jogo.personagem.Personagem;
import jogo.utils.InputOutput;

public class Turno {
    private final Personagem personagem;
    private Ambiente ambienteAtual;
    private final GerenciadorDeAmbientes gerenciadorDeAmbientes;
    private final GerenciadorDeEventos gerenciadorDeEventos;
    private final InputOutput io;

    private int dVida;
    private int dSede;
    private int dFome;
    private int dEnergia;
    private int dSanidade;

    public Turno(Personagem personagem, Ambiente ambienteInicial, GerenciadorDeAmbientes gerenciadorDeAmbientes,
                InputOutput io) {
        this.personagem = personagem;
        this.ambienteAtual = ambienteInicial;
        this.gerenciadorDeAmbientes = gerenciadorDeAmbientes;
        this.io = io;

        this.gerenciadorDeEventos = new GerenciadorDeEventos(ambienteInicial.getEventosPossiveis(),
                                                            ambienteInicial.getProbabilidadeDeEventos());
    }

    public void iniciarTurno() {
        io.print(" INÍCIO DO TURNO ");

        dVida = 0;
        dSede = -2;
        dFome = -5;
        dEnergia = -ambienteAtual.getDificuldadeDeExploracao();

        faseDeInicio();
        faseDeAcao();
        faseDeEeventos();
        faseDeManutencao();

        io.print("FIM DO TURNO");
    }

    private void faseDeInicio() {
        io.print("Status do personagem: ");
        io.print(personagem.toString());

        io.print("Ambiente atual");
        io.print(ambienteAtual.getNome());

    }

    private void faseDeAcao() {
        EventoCriatura criatura = gerenciadorDeEventos.getEventoCriaturaAtivo();
        boolean isEventoCriaturaAtivo = criatura != null;
        
        io.print("O que você deseja fazer?");
        io.print("1. Mudar de ambiente");
        io.print("2. Gerenciar inventário");
        if(!isEventoCriaturaAtivo) {
            io.print("3. Explorar " + ambienteAtual.getNome());
        } else {
            io.print("3. Batalhar " + criatura.getNome());
        }

        while(true){
            int escolha = Integer.parseInt(io.getInput("Escolha uma opção: "));

            switch (escolha) {
            case 1:
                if(personagem.getEnergia() < 15) {
                    io.print(personagem.getNome() + " está cansado demais para explorar!");
                    continue;
                }

                if(isEventoCriaturaAtivo) {
                    io.print(personagem.getNome() + " está no meio de uma batalha!");
                    continue;
                }

                dEnergia *= 5;
                dSede *= 5;    
                dFome *= 5;    

                this.ambienteAtual = gerenciadorDeAmbientes.sortearAmbiente();

                gerenciadorDeEventos.setEventosPossiveis(ambienteAtual.getEventosPossiveis());
                gerenciadorDeEventos.setProbabilidadeDeEventos(ambienteAtual.getProbabilidadeDeEventos());

                break;
            case 2:
                dEnergia = +15;

                gerenciarInventario();

                break;
            case 3:
                if(isEventoCriaturaAtivo) {
                    faseDeAtaque(criatura);
                    break;
                }

                if(personagem.getEnergia() == 0) {
                    io.print(personagem.getNome() + "está cansado demais para explorar!");
                    dEnergia += 2;
                    continue;
                }

                gerenciadorDeEventos.adicionarEventoAleatorio();

                break;
            default:
                io.print("Escolha inválida.");
                continue;
            }

            break;
        }
    }

    private void faseDeEeventos() {
        gerenciadorDeEventos.executarEventos(ambienteAtual, personagem);
    }

    private void faseDeManutencao() {
        if(personagem.getFome() == 0) {
            io.print(personagem.getNome() + "está com fome!");
            dVida -= 5;
        }
        if(personagem.getSede() == 0) {
            io.print(personagem.getNome() + "está com sede!");
            dVida -= 2;
        }

        personagem.setVida(personagem.getVida() + dVida);
        personagem.setFome(personagem.getFome() + dFome);
        personagem.setSede(personagem.getSede() + dSede);
        personagem.setEnergia(personagem.getEnergia() + dEnergia);

        if (personagem.getVida() == 0) {
            io.print("Você morreu!");
            return;
        }

        while(personagem.getInventario().estaCheio()) {
            io.print("Necessário remover itens do inventário");

            descartarItemDoInventario(personagem.getInventario());
        }
    }

    private void faseDeAtaque(EventoCriatura criatura) {
        ArrayList<Item> armas = personagem.getInventario().getItens(Inventario.InventarioEnum.ARMA.getIndice());

        int escolha = io.decisaoEmIntervalo("Escolha sua arma", armas.toArray(Arma[]::new), armas.size());
            
        personagem.getInventario().usarItemArma(escolha, criatura);
    }

    private void gerenciarInventario() {
        Inventario inventario = personagem.getInventario();
        boolean fecharInventario = false;

        while (!fecharInventario) {
            io.print("\nInventário");
            io.print(inventario.toString());

            io.print("\n1. Usar consumivel");
            io.print("2. Combinar materiais");
            io.print("3. Descartar itens");
            io.print("4. Acabar o turno");

            int escolha = Integer.parseInt(io.getInput("Escolha uma opção: "));

            switch (escolha) {
            case 1:
                usarConsumivelDoInventario(inventario);
                break;
            case 2:
                combinarMateriaisDoInventario(inventario);
                break;
            case 3:
                descartarItemDoInventario(inventario);
                break;
            case 4:
                fecharInventario = true;
                break;
            default:
                io.print("Opção inválida!");
            }
        }
    }

    private void usarConsumivelDoInventario(Inventario inventario) {
        ArrayList<Item> consumiveis = inventario.getItens(Inventario.InventarioEnum.CONSUMIVEL.getIndice());

        if (consumiveis.isEmpty()) {
            io.print("Nenhum consumivel disponivel");
            return;
        }

        int indice = io.decisaoEmIntervalo("Escolha o consumível", consumiveis.toArray(Consumivel[]::new), consumiveis.size());

        inventario.usarItemConsumivel(indice, personagem);
    }

    private void combinarMateriaisDoInventario(Inventario inventario) {
        ArrayList<Item> materiais = inventario.getItens(Inventario.InventarioEnum.MATERIAL.getIndice());

        if(materiais.isEmpty()) {
            io.print("Nenhum material para combinar");
            return;
        }

        ArrayList<Material> materiaisEscolhidos = new ArrayList<>();
        HashSet<Integer> indicesMarcados = new HashSet<>();

        while(true) {
            int indice = io.decisaoEmIntervalo("Escolha os materiais, ou digite 0 para parar", materiais.toArray(Material[]::new), materiais.size());

            if(indice == -1) break;

            if(indicesMarcados.contains(indice)) {
                io.print("Material já escolhido");
                continue;
            }

            indicesMarcados.add(indice);

            int quantidade = Integer.parseInt(io.getInput("Digite a quantidade: "));

            Material material = (Material) materiais.get(indice);
            material.setQuantidade(quantidade);

            materiaisEscolhidos.add(material);
        }
        
        Material[] materiaisEscolhidosArr = materiaisEscolhidos.toArray(new Material[materiaisEscolhidos.size()]);

        boolean res = inventario.combinarMateriais(materiaisEscolhidosArr);

        if(!res) {
            io.print("Item não existe");
        }
    }

    private void descartarItemDoInventario(Inventario inventario) {
        if (inventario.estaVazio()) {
            io.print("Inventário vazio!");
            return;
        }

        ArrayList<Item> itens = inventario.getItens();

        while(true) { 
            int indice = io.decisaoEmIntervalo("Escolha o item para descarte ou digite 0 para parar", itens.toArray(Item[]::new), itens.size());

            if(indice == -1) break;

            int quantidade = Integer.parseInt(io.getInput("Digite a quantidade: "));

            inventario.removerItem(indice, quantidade);
        }
    }
}
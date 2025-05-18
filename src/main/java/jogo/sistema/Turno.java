package jogo.sistema;

import java.util.ArrayList;
import java.util.HashSet;

import jogo.ambiente.Ambiente;
import jogo.eventos.criatura.EventoCriatura;
import jogo.gerenciadores.GerenciadorDeAmbientes;
import jogo.gerenciadores.GerenciadorDeEventos;
import jogo.itens.Item;
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

        int escolha = Integer.parseInt(io.getInput("Escolha uma opção: "));

        switch (escolha) {
        case 1:
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
                break;
            }

            gerenciadorDeEventos.adicionarEventoAleatorio();

            break;
        default:
            io.print("Escolha inválida.");
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

        for(int i = 0; i < armas.size(); i++) {
            if(armas.get(i) == null) break;
            io.print(i + ". " + armas.get(i).toString());
        }

        int escolha = Integer.parseInt(io.getInput("Escolha sua arma (1-" + armas.size() + ")")) - 1;
            
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

        io.print(consumiveis.toString());

        int indice = -1;
        while(indice < 0 || indice > consumiveis.size())
            indice = Integer.parseInt(io.getInput("Escolha o consumivel: (1-" + consumiveis.size() + "):")) - 1;

        inventario.usarItemConsumivel(indice, personagem);
    }

    private void combinarMateriaisDoInventario(Inventario inventario) {
        ArrayList<Item> materiais = inventario.getItens(Inventario.InventarioEnum.MATERIAL.getIndice());

        if(materiais.isEmpty()) {
            io.print("Nenhum material para combinar");
            return;
        }

        io.print(materiais.toString());

        ArrayList<Material> materiaisEscolhidos = new ArrayList<>();
        HashSet<Integer> indicesMarcados = new HashSet<>();

        io.print(materiais.toString());
        int indice = Integer.parseInt(io.getInput("Escolha o material: (1-" + materiais.size() + "):")) - 1;
        
        while(0 <= indice && indice < materiais.size()) {

            if(indicesMarcados.contains(indice)) {
                io.print("Material já escolhido");
                continue;
            }

            indicesMarcados.add(indice);

            int quantidade = Integer.parseInt(io.getInput("Digite a quantidade: "));

            Material material = (Material) materiais.get(indice);
            material.setQuantidade(quantidade);

            materiaisEscolhidos.add(material);

            io.print(materiais.toString());
            indice = Integer.parseInt(io.getInput("Escolha o material: (1-" + materiais.size() + "):")) - 1;
        }
        
        Material[] materiaisEscolhidosArr = materiaisEscolhidos.toArray(new Material[materiaisEscolhidos.size()]);

        io.print(materiais.toString());
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

        io.print(itens.toString());
        int indice = Integer.parseInt(io.getInput("Qual item deseja descartar? (1-" + inventario.getQuantidadeItens() + "):")) - 1;

        while(0 <= indice && indice < inventario.getQuantidadeItens()) {            
            int quantidade = Integer.parseInt(io.getInput("Digite a quantidade: "));

            inventario.removerItem(indice, quantidade);

            io.print(itens.toString());
            indice = Integer.parseInt(io.getInput("Qual item deseja descartar? (1-" + inventario.getQuantidadeItens() + "):")) - 1;
        }
    }
}
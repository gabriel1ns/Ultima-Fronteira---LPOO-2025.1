package jogo.gerenciadores;

import java.util.ArrayList;
import java.util.HashMap;

import jogo.construtores.ConstrutorItem;
import jogo.construtores.itens.ConstrutorMaterial;
import jogo.enums.CombinacoesEnum;
import jogo.enums.ItensEnum;
import jogo.enums.itens.MateriaisEnum;
import jogo.sistema.Inventario;
import jogo.sistema.Personagem;
import jogo.sistema.eventos.EventoCriatura;
import jogo.sistema.itens.IItemPerecivel;
import jogo.sistema.itens.Item;
import jogo.sistema.itens.ItemArma;
import jogo.sistema.itens.ItemMaterial;
import jogo.sistema.itens.consumiveis.Consumivel;
import jogo.utils.InputOutput;
import jogo.utils.IntMath;

public class GerenciadorDeInventario {
    private final HashMap<Integer, Enum<?>> mapaDeCombinacoes;
    private final InputOutput io = new InputOutput();

    private final Personagem personagem;
    private final Inventario inventario;

    public GerenciadorDeInventario(Personagem personagem) {
        
        this.personagem = personagem;
        this.inventario = personagem.getInventario();
        
        mapaDeCombinacoes = new HashMap<>();    

        for(CombinacoesEnum combinacao: CombinacoesEnum.values()) {
        
            int combinacaoID = calcularIdDaCombinacao(
                combinacao.getMateriaisCombinados(), 
                combinacao.getQuantidades()
            );
            
            // adiciona todas as combinacoes de materiais existentes no hashmap
            mapaDeCombinacoes.put(
                combinacaoID, 
                combinacao.getItemResultanteEnum()
            );
        }
    }

    private int calcularIdDaCombinacao(ItemMaterial[] materiaisCombinados) {
        int combinacaoID = 0;

        // calcula o número identificador de cada combinação a partir da fórmula
        // somatório_(através dos materiais combinados)[ quantidade_do_material * (QUANTIDADE_MAXIMA(4 no momento)+1) ^ (ID_do_material)
        // em que quantidade_do_material deverá ser menor que QUANTIDADE_MAXIMA e o ID_do_material de todos os materiais são distintos,
        // assim permitindo que toda combinação de itens gere um inteiro único        
        for(ItemMaterial material: materiaisCombinados) 
            combinacaoID += material.getQuantidade() * IntMath.pow(Item.QUANTIDADE_MAXIMA, material.getID());

        return combinacaoID;
    }

    private int calcularIdDaCombinacao(MateriaisEnum[] materiaisCombinadosEnum, int[] quantidades) {
        ItemMaterial[] materiais = new ItemMaterial[materiaisCombinadosEnum.length];

        int i = 0;
        for(MateriaisEnum materialEnum: materiaisCombinadosEnum) {
            materiais[i] = ConstrutorMaterial.construirMaterial(materialEnum, quantidades[i]);
            i++;
        }

        return calcularIdDaCombinacao(materiais);
    }

    public void removerItens() {
        if (inventario.estaVazio()) {
            io.print("Inventário vazio!");
            return;
        }

        ArrayList<Item> itens = inventario.getItens();

        while(true) { 
            int indice = io.decisaoEmIntervalo("Escolha o item para descarte ou digite 0 para parar", itens.toArray(Item[]::new));

            if(indice == -1) break;

            int quantidade = Integer.parseInt(io.getInput("Digite a quantidade: "));

            io.print(personagem.getNome() + " descartou " + quantidade + " " + itens.get(indice).getNome() + "(s)");

            inventario.removerItem(indice, quantidade);
        }
    }

    public void combinarMateriais() {
        ArrayList<Item> materiais = new ArrayList<>();

        for(Item material: inventario.getItens(ItensEnum.MATERIAL.getIndice()))
            materiais.add(new ItemMaterial((ItemMaterial) material));

        if(materiais.isEmpty()) {
            io.print("Nenhum material para combinar");
            return;
        }

        //ArrayList<ItemMaterial> materiaisEscolhidos = new ArrayList<>();
        HashMap<String, Item> materiaisEscolhidos = new HashMap<>();
        //HashSet<Integer> indicesMarcados = new HashSet<>();

        while(true) {
            int indice = io.decisaoEmIntervalo("Escolha os materiais, ou digite 0 para parar", materiais.toArray(ItemMaterial[]::new));

            if(indice == -1) break;
            ItemMaterial material = (ItemMaterial) materiais.get(indice);

            int quantidade = Integer.parseInt(io.getInput("Digite a quantidade: "));

            if(quantidade > materiais.get(indice).getQuantidade()) {
                io.print("Quantidade inválida!");
                continue;
            }

            if(materiaisEscolhidos.containsKey(material.getNome())) {
                quantidade = Math.min(
                    Item.QUANTIDADE_MAXIMA - materiaisEscolhidos.get(material.getNome()).getQuantidade(),
                    quantidade
                );
                
                materiaisEscolhidos.get(material.getNome()).mudarQuantidade(quantidade);
            } else {
                ItemMaterial materialAdicionado = new ItemMaterial(material);
                materialAdicionado.setQuantidade(quantidade);

                materiaisEscolhidos.put(material.getNome(), materialAdicionado);
            }

            material.mudarQuantidade(-1*quantidade);
            if(material.getQuantidade() <= 0)
                materiais.remove(indice);

            String materiaisEscolhidosStr = "Materiais Escolhidos: ";
            for(Item materialEscolhido : materiaisEscolhidos.values())
                materiaisEscolhidosStr += materialEscolhido.getNome() + " (" + materialEscolhido.getQuantidade() + "), ";

            io.print(materiaisEscolhidosStr);
        }
        
        ItemMaterial[] materiaisEscolhidosArr = materiaisEscolhidos.values().toArray(ItemMaterial[]::new);

        combinarMateriais(materiaisEscolhidosArr);
    }

    public void combinarMateriais(ItemMaterial[] materiaisCombinados) {
        int combinacaoID = calcularIdDaCombinacao(materiaisCombinados);
        
        if(mapaDeCombinacoes.get(combinacaoID) == null) {
            io.print("Combinação não existe");
            return;
        }

        Item itemCombinado = ConstrutorItem.construir(mapaDeCombinacoes.get(combinacaoID), 1);

        for(ItemMaterial material: materiaisCombinados) 
            inventario.removerItem(material, material.getQuantidade());
        
        inventario.adicionarItem(itemCombinado);

        io.print(personagem.getNome() + " construiu " + itemCombinado.getNome());

    }

    public void usarPerecivel(int indice) {
        IItemPerecivel perecivel = (IItemPerecivel) inventario.getItens().get(indice);

        perecivel.decrementarDurabilidade();

        if(perecivel.estaPerecido())
            inventario.removerItem(indice, 1);
    }

    public void usarItemArma(EventoCriatura criatura) {
        ArrayList<Item> armas = inventario.getItens(ItensEnum.ARMA.getIndice());

        int escolha = io.decisaoEmIntervalo("Escolha sua arma", armas.toArray(ItemArma[]::new));
            
        personagem.getGerenciadorDeInventario().usarItemArma(escolha, criatura);
    }

    public void usarItemArma(int indice, EventoCriatura criatura) {
        ItemArma arma = (ItemArma) inventario.getItens(ItensEnum.ARMA.getIndice()).get(indice);

        if(criatura.serAtacada(arma, personagem)) {
            io.print(personagem.getNome() + " atacou " + criatura.getNome() + " com " + arma.getNome());
            usarPerecivel(indice);    
        } else {
            io.print(arma.getNome() + " não tem alcance suficiente para " + criatura.getNome());
        }    
    }

    public void usarItemConsumivel() {
        ArrayList<Item> consumiveis = inventario.getItens(ItensEnum.CONSUMIVEL.getIndice());

        if (consumiveis.isEmpty()) {
            io.print("Nenhum consumivel disponivel");
            return;
        }

        int indice = io.decisaoEmIntervalo("Escolha o consumível", consumiveis.toArray(Consumivel[]::new));

        usarItemConsumivel(indice);
    }

    public void usarItemConsumivel(int indice) {
        Consumivel consumivel = (Consumivel) inventario.getItens(ItensEnum.CONSUMIVEL.getIndice()).get(indice);

        consumivel.consumir(personagem);
        io.print(personagem.getNome() + " consumiu " + consumivel.getNome());
        
        inventario.removerItem(consumivel, 1);
    }

    
}

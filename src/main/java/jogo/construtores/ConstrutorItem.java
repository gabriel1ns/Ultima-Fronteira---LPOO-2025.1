package jogo.construtores;

import jogo.construtores.itens.ConstrutorArma;
import jogo.construtores.itens.ConstrutorFerramenta;
import jogo.construtores.itens.ConstrutorMaterial;
import jogo.construtores.itens.consumiveis.ConstrutorAgua;
import jogo.construtores.itens.consumiveis.ConstrutorAlimento;
import jogo.construtores.itens.consumiveis.ConstrutorRemedio;
import jogo.enums.itens.ArmasEnum;
import jogo.enums.itens.FerramentasEnum;
import jogo.enums.itens.MateriaisEnum;
import jogo.enums.itens.consumiveis.AguaEnum;
import jogo.enums.itens.consumiveis.AlimentosEnum;
import jogo.enums.itens.consumiveis.RemediosEnum;
import jogo.sistema.itens.Item;

public class ConstrutorItem {
    public static Item construir(Enum<?> itemEnum, int quantidade) throws IllegalArgumentException {
        String tipo = itemEnum.getClass().getSimpleName();

        if(tipo.equals(ArmasEnum.class.getSimpleName()))
            return ConstrutorArma.construirArma((ArmasEnum) itemEnum, quantidade);

        if(tipo.equals(FerramentasEnum.class.getSimpleName()))
            return ConstrutorFerramenta.construirFerramenta((FerramentasEnum) itemEnum, quantidade);

        if(tipo.equals(MateriaisEnum.class.getSimpleName()))
            return ConstrutorMaterial.construirMaterial((MateriaisEnum) itemEnum, quantidade);

        if(tipo.equals(AguaEnum.class.getSimpleName()))
            return ConstrutorAgua.construirAgua((AguaEnum) itemEnum, quantidade);

        if(tipo.equals(AlimentosEnum.class.getSimpleName()))
            return ConstrutorAlimento.construirAlimento((AlimentosEnum) itemEnum, quantidade);

        if(tipo.equals(RemediosEnum.class.getSimpleName()))
            return ConstrutorRemedio.construirRemedio((RemediosEnum) itemEnum, quantidade);
        
        throw new IllegalArgumentException("Tipo de evento não existente: " + tipo);
    }
}

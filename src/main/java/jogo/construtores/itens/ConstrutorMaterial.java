package jogo.construtores.itens;

import jogo.enums.itens.MateriaisEnum;
import jogo.itens.ItemMaterial;

public class ConstrutorMaterial {
    public static ItemMaterial construirMaterial(MateriaisEnum material, int quantidade) {
        return new ItemMaterial(material.getNome(), material.getPeso(), quantidade, material.getId());
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//ALLAN BALDISSIN  8657904

package Xadrez;

import gui.Posicao;
import pecas.*;
import pecas.Peca.Cor;

/**
 *
 * @author Allan
 */
public class FactoryPeca {
    
    public Peca getPeca(String tipo, Cor cor, Posicao matrizTabuleiro){
        if(tipo == "Peao")
            return new Peao(cor, matrizTabuleiro);
        else if(tipo == "Torre")
            return new Torre(cor, matrizTabuleiro);
        else if(tipo == "Cavalo")
            return new Cavalo(cor, matrizTabuleiro);
        else if(tipo == "Bispo")
            return new Bispo(cor, matrizTabuleiro);
        else if(tipo == "Rei")
            return new Rei(cor, matrizTabuleiro);
        else
            return new Dama(cor, matrizTabuleiro);
    }
}

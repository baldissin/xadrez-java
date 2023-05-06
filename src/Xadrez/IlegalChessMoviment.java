/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// ALLAN BALDISSIN 8657904

package Xadrez;

import pecas.*;

/**
 *
 * @author Allan
 */
public class IlegalChessMoviment extends Exception{
    private Peca p;
    
    public IlegalChessMoviment(Peca p){
        this.p = p;
    }
    
    public Peca getPeca(){
        return p;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// ALLAN BALDISSIN 8657904

package pecas;


import Xadrez.IlegalChessMoviment;
import Xadrez.XadrezBotao;
import gui.*;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Allan
 */
public abstract class Peca implements Serializable{
    
    private Cor cor;
    private Posicao casa;
    XadrezBotao xadrez;
    private ArrayList<Posicao> listaPossivel;
    
    public Peca(Cor cor, Posicao casa)
    {
        xadrez = XadrezBotao.getXadrezSingleton();
        setCor(cor);
        this.casa = casa;
        this.desenha();
        this.casa.setPeca(this);
        listaPossivel = null;
        
    }

    public enum Cor{
        preta,
        branca
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public Posicao getCasa() {
        return casa;
    }

    public void setCasa(Posicao casa) {
        this.casa = casa;
    }
    
    public abstract void desenha();
    public abstract ArrayList<Posicao> casasPossiveis();
    public abstract ArrayList<Posicao> casasParaComer();
    
    public void verifMovimento(Posicao p) throws IlegalChessMoviment
    {
        listaPossivel = casasPossiveis();
        if(listaPossivel.contains(p))
        {
            return;
        }
        else
            throw new IlegalChessMoviment(this);
    }
    
    public void verifComer(Posicao p) throws IlegalChessMoviment{
        listaPossivel = casasParaComer();
        if (listaPossivel.contains(p)) {
            return;
        }
        else
            throw new IlegalChessMoviment(this);
    }
    public void desenhaPecaSelecionada(int x, int y){
        
        if (xadrez.getMatrizTabuleiro()[x][y].getCor() == Peca.Cor.branca) {
            xadrez.getMatrizTabuleiro()[x][y].getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/BrancaSelecionada/casaBrancaS.jpg")));
        }
        else
        {
            xadrez.getMatrizTabuleiro()[x][y].getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/PretaSelecionada/casaPretaS.jpg")));
        }
    }
    public abstract void casasPossiveisSelecionadas();
    
    public abstract void casaPossiveisAtaque();

    public abstract void desenhaAtaque();
    
}
